package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.effect.GaussianBlur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameWindow extends Application {

    private Pane root;
    private Scene scene;

    private Player player;
    private EnemyManager enemyManager;
    private final List<Bullet> bullets = new ArrayList<>();

    private boolean movingLeft = false;
    private boolean movingRight = false;

    private long lastShotTime = 0;
    private final long shootCooldown = 300_000_000;

    private AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        scene = new Scene(root, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/stars_black_background.png")));
        ImageView backgroundView = new ImageView(backgroundImage);

        backgroundView.setFitWidth(GameConfig.WINDOW_WIDTH);
        backgroundView.setFitHeight(GameConfig.WINDOW_HEIGHT);
        root.getChildren().add(backgroundView);

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

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
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
        if (enemyManager.getEnemies().isEmpty()) {
            displayVictoryMessage();
            gameLoop.stop(); // Para o loop do jogo
        }

        enemyManager.update(GameConfig.WINDOW_WIDTH);
    }

    private void displayVictoryMessage() {
        GaussianBlur blurEffect = new GaussianBlur(10);
        for (javafx.scene.Node node : root.getChildren()) {
            node.setEffect(blurEffect);
        }

        Text victoryText = new Text("Você venceu!");
        victoryText.setFill(Color.LIMEGREEN);
        victoryText.setStroke(Color.WHITE);
        victoryText.setStrokeWidth(1);
        try {
            victoryText.setFont(Font.loadFont(getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf"), 48));
        } catch (Exception e) {
            victoryText.setFont(Font.font("Consolas", 48));
        }

        victoryText.setX((GameConfig.WINDOW_WIDTH - victoryText.getLayoutBounds().getWidth()) / 2);
        victoryText.setY(GameConfig.WINDOW_HEIGHT / 2.0);

        root.getChildren().add(victoryText);
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