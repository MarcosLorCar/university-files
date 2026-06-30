import java.util.ArrayList;

public class P3_Sol {
    public static void main(String[] args) {
        Floor floor = Utils.readOrGenerateFloor("File to read or generate: ");

        System.out.println("Available tiles:");
        Utils.printTiles(floor);
        System.out.println();

        System.out.println("Solving with greedy algorithm.");

        solveGreedyTiling(floor);

        Utils.printSolutionResult(floor, "greedy");
    }

    /**
     * This greedy implementation first sorts the tiles by area, then tries to place them in the floor by trying
     * each possible placement, with both possible rotations.
     * The elements are the floor, the matrix that represent the current placements of tiles, which serves as the
     * current solution too, and the list of tiles to be placed in the loop.
     * It is not optimal, as seen if p4_test.txt is used for example, as this algorithm will fail to produce a
     * complete solution, while it is possible, due to always placing the tile with the highest area first.
     */
    private static void solveGreedyTiling(Floor floor) {
        ArrayList<Tile> tiles = floor.getTiles();
        tiles.sort((tileA, tileB) -> Integer.compare(tileB.area(), tileA.area()));

        System.out.println("Tiles prepared by area:");
        Utils.printTiles(floor);

        for (int i = 0; i < tiles.size(); i++) {
            floor.tryPlace(i);
        }
    }
}
