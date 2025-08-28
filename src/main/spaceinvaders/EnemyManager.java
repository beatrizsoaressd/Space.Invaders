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
    private  int currentWave = 1;

    public EnemyManager(Pane root) {
        this.root = root;
        createEnemies(currentWave);
        startAnimation();
    }

    private void createEnemies(int wave) {
        int rows = 3 + (wave - 1);
        int cols = 8;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = 80 + col * 50;
                double y = 80 + row * 40;

                int spriteCol = 0;
                int spriteRow = row % 3;

                Enemy enemy = new Enemy(x, y, spriteCol, spriteRow);
                enemies.add(enemy);
                root.getChildren().add(enemy.getSprite());
            }
        }

    }

    private void startAnimation() {
        // Alterna o frame de todos os inimigos a cada 150ms
        animation = new Timeline(new KeyFrame(Duration.millis(150), e -> {
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

    public void nextWave() {
        currentWave++;
        for(Enemy e : enemies) {
            root.getChildren().remove(e.getSprite());
        }
        enemies.clear();
        createEnemies(currentWave);
    }

    public int getCurrentWave() {
        return currentWave;
    }
}

