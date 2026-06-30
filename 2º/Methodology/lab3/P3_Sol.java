import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class P3_Sol {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        File file = readFileName("File to read or generate: ");
    
        Floor floor;

        if (file.exists()) {
            floor = readTilesFromFile(file);
        } else {
            floor = generateTilesAndFile(file);
        }

        printTiles(floor);

        System.out.println("Solving with greedy algorithm.");

        solveGreedyTiling(floor);

        if (floor.hasFreeSpace()) {
            System.out.println("Floor couldnt be fully solved.");
        } else {
            System.out.println("Floor fully solved.");
        }

        System.out.println("Floor after greedy algorithm:");
        printFloor(floor);
    }

    private static void solveGreedyTiling(Floor floor) {
        ArrayList<Tile> tiles = floor.getTiles();
        tiles.sort((t1, t2) -> Integer.compare(t2.area(), t1.area()));

        for (int i = 0; i < tiles.size(); i++) {
            boolean success = floor.tryPlace(i);
        }
    }

    private static Floor readTilesFromFile(File file) {
        System.out.println("Reading file: " + file.getName());

        int floorWidth = 0, floorHeight = 0;
        ArrayList<Tile> tiles = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            floorWidth = Integer.parseInt(sc.nextLine());
            floorHeight = Integer.parseInt(sc.nextLine());
            while (sc.hasNextLine()) {
                String tileStr = sc.nextLine();
                int width = Integer.parseInt(tileStr.split(" ")[0]);
                int height = Integer.parseInt(tileStr.split(" ")[1]);
                tiles.add(new Tile(width, height));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File error.");
            System.exit(1);
        }
        return new Floor(tiles, floorWidth, floorHeight);
    }

    private static Floor generateTilesAndFile(File file) {
        System.out.println("Generating file: " + file.getName());
        int floorWidth = readPositiveInt("Enter floor width: ");
        int floorHeight = readPositiveInt("Enter floor height: ");
        int numTiles = readPositiveInt("Enter number of tiles: ");
        int maxWidth = readPositiveInt("Enter max width of tiles: ");
        int maxHeight = readPositiveInt("Enter max height of tiles: ");
        ArrayList<Tile> tiles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numTiles; i++) {
            int width = random.nextInt(maxWidth) + 1;
            int height = random.nextInt(maxHeight) + 1;
            tiles.add(new Tile(width, height));
        }
        try {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.println(floorWidth);
            writer.println(floorHeight);
            for (Tile t : tiles) {
                writer.printf("%d %d\n", t.getWidth(), t.getHeight());
            }
            writer.close();
            System.out.println("File created.");
        } catch (IOException e) {
            System.out.println("Failed to create file.");
        }
        return new Floor(tiles, floorWidth, floorHeight);
    }

    private static int readPositiveInt(String msg) {
        int value;
        do {
            System.out.println(msg);
            value = 0;
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value <= 0) System.out.println("Number must be greater than 0");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        } while (value <= 0);
        return value;
    }

    private static File readFileName(String msg) {
        File file;
        System.out.print(msg);
        String fileName = sc.nextLine();
        file = new File(fileName);
        return file;
    }

    private static void printFloor(Floor f) {
        int maxPlacedIndex = 0;
        for (int i = 0; i < f.getHeight(); i++) {
            for (int j = 0; j < f.getWidth(); j++) {
                int tileIndex = f.getTileIndex(j, i);
                if (tileIndex > maxPlacedIndex) {
                    maxPlacedIndex = tileIndex;
                }
            }
        }
        int maxDigits = String.valueOf(maxPlacedIndex).length();
        for (int i = 0; i < f.getHeight(); i++) {
            for (int j = 0; j < f.getWidth(); j++) {
                System.out.printf("%" + maxDigits + "d ", f.getTileIndex(j, i));
            }
            System.out.println();
        }
    }

    private static void printTiles(Floor f) {
        ArrayList<Tile> tiles = f.getTiles();
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            System.out.printf("#%d: %s\n", i + 1, t.toString());
        }
    }
}
