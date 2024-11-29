package com.example.demo.controller;


import com.example.demo.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Sky Battle");
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		// Show the main menu
		MainMenu mainMenu = new MainMenu(stage);
		mainMenu.showMenu();
	}

	public static void main(String[] args) {
		launch();
	}
}
