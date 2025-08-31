package spaceinvaders;

public class Rect {
    private double x;
    private double y;
    private double width;
    private double height;
    public Rect(double x, double y, double width, double height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public boolean intersects(Rect other) {
        return this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + this.height > other.y;
    }
}
