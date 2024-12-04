package com.example.demo;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private static final String BACKGROUND_IMAGE_PATH = "/com/example/demo/images/MenuBackground.jpg";
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: #2c3e50;");

        Button startButton = new Button("Start");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");

        // Style buttons
        styleButton(startButton);
        styleButton(optionsButton);
        styleButton(exitButton);

        // Button actions
        startButton.setOnAction(e -> startGame());
        optionsButton.setOnAction(e -> showOptions());
        exitButton.setOnAction(e -> stage.close());


        menuLayout.getChildren().addAll(startButton, optionsButton, exitButton);

        Scene mainMenuScene = new Scene(menuLayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    private void styleButton(Button button) {
        button.setStyle(
                "-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #34495e; -fx-text-fill: white;"
        );
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #1abc9c; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
    }

    private void startGame() {
        try {
            Controller controller = new Controller(stage);
            controller.launchGame(); // Launches the game starting at Level One
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showOptions() {
        System.out.println("Options menu coming soon...");
        // Add code to display an options menu scene if needed
    }
}
