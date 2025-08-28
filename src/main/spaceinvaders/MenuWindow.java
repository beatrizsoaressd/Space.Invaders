package spaceinvaders;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SPACE INVADERS");

        Button startButton = new Button("INICIAR");
        Button tutorialButton = new Button("COMO JOGAR");
        Button exitButton = new Button("SAIR");

        startButton.setOnAction(e -> {
            try {
                GameWindow game = new GameWindow();
                game.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        tutorialButton.setOnAction(e -> showTutorial(primaryStage));

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        VBox layout = new VBox(20, startButton, tutorialButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showTutorial(Stage stage) {
        Text tutorialText = new Text(
                "COMO JOGAR:\n\n"+
                        "A/D ou setas (← →) para mover a nave\n"+
                        "Barra de espaço para atirar\n"+
                        "Objetivo: Derrote todos os inimigos antes que cheguem até você!\n"
        );
        Button backButton = new Button("Voltar");
        backButton.setOnAction(e -> start(stage));

        VBox layout = new VBox(20, tutorialText, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene tutorialScene = new Scene(layout, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        stage.setScene(tutorialScene);
    }
}
