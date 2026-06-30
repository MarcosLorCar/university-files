import java.util.ArrayList;

public class P4_Sol {
    public static void main(String[] args) {
        Floor floor = Utils.readOrGenerateFloor("File to read or generate: ");

        System.out.println("Available tiles:");
        Utils.printTiles(floor);
        System.out.println();

        System.out.println("Solving with backtracking algorithm.");

        Floor solution = solveBacktrackingTiling(floor, new ArrayList<>(), 0, null);

        Utils.printSolutionResult(solution, "backtracking");
    }

    /**
     * The backtracking algorithm that finds the best solution.
     * The solution is represented as a 2d matrix that stores the indicies of the tile that occupies each position.
     * In each depth step, first it checks if the current depth is a leaf node, and if so, it replaces best solution
     * if the current one is better. If not, it tries to place the tile at the current depth, and recursively calls
     * deeper in the recursion tree.
     * A partial solution is valid when its neither a leaf node, nor has it exceeded the current best solution's count.
     * A complete solution is when there is no more free space in the floor.
     * Backtrack occurs when the current solution is not valid.
     */
    private static Floor solveBacktrackingTiling(Floor floor, ArrayList<Tile> placedTiles, int depth, Floor bestSolution) {
        // Check if its a leaf node
        if (depth >= floor.getTiles().size()) {
            // Check if its best
            if (!floor.hasFreeSpace()) {
                if (bestSolution == null || placedTiles.size() < bestSolution.getPlacedTiles().size()) {
                    bestSolution = new Floor(floor, new ArrayList<>(placedTiles));
                }
            }
            return bestSolution;
        } else {
            // Prune branch
            if (bestSolution != null && placedTiles.size() >= bestSolution.getPlacedTiles().size()) {
                return bestSolution;
            }

            Tile t = floor.getTiles().get(depth);

            // Try normal
            if (floor.tryPlaceWithDimensions(depth, t.getWidth(), t.getHeight())) {
                placedTiles.add(t);
                bestSolution = solveBacktrackingTiling(floor, placedTiles, depth + 1, bestSolution);
                placedTiles.remove(placedTiles.size() - 1);
                floor.removeTile(depth);
            }

            // Try transposed
            Tile tRot = t.rotated();
            if (floor.tryPlaceWithDimensions(depth, tRot.getWidth(), tRot.getHeight())) {
                placedTiles.add(tRot);
                bestSolution = solveBacktrackingTiling(floor, placedTiles, depth + 1, bestSolution);
                placedTiles.remove(placedTiles.size() - 1);
                floor.removeTile(depth);
            }

            // Try without placing
            bestSolution = solveBacktrackingTiling(floor, placedTiles, depth + 1, bestSolution);

            return bestSolution;
        }
    }

    /**
     * Previous, session 1 version of the backtracking algorithm,
     * instead of keeping track of the best solution, it returns true/false depending on if any solution
     * has been found, and avoiding more recursion if so.
     * The resulting floor object has the found solution if there has been one.
     */
    private static boolean solveBacktrackingTiling(Floor floor, ArrayList<Tile> placedTiles, int depth) {
        // Check if leaf node
        if (!floor.hasFreeSpace() || placedTiles.size() == floor.getTiles().size()) {
            // Check if solution found
            if (!floor.hasFreeSpace()) {
                return true;
            }
        } else {
            if (depth >= floor.getTiles().size()) {
                return false;
            }

            Tile t = floor.getTiles().get(depth);

            // Try normal
            if (floor.tryPlaceWithDimensions(depth, t.getWidth(), t.getHeight())) {
                placedTiles.add(t);
                if (solveBacktrackingTiling(floor, placedTiles, depth + 1)) {
                    return true;
                }
                placedTiles.remove(placedTiles.size() - 1);
                floor.removeTile(depth);
            }

            // Try transposed
            Tile tRot = t.rotated();
            if (floor.tryPlaceWithDimensions(depth, tRot.getWidth(), tRot.getHeight())) {
                placedTiles.add(tRot);
                if (solveBacktrackingTiling(floor, placedTiles, depth + 1)) {
                    return true;
                }
                placedTiles.remove(placedTiles.size() - 1);
                floor.removeTile(depth);

            }

            // Try without placing
            if (solveBacktrackingTiling(floor, placedTiles, depth + 1)) {
                return true;
            }
        }
        return false;
    }
}
