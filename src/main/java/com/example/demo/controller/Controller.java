package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.Levels.LevelParent;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() {
		stage.show();
		try {
			loadLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			displayErrorAlert(e);
		}
	}

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

	private void displayErrorAlert(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occurred");
		alert.setContentText(e.getMessage());
		alert.showAndWait();
	}
}
