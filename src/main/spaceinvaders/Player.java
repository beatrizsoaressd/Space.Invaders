package spaceinvaders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Player {
    private Rectangle sprite;
    private double speed = 5;

    public Player(double x, double y){
        sprite = new Rectangle(40, 20, Color.BLUE);
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    public Rectangle getSprite(){
        return sprite;
    }

    public void moveLeft(){
        sprite.setTranslateX(sprite.getTranslateX() - speed);
    }

    public void moveRight() {
        sprite.setTranslateX(sprite.getTranslateX() + speed);
    }

    public Bullet shoot(){
        return new Bullet(sprite.getTranslateX() + 18, sprite.getTranslateY() - 10, -7);
    }
}
