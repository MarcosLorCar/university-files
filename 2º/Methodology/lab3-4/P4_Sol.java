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

    private static Floor solveBacktrackingTiling(Floor floor, ArrayList<Tile> placedTiles, int depth, Floor bestSolution) {
        // Check if its a leaf node
        if (depth >= floor.getTiles().size()) {
            // Check if its best solution so far (and valid/complete)
            if (!floor.hasFreeSpace()) {
                if (bestSolution == null || placedTiles.size() < bestSolution.getPlacedTiles().size()) {
                    bestSolution = new Floor(floor, new ArrayList<>(placedTiles));
                }
            }
            return bestSolution;
        }

        Tile t = floor.getTiles().get(depth);

        // Try normal placement
        if (floor.tryPlaceWithDimensions(depth, t.getWidth(), t.getHeight())) {
            placedTiles.add(t);
            bestSolution = solveBacktrackingTiling(floor, placedTiles, depth + 1, bestSolution);
            placedTiles.remove(placedTiles.size() - 1);
            floor.removeTile(depth);
        }

        // Try transposed placement
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
