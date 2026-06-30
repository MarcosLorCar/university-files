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

    private static void solveGreedyTiling(Floor floor) {
        ArrayList<Tile> tiles = floor.getTiles();
        tiles.sort((t1, t2) -> Integer.compare(t2.area(), t1.area()));

        System.out.println("Tiles prepared by area:");
        Utils.printTiles(floor);

        for (int i = 0; i < tiles.size(); i++) {
            floor.tryPlace(i);
        }
    }
}
