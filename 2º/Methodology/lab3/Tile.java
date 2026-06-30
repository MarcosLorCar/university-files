public class Tile {
    private final int w;
    private final int h;

    public Tile(int w, int h) {
        this.w = w;
        this.h = h;
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

    public Tile rotated() {
        return new Tile(h, w);
    }

    @Override
    public String toString() {
        return w + "x" + h;
    }
}
