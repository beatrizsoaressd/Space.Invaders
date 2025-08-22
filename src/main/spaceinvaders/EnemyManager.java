package spaceinvaders;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();
    private Pane root;

    public EnemyManager(Pane root) {
        this.root = root;
        createEnemies();
    }

    private void createEnemies() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                double x = 80 + col * 50;
                double y = 80 + row * 40;

                Enemy enemy = new Enemy(x, y, col % 5, row);
                enemies.add(enemy);
                root.getChildren().add(enemy.getSprite());
            }
        }
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

