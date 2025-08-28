package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Text hudScore;
    private Text hudWave;
    private int currentWave = 1;

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

        //HUD: pontuação
        hudScore = new Text("PONTUAÇÃO: " + GameConfig.score);
        hudWave  = new Text("WAVE: " + currentWave);
        hudScore.setFill(Color.WHITE);
        hudWave.setFill(Color.WHITE);
        hudScore.setFont(Font.font(20));
        hudWave.setFont(Font.font(20));
        hudScore.setTranslateX(20);
        hudScore.setTranslateY(30);
        hudWave.setTranslateX(GameConfig.WINDOW_WIDTH - 100);
        hudWave.setTranslateY(30);

        root.getChildren().addAll(hudScore, hudWave);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
    }

    //detecção de colisões, eliminação de inimigos
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
            GameConfig.addScore(GameConfig.SCORE_PER_ENEMY);
            hudScore.setText("PONTUAÇÃO: " + GameConfig.score);
        }

        if (enemyManager.getEnemies().isEmpty()) {
            if (currentWave >= 5) {
                displayEndMessage(true);
                gameLoop.stop();
                return;
            }
            currentWave++;
            enemyManager.nextWave();
            hudWave.setText("WAVE: " + currentWave);
        }

        enemyManager.update(GameConfig.WINDOW_WIDTH);

        for (Enemy e : enemyManager.getEnemies()) {
            if (e.getBounds().intersects(player.getSprite().getBoundsInParent())) {
                displayEndMessage(false);
                gameLoop.stop();
                return;
            }
        }
    }

    private void displayEndMessage(boolean victory) {
        GaussianBlur blurEffect = new GaussianBlur(10);
        for (javafx.scene.Node node : root.getChildren()) {
            node.setEffect(blurEffect);
        }
        String msg = victory ? "VOCÊ VENCEU!" : "VOCÊ PERDEU!";
        Color color = victory ? Color.LIMEGREEN : Color.RED;

        Text endText = new Text(msg);
        endText.setFill(color);
        endText.setStroke(Color.BLACK);
        endText.setStrokeWidth(1);
        try {
            endText.setFont(Font.loadFont(getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf"), 48));
        } catch (Exception e) {
            endText.setFont(Font.font("Consolas", 48));
        }
        endText.setX((GameConfig.WINDOW_WIDTH - endText.getLayoutBounds().getWidth()) / 2);
        endText.setY(GameConfig.WINDOW_HEIGHT / 2.0 - 50);

        Button backButton = new Button("MENU");
        backButton.setFont(Font.font("Press Start 2P", 16)); // usa a mesma fonte pixelada
        backButton.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: limegreen;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        );
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-background-color: limegreen;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        ));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: limegreen;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        ));
        backButton.setPrefWidth(220);
        backButton.setPrefHeight(40);

        backButton.setLayoutX((GameConfig.WINDOW_WIDTH - 220) / 2.0);
        backButton.setLayoutY(GameConfig.WINDOW_HEIGHT / 2.0 + 30);

        backButton.setOnAction(e -> {
            goToMenu();
        });
        root.getChildren().addAll(endText, backButton);
    }

    private void goToMenu() {
        try {
            Stage stage = (Stage) scene.getWindow();
            MenuWindow menu = new MenuWindow();
            menu.start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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