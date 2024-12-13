package com.example.demo.UI;

import com.example.demo.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * MainMenu represents the main menu of the game where the user can start the game or quit.
 * It contains buttons for starting the game and quitting, and it is responsible for setting up the layout and behavior.
 */
public class MainMenu {

    public static final int SCREEN_WIDTH = 1300;  // The width of the screen
    public static final int SCREEN_HEIGHT = 750;  // The height of the screen
    private final Stage stage;  // The stage on which the menu is displayed

    /**
     * Constructor for MainMenu. Initializes the main menu with the given stage.
     *
     * @param stage The Stage object that will display the main menu scene.
     */
    public MainMenu(Stage stage) {
        this.stage = stage;
    }

    /**
     * Displays the main menu with "Start" and "Quit" buttons.
     * Sets the background image and layout, and binds button actions.
     */
    public void showMenu() {
        VBox menuLayout = createMenuLayout();

        // Create and set up the buttons
        Button startButton = createButton("Start", e -> startGame());
        Button quitButton = createButton("Quit", e -> stage.close());

        // Add buttons to the menu layout
        menuLayout.getChildren().addAll(startButton, quitButton);

        // Create and set the scene for the stage
        Scene mainMenuScene = new Scene(menuLayout, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Creates and styles the layout for the menu, including a background image.
     *
     * @return A VBox container with the main menu layout.
     */
    private VBox createMenuLayout() {
        VBox menuLayout = new VBox(20);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 20; " +
                "-fx-background-image: url('" + getBackgroundImagePath() + "'); " +
                "-fx-background-size: cover;");
        return menuLayout;
    }

    /**
     * Retrieves the path to the background image used in the main menu.
     *
     * @return The path to the background image as a String.
     */
    private String getBackgroundImagePath() {
        return getClass().getResource("/com/example/demo/images/menubackground.jpg").toExternalForm();
    }

    /**
     * Creates a button with the specified text and an action to be performed when clicked.
     *
     * @param text The text displayed on the button.
     * @param action The action handler for the button's click event.
     * @return A configured Button object.
     */
    private Button createButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        styleButton(button);
        button.setOnAction(action);
        return button;
    }

    /**
     * Applies styling to the given button, including font size, padding, and colors.
     * Also sets hover effects for the button.
     *
     * @param button The Button to be styled.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #34495e; -fx-text-fill: white;");

        // Change button style when mouse enters or exits
        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #1abc9c; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 24px; -fx-padding: 20 40; -fx-background-color: #34495e; -fx-text-fill: white;"));
    }

    /**
     * Starts the game by creating a Controller object and calling its launchGame method.
     * This method handles launching the game starting at Level One.
     */
    private void startGame() {
        try {
            Controller controller = new Controller(stage);
            controller.launchGame();  // Launches the game starting at Level One
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
