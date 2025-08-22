package spaceinvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy {
    private ImageView sprite;
    private double speed = 2;

    //Tamanho de cada quadradinho do sprite
    private static final int SPRITE_WIDTH = 36;
    private static final int SPRITE_HEIGHT = 36;
    private static final Image spriteSheet = new Image(Enemy.class.getResourceAsStream("/assets/inimigos_aliens_36x36.png"));
    private int row;
    private int col;
    public Enemy(double x, double y, int col, int row) {
        this.row = row;
        this.col = col;

        sprite = new ImageView(spriteSheet);

        sprite.setViewport(new Rectangle2D(
                col * SPRITE_WIDTH,
                row * SPRITE_HEIGHT,
                SPRITE_WIDTH,
                SPRITE_HEIGHT
        ));
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);

        //redimensiona os inimigos
        sprite.setFitWidth(30);
        sprite.setFitHeight(30);
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
