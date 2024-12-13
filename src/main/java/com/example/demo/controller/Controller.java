package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;

import java.util.Observable;
import java.util.Observer;

/**
 * The {@code Controller} class is responsible for managing the game flow, including loading and transitioning between levels.
 * This class implements the {@code Observer} interface to respond to changes in the game state and update the scene accordingly.
 * <p>
 * The controller interacts with the game window (represented by a {@code Stage}) to display the appropriate scenes and levels.
 * It handles loading a specified level, starting the game, and reacting to level transitions.
 * </p>
 */
public class Controller implements Observer {

	/**
	 * The class name of the first level in the game.
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";

	/**
	 * The primary stage of the game where scenes are displayed.
	 */
	private final Stage stage;

	/**
	 * Constructs a {@code Controller} with the specified game stage.
	 *
	 * @param stage the stage for displaying scenes in the game
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by displaying the primary stage and loading the first level.
	 * If an error occurs while loading the level, an error alert is displayed.
	 */
	public void launchGame() {
		stage.show();
		try {
			loadLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			displayErrorAlert(e);
		}
	}

	/**
	 * Loads a specified level by dynamically loading its class, creating an instance of the level,
	 * initializing the scene, and starting the game.
	 *
	 * @param className the fully qualified name of the level class to load
	 * @throws Exception if an error occurs during the level loading process
	 */
	private void loadLevel(String className) throws Exception {
		Class<?> levelClass = Class.forName(className);
		LevelParent level = (LevelParent) levelClass
				.getConstructor(double.class, double.class)
				.newInstance(stage.getHeight(), stage.getWidth());

		level.addObserver(this);
		Scene scene = level.initializeScene();
		stage.setScene(scene);
		level.startGame();
	}

	/**
	 * Called when the observed object (a level) notifies the controller of a change.
	 * If the level indicates a transition to a new level, this method loads the next level.
	 *
	 * @param observable the observable object (the current level)
	 * @param levelClassName the name of the next level class to load
	 */
	@Override
	public void update(Observable observable, Object levelClassName) {
		if (levelClassName instanceof String nextLevelName) {
			try {
				loadLevel(nextLevelName);
			} catch (Exception e) {
				displayErrorAlert(e);
			}
		}
	}

	/**
	 * Displays an error alert with the given exception's message.
	 *
	 * @param e the exception that caused the error
	 */
	private void displayErrorAlert(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occurred");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}
}
