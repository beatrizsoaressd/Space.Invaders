package spaceinvaders;

public class Vector {
    private double x, y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Métodos para obter os valores
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Métodos para alterar os valores
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // para somar outro vetor
    public void add(Vector other) {
        this.x += other.x;
        this.y += other.y;
    }

    // para multiplicar por um escalar (número)
    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }
}
