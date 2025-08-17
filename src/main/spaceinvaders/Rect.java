package spaceinvaders;

public class Rect {
    public double x, y, w, h;
    public Rect(double x, double y, double w, double h) {
        this.x = x; this.y = y; this.w = w; this.h = h;
    }
    public boolean intersects(Rect other) {
        return this.x < other.x + other.w &&
                this.x + this.w > other.x &&
                this.y < other.y + other.h &&
                this.y + this.h > other.y;
    }
}
