package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class GameWindow extends Application {

    private Pane root;
    private Scene scene;

    private Player player;
    private Enemy enemy;
    private List<Bullet> bullets = new ArrayList<>();

    private boolean movingLeft = false;
    private boolean movingRight = false;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        scene = new Scene(root, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        player = new Player(200, 550);
        enemy = new Enemy(100, 100);

        root.getChildren().add(player.getSprite());
        root.getChildren().add(enemy.getSprite());

        scene.setOnKeyPressed(event -> {
            // Lógica para capturar teclas, vou colocar a lógica da movimentação aqui
        });

        primaryStage.setTitle(GameConfig.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Aqui vai ser a lógica de atualização do jogo
            }
        }.start();
    }

    public Pane getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }
}