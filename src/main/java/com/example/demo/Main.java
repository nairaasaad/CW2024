package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.demo.UI.MainMenu;

/**
 * The Main class is the entry point for the Sky Battle game application.
 * It extends the Application class from JavaFX and manages the initialization of the game stage and the main menu.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";

	/**
	 * This method is called when the application is launched.
	 * It configures the stage and displays the main menu.
	 *
	 * @param stage The primary stage for this application, onto which scenes will be placed.
	 */
	@Override
	public void start(Stage stage) {
		configureStage(stage);  // Configures stage settings like title, size, and resizability.
		showMainMenu(stage);    // Displays the main menu of the game.
	}

	/**
	 * Configures the stage with the title, size, and resizability settings.
	 *
	 * @param stage The primary stage for this application.
	 */
	private void configureStage(Stage stage) {
		stage.setTitle(TITLE);              // Sets the title of the game window.
		stage.setResizable(false);          // Prevents resizing of the window.
		stage.setWidth(SCREEN_WIDTH);       // Sets the width of the window.
		stage.setHeight(SCREEN_HEIGHT);     // Sets the height of the window.
	}

	/**
	 * Displays the main menu of the game.
	 *
	 * @param stage The primary stage for this application.
	 */
	private void showMainMenu(Stage stage) {
		MainMenu mainMenu = new MainMenu(stage);  // Creates an instance of the MainMenu.
		mainMenu.showMenu();                     // Displays the main menu on the stage.
	}

	/**
	 * The main method launches the JavaFX application.
	 *
	 * @param args Command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		launch();  // Launches the JavaFX application.
	}
}
