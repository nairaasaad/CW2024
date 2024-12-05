package com.example.demo;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}

    public static class MainMenu {
        private final Stage stage;

        public MainMenu(Stage stage) {
            this.stage = stage;
        }

        public void showMenu() {
            // Create buttons for the menu
            Button startButton = new Button("Start");
            Button optionsButton = new Button("Options");
            Button exitButton = new Button("Exit");

            // Style buttons
            startButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
            optionsButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
            exitButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");

            // Add action handlers
            startButton.setOnAction(e -> startGame());
            optionsButton.setOnAction(e -> showOptions());
            exitButton.setOnAction(e -> stage.close());

            // Arrange buttons in a vertical layout
            VBox layout = new VBox(20);
            layout.getChildren().addAll(startButton, optionsButton, exitButton);
            layout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: navy;");

            // Set the scene and show the menu
            Scene scene = new Scene(layout, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        }

        private void startGame() {
            try {
                Controller controller = new Controller(stage);
                controller.launchGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void showOptions() {
            System.out.println("Options selected (placeholder).");
        }
    }
}
