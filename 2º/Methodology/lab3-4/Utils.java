import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    private static Scanner sc = new Scanner(System.in);

    public static Floor readOrGenerateFloor(String message) {
        File file = readFileName(message);

        if (file.exists()) {
            return readTilesFromFile(file);
        } else {
            return generateTilesAndFile(file);
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
                tiles.add(new Tile(width, height, tiles.size() + 1));
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
            tiles.add(new Tile(width, height, i + 1));
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
        System.out.print(msg);
        String fileName = sc.nextLine();
        return new File(fileName);
    }

    public static void printFloor(Floor f) {
        int maxPlacedId = 0;

        for (int i = 0; i < f.getHeight(); i++) {
            for (int j = 0; j < f.getWidth(); j++) {
                int tileIndex = f.getTileIndex(j, i);
                if (tileIndex > 0) {
                    int tileId = f.getTiles().get(tileIndex - 1).getId();
                    if (tileId > maxPlacedId) {
                        maxPlacedId = tileId;
                    }
                }
            }
        }

        int maxDigits = String.valueOf(maxPlacedId).length();

        for (int i = 0; i < f.getHeight(); i++) {
            for (int j = 0; j < f.getWidth(); j++) {
                int tileIndex = f.getTileIndex(j, i);
                int displayValue = (tileIndex > 0) ? f.getTiles().get(tileIndex - 1).getId() : 0;
                System.out.printf("%" + maxDigits + "d ", displayValue);
            }
            System.out.println();
        }
    }

    public static void printTiles(Floor f) {
        ArrayList<Tile> tiles = f.getTiles();

        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            System.out.printf("#%d: %s\n", t.getId(), t);
        }
    }

    public static void printSolutionResult(Floor floor, String algorithmName) {
        if (floor.hasFreeSpace()) {
            System.out.println("Floor couldnt be fully solved.");
        } else {
            System.out.println("Floor fully solved with " + floor.getPlacedTiles().size() + " tiles used.");
        }

        System.out.println("Floor after " + algorithmName + " algorithm:");
        printFloor(floor);
    }
}
