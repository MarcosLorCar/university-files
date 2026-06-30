public class Tile {
    private final int w;
    private final int h;
    private final int id;

    public Tile(int w, int h, int id) {
        this.w = w;
        this.h = h;
        this.id = id;
    }

    public int area() {
        return w * h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public int getId() {
        return id;
    }

    public Tile rotated() {
        return new Tile(h, w, id);
    }

    @Override
    public String toString() {
        return w + "x" + h;
    }
}
