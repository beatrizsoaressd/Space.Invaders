package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import spaceinvaders.GameConfig;


public class Player {
    private ImageView sprite;
    private double speed = 4;

    public Player(double x, double y){
        Image image = new Image(getClass().getResourceAsStream("/assets/nave_azul.png"));
        sprite = new ImageView(image);

        // Para ajustar tamanho
        sprite.setFitWidth(50);
        sprite.setFitHeight(50);

        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    public ImageView getSprite(){
        return sprite;
        
    }

    public void moveLeft(){
        if (sprite.getTranslateX() > 0) {
            sprite.setTranslateX(sprite.getTranslateX() - speed);
        }
    }

    public void moveRight() {
        double windowWidth = GameConfig.WINDOW_WIDTH;
        double playerWidth = sprite.getFitWidth();

        if (sprite.getTranslateX() < windowWidth - playerWidth) {
            sprite.setTranslateX(sprite.getTranslateX() + speed);
        }
    }

    public Bullet shoot() {
        return new Bullet(sprite.getTranslateX() + 18, sprite.getTranslateY() - 10, -7);
    }
}
