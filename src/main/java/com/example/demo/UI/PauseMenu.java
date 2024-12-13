package com.example.demo.UI;

import com.example.demo.Levels.LevelParent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * PauseMenu is a class that manages the pause menu overlay in the game.
 * It provides functionality to pause and unpause the game when the user presses the "P" key.
 * When paused, the game is dimmed, and a pause message is shown.
 */
public class PauseMenu {

    private final Group root;  // The root group of the scene where the pause menu will be displayed
    private final Scene scene;  // The scene where the pause menu is applied
    private boolean isPaused = false;  // Flag to track the pause state of the game

    private final Rectangle dimBackground;  // The semi-transparent background that dims the game when paused
    private final Text pauseText;  // The text message displayed on the pause menu
    private final StackPane pauseOverlay;  // The StackPane that holds the dim background and the pause message

    /**
     * Constructor for the PauseMenu class. Initializes the dim background, pause text, and overlay.
     * Initially, the pause overlay is set to be invisible.
     *
     * @param root The root group of the scene, where the pause menu will be displayed.
     * @param scene The scene where the pause functionality will be applied.
     */
    public PauseMenu(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;

        dimBackground = createDimBackground();
        pauseText = createPauseText();
        pauseOverlay = createPauseOverlay();

        pauseOverlay.setVisible(false); // Initially invisible
    }

    /**
     * Initializes the key handler to toggle pause and unpause when the "P" key is pressed.
     *
     * @param levelParent The LevelParent instance to control the game timeline (pausing and unpausing).
     */
    public void initializePauseHandler(LevelParent levelParent) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.P) {
                togglePause(levelParent);
            }
        });
    }

    /**
     * Toggles the pause state. If the game is paused, it unpauses it, and if it's running, it pauses it.
     *
     * @param levelParent The LevelParent instance to control the game timeline (pausing and unpausing).
     */
    private void togglePause(LevelParent levelParent) {
        if (isPaused) {
            unpauseGame(levelParent);
        } else {
            pauseGame(levelParent);
        }
    }

    /**
     * Pauses the game by stopping the game timeline, displaying the pause overlay, and dimming the background.
     *
     * @param levelParent The LevelParent instance to stop the game timeline.
     */
    private void pauseGame(LevelParent levelParent) {
        isPaused = true;
        levelParent.stopTimeline();  // Stop the game timeline to freeze the game
        root.getChildren().add(pauseOverlay);  // Add the pause overlay to the scene
        pauseOverlay.setVisible(true);  // Make the pause overlay visible
    }

    /**
     * Unpauses the game by starting the game timeline, removing the pause overlay, and hiding it.
     *
     * @param levelParent The LevelParent instance to start the game timeline.
     */
    private void unpauseGame(LevelParent levelParent) {
        isPaused = false;
        levelParent.startTimeline();  // Restart the game timeline to resume the game
        root.getChildren().remove(pauseOverlay);  // Remove the pause overlay from the scene
        pauseOverlay.setVisible(false);  // Hide the pause overlay
    }

    /**
     * Creates the dim background that will cover the entire screen when the game is paused.
     *
     * @return A Rectangle representing the dimmed background.
     */
    private Rectangle createDimBackground() {
        Rectangle background = new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK);
        background.setOpacity(0.5);  // Set the opacity to 50% to create a dim effect
        return background;
    }

    /**
     * Creates the pause text that instructs the user to press "P" again to unpause the game.
     *
     * @return A Text object containing the pause message.
     */
    private Text createPauseText() {
        Text text = new Text("Press again P to Unpause");
        text.setFont(Font.font("Arial", 36));  // Set the font and size for the text
        text.setFill(Color.WHITE);  // Set the text color to white
        return text;
    }

    /**
     * Creates the StackPane that will hold the dim background and pause text.
     *
     * @return A StackPane containing the dim background and pause text.
     */
    private StackPane createPauseOverlay() {
        return new StackPane(dimBackground, pauseText);  // Stack the dim background and text on top of each other
    }
}
