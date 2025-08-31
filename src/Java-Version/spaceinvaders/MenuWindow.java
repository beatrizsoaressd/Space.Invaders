package spaceinvaders;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuWindow extends Application {

    private Font pixelFont;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu - Space Invaders");

        try {
            pixelFont = Font.loadFont(
                    getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf"), 24
            );
        } catch (Exception e) {
            pixelFont = Font.font("Consolas", 24);
        }

        Text title = new Text("SPACE INVADERS");
        title.setFill(Color.WHITE);
        title.setStroke(Color.LIMEGREEN);
        title.setStrokeWidth(1);
        title.setFont(Font.font(pixelFont.getFamily(), 42));

        Button startButton = createStyledButton("INICIAR");
        Button tutorialButton = createStyledButton("COMO JOGAR");
        Button exitButton = createStyledButton("SAIR");

        startButton.setOnAction(e -> {
            try {
                GameWindow game = new GameWindow();
                game.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        tutorialButton.setOnAction(e -> showTutorial(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(30, title, startButton, tutorialButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        layout.setBackground(createBackground());

        Scene scene = new Scene(layout, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showTutorial(Stage stage) {
        Text tutorialText = new Text(
                "TUTORIAL:\n\n" +
                        "TECLAS A/D OU SETAS (← →): MOVER A NAVE\n\n" +
                        "BARRA DE ESPAÇO: ATIRAR\n\n" +
                        "OBJETIVO: DERROTE TODOS OS INIMIGOS\nANTES QUE CHEGUEM ATÉ VOCÊ!"
        );
        tutorialText.setFill(Color.WHITE);
        tutorialText.setStroke(Color.BLACK);
        tutorialText.setStrokeWidth(2);
        tutorialText.setFont(Font.font(pixelFont.getFamily(), 25));
        tutorialText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        tutorialText.setWrappingWidth(GameConfig.WINDOW_WIDTH - 100);

        Button backButton = createStyledButton("VOLTAR");
        backButton.setOnAction(e -> start(stage));

        VBox layout = new VBox(35, tutorialText, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        layout.setBackground(createBackground());

        Scene tutorialScene = new Scene(layout, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        stage.setScene(tutorialScene);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font(pixelFont.getFamily(), 16));
        button.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: limegreen;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: limegreen;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: limegreen;" +
                        "-fx-border-color: limegreen;" +
                        "-fx-border-width: 2px;"
        ));
        button.setPrefWidth(220);
        button.setPrefHeight(40);
        return button;
    }

    private Background createBackground() {
        Image bgImg = new Image(getClass().getResourceAsStream("/assets/background_space.png"),
                GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT, false, true);
        return new Background(new BackgroundImage(
                bgImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        ));
    }
}
