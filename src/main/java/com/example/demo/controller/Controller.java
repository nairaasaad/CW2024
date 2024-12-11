package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

import java.lang.reflect.Constructor;
import java.util.Observer;
import java.util.Observable;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "level1.LevelOne";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() {
		stage.show();
		try {
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			showAlert(e);
		}
	}

	private void goToLevel(String className) throws Exception {
		Class<?> levelClass = Class.forName(className);
		Constructor<?> constructor = levelClass.getConstructor(double.class, double.class);
		LevelParent level = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		level.addObserver(this);

		Scene scene = level.initializeScene();
		stage.setScene(scene);

		level.startGame();
	}

	@Override
	public void update(Observable observable, Object levelClassName) {
		try {
			goToLevel((String) levelClassName);
		} catch (Exception e) {
			showAlert(e);
		}
	}

	private void showAlert(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("Error: " + e.getMessage());
		alert.show();
	}
}
