package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PauseMenu {

    private final Group root;
    private final Scene scene;
    private boolean isPaused = false;

    // Dim background and text components for pause screen
    private final Rectangle dimBackground;
    private final Text pauseText;
    private final StackPane pauseOverlay;

    public PauseMenu(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;

        dimBackground = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        dimBackground.setOpacity(0.5); // Adjust opacity for dim effect

        pauseText = new Text("Press again P to Unpause");
        pauseText.setFont(Font.font("Arial", 36));
        pauseText.setFill(Color.WHITE);


        pauseOverlay = new StackPane(dimBackground, pauseText);
        pauseOverlay.setVisible(false); // Initially invisible
    }

    public void initializePauseHandler(LevelParent levelParent) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case P:
                    if (isPaused) {
                        unpauseGame(levelParent);
                    } else {
                        pauseGame(levelParent);
                    }
                    break;
                default:
                    break;
            }
        });
    }

    private void pauseGame(LevelParent levelParent) {
        isPaused = true;
        levelParent.stopTimeline();
        root.getChildren().add(pauseOverlay);
        pauseOverlay.setVisible(true);
    }

    private void unpauseGame(LevelParent levelParent) {
        isPaused = false;
        levelParent.startTimeline();
        root.getChildren().remove(pauseOverlay);
        pauseOverlay.setVisible(false);
    }
}
