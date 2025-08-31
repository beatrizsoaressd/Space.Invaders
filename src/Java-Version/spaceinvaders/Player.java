package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import spaceinvaders.GameConfig;


public class Player {
    private ImageView sprite;
    private double speed = 2;

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
        // centraliza o tiro no meio da nave
        double bx = sprite.getTranslateX() + sprite.getFitWidth()/2 - Bullet.DRAW_WIDTH/2;
        double by = sprite.getTranslateY() - 8;

        // para escolher o sprite do projétil (col,row) da spritesheet
        // Ex.: um laser fino azul no topo pode ser (1,0). (para a gente se guiar depois)
        int projectileCol = 1;
        int projectileRow = 0;
        return new Bullet(bx, by, -8, projectileCol, projectileRow);
    }

    public double getX() {
        return sprite.getX();
    }
    public double getY() {
        return sprite.getY();
    }
}
