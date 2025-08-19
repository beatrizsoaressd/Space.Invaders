package spaceinvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet {
    private Rectangle sprite;
    private double speed;

    public Bullet(double x, double y, double speed) {
        this.speed = speed;
        sprite = new Rectangle(5, 10, Color.YELLOW);
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public void update() {
        sprite.setTranslateY(sprite.getTranslateY() + speed);
    }
}


