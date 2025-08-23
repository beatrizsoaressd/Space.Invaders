package spaceinvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();
    private Pane root;
    private Timeline animation;

    public EnemyManager(Pane root) {
        this.root = root;
        createEnemies();
        startAnimation();
    }

    private void createEnemies() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                double x = 80 + col * 50;
                double y = 80 + row * 40;

                int spriteCol = 0;
                int spriteRow = row;

                Enemy enemy = new Enemy(x, y, spriteCol, spriteRow);
                enemies.add(enemy);
                root.getChildren().add(enemy.getSprite());
            }
        }
    }

    private void startAnimation() {
        // Alterna o frame de todos os inimigos a cada 500ms
        animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            for (Enemy enemy : enemies) {
                enemy.nextFrame();
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public void update(double windowWidth) {
        boolean reverse = false;

        for (Enemy e : enemies) {
            e.move();
            if (e.getX() <= 0 || e.getX() >= windowWidth - e.getWidth()) {
                reverse = true;
            }
        }

        if (reverse) {
            for (Enemy e : enemies) {
                e.reverseDirection();
            }
        }
    }

    public List<Enemy> getEnemies() {

        return enemies;
    }
}

