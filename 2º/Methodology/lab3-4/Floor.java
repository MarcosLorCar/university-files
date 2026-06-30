import java.util.ArrayList;

public class Floor {
    private int[][] placedTiles;
    private ArrayList<Tile> tiles;
    private ArrayList<Tile> placedTilesList;

    public Floor(ArrayList<Tile> tiles, int width, int height) {
        placedTilesList = new ArrayList<>();
        this.placedTiles = new int[width][height];
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                placedTiles[i][j] = 0;
            }
        }
        this.tiles = tiles;
    }

    public Floor(Floor floor, ArrayList<Tile> placedTiles) {
        this.placedTilesList = placedTiles;
        this.placedTiles = new int[floor.getWidth()][floor.getHeight()];
        for (int i = 0; i < floor.getWidth(); i++) {
            for (int j = 0; j < floor.getHeight(); j++) {
                this.placedTiles[i][j] = floor.placedTiles[i][j];
            }
        }
        this.tiles = floor.tiles;
    }

    public int getWidth() {
        return placedTiles.length;
    }

    public int getHeight() {
        return placedTiles[0].length;
    }

    public int getTileIndex(int x, int y) {
        int index;
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            index = -1;
        } else {
            index = placedTiles[x][y];
        }
        return index;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Tile> getPlacedTiles() {
        return placedTilesList;
    }

    public boolean tryPlace(int i) {
        Tile t = tiles.get(i);
        Tile tRot = t.rotated();

        boolean success = tryPlaceWithDimensions(i, t.getWidth(), t.getHeight());
        if (!success) {
            success = tryPlaceWithDimensions(i, tRot.getWidth(), tRot.getHeight());
        }
        return success;
    }

    public void removeTile(int i) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (placedTiles[x][y] == i + 1) {
                    placedTiles[x][y] = 0;
                }
            }
        }
    }

    public boolean tryPlaceWithDimensions(int i, int tileWidth, int tileHeight) {
        boolean success = false;
        for (int y = 0; y < getHeight() && !success; y++) {
            for (int x = 0; x < getWidth() && !success; x++) {
                boolean free = true;
                for (int j = 0; j < tileHeight && free; j++) {
                    for (int k = 0; k < tileWidth; k++) {
                        if (x + k >= getWidth() || y + j >= getHeight() || placedTiles[x + k][y + j] != 0) {
                            free = false;
                            break;
                        }
                    }
                }
                if (free) {
                    success = true;
                    for (int j = 0; j < tileHeight; j++) {
                        for (int k = 0; k < tileWidth; k++) {
                            placedTiles[x + k][y + j] = i + 1;
                        }
                    }
                }
            }
        }
        return success;
    }

    public boolean hasFreeSpace() {
        boolean free = false;
        for (int i=0; i<placedTiles.length && !free; i++) {
            for (int j=0; j<placedTiles[i].length; j++) {
                if (placedTiles[i][j] == 0) {
                    free = true;
                    break;
                }
            }
        }
        return free;
    }
}
