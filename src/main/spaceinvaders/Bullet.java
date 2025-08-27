package spaceinvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {
    private ImageView sprite;
    private double speed;
    private static final double BULLET_SPEED = 2.0;

    public static final int SPRITE_WIDTH = 16;
    public static final int SPRITE_HEIGHT = 16;
    public static final double DRAW_WIDTH = 6;
    public static final double DRAW_HEIGHT = 20;

    private static final Image SHEET =
            new Image(Bullet.class.getResourceAsStream("/assets/projectiles_16x16.png"));
    private final int col;
    private final int row;

    public Bullet(double x, double y, double speed, int col, int row) {
        this.speed = BULLET_SPEED;
        this.col = col;
        this.row = row;

        sprite = new ImageView(SHEET);
        sprite.setViewport(new Rectangle2D(
                col * SPRITE_WIDTH,
                row * SPRITE_HEIGHT,
                SPRITE_WIDTH,
                SPRITE_HEIGHT
        ));
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
        sprite.setFitWidth(DRAW_WIDTH);
        sprite.setFitHeight(DRAW_HEIGHT);
        sprite.setPreserveRatio(false); // manter formato de “laser”
    }

    public ImageView getSprite() {

        return sprite;
    }

    public void update() {

        sprite.setTranslateY(sprite.getTranslateY() - speed);
    }
}


