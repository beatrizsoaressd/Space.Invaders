package spaceinvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy {
    private Rectangle sprite;
    private double speed = 2;

    public Enemy(double x, double y) {
        sprite = new Rectangle(30, 20, Color.RED);
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public void move() {
        sprite.setTranslateX(sprite.getTranslateX() + speed);
    }

    public void reverseDirection() {
        speed *= -1;
        sprite.setTranslateY(sprite.getTranslateY() + 20);
    }

    public double getWidth() {
        return sprite.getWidth();
    }
    public double getX() {
        return sprite.getTranslateX();
    }
    public double getY() {
        return sprite.getTranslateY();
    }
}
