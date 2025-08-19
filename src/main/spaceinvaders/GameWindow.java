package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class GameWindow extends Application {

    private Pane root;
    private Scene scene;

    private Player player;
    private EnemyManager enemyManager;
    private List<Bullet> bullets = new ArrayList<>();

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private long lastShotTime = 0;
    private long shootCooldown = 300_000_000;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        scene = new Scene(root, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        player = new Player(200, 550);
        enemyManager = new EnemyManager(root);

        root.getChildren().add(player.getSprite());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                movingLeft = true;
            }
            if (event.getCode() == KeyCode.A) {
                movingLeft = true;
            }
            if (event.getCode() == KeyCode.RIGHT) {
                movingRight = true;
            }
            if (event.getCode() == KeyCode.D) {
                movingRight = true;
            }
            // O player pode se movimentar usando as setinhas ou A/D
            if (event.getCode() == KeyCode.SPACE) {
                long now = System.nanoTime();
                if (now - lastShotTime >= shootCooldown) {
                    Bullet bullet = player.shoot();
                    bullets.add(bullet);
                    root.getChildren().add(bullet.getSprite());
                    lastShotTime = now;
                }
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                movingLeft = false;
            }
            if (event.getCode() == KeyCode.A) {
                movingLeft = false;
            }
            if (event.getCode() == KeyCode.RIGHT) {
                movingRight = false;
            }
            if (event.getCode() == KeyCode.D) {
                movingRight = false;
            }
        });

        primaryStage.setTitle(GameConfig.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        }.start();
    }
    private void update() {
        if (movingLeft) player.moveLeft();
        if (movingRight) player.moveRight();

        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Bullet b : bullets) {
            b.update();
            for (Enemy e : enemyManager.getEnemies()) {
                if (b.getSprite().getBoundsInParent().intersects(
                        e.getSprite().getBoundsInParent())) {
                    bulletsToRemove.add(b);
                    enemiesToRemove.add(e);
                }
            }
        }
        for (Bullet b : bulletsToRemove) {
            root.getChildren().remove(b.getSprite());
            bullets.remove(b);
        }
        for (Enemy e : enemiesToRemove) {
            root.getChildren().remove(e.getSprite());
            enemyManager.getEnemies().remove(e);
        }

        enemyManager.update(GameConfig.WINDOW_WIDTH);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Pane getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }
}