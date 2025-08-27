package spaceinvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    private ImageView sprite;
    private double speed = 0.6;

    //Tamanho de cada quadradinho do sprite
    private static final int SPRITE_WIDTH = 36;
    private static final int SPRITE_HEIGHT = 36;
    private static final Image spriteSheet = new Image(Enemy.class.getResourceAsStream("/assets/blue_alien_black_36x36.png"));

    private int row;
    private int col;
    private int numFrames;

    public Enemy(double x, double y, int initialCol, int row) {
        this.row = row;
        this.col = initialCol;

        // calcula quantos frames cabem horizontalmente
        numFrames = (int)(spriteSheet.getWidth() / SPRITE_WIDTH);

        sprite = new ImageView(spriteSheet);
        updateViewport();

        sprite.setTranslateX(x);
        sprite.setTranslateY(y);

        sprite.setFitWidth(40);
        sprite.setFitHeight(35);
        sprite.setPreserveRatio(true);

    }
private void updateViewport() {
    sprite.setViewport(new Rectangle2D(
            col * SPRITE_WIDTH,
            row * SPRITE_HEIGHT,
            SPRITE_WIDTH,
            SPRITE_HEIGHT
    ));
}
public void nextFrame() {
        col = (col + 1) % numFrames;
        updateViewport();
}
    public ImageView getSprite() {
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
        return sprite.getFitWidth();
    }
    public double getX() {

        return sprite.getTranslateX();
    }
    public double getY() {

        return sprite.getTranslateY();
    }
}
