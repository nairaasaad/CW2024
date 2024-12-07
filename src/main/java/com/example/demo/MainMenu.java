package com.example.demo;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private final Stage stage;

    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Use JavaFX URL-style path for the background image
        String imagePath = getClass().getResource("/com/example/demo/images/menubackground.jpg").toExternalForm();
        menuLayout.setStyle(
                "-fx-alignment: center; -fx-padding: 20; " +
                        "-fx-background-image: url('" + imagePath + "'); " +
                        "-fx-background-size: cover;"
        );

        Button startButton = new Button("Start");
        Button optionsButton = new Button("Options");
        Button quitButton = new Button("Quit");

        // Style buttons
        styleButton(startButton);
        styleButton(optionsButton);
        styleButton(quitButton);

        // Button actions
        startButton.setOnAction(e -> startGame());
        optionsButton.setOnAction(e -> showOptions());
        quitButton.setOnAction(e -> stage.close());

        menuLayout.getChildren().addAll(startButton, optionsButton, quitButton);

        Scene mainMenuScene = new Scene(menuLayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(mainMenuScene);
        stage.show();
    }


    private void styleButton(Button button) {
        button.setStyle(
                "-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #34495e; -fx-text-fill: white;"
        );


        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #1abc9c; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #34495e; -fx-text-fill: white;"));
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

    }
}
