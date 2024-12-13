package com.example.demo.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * GameOverImage is a custom ImageView class that displays the "Game Over" image when the player loses the game.
 * It extends the ImageView class to handle the image rendering and positioning on the screen.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_PATH = "/com/example/demo/images/gameover.png";

	/**
	 * Constructor for the GameOverImage class.
	 * This initializes the image from the specified path and sets its position on the screen.
	 *
	 * @param xPosition The x-coordinate for positioning the game over image on the screen.
	 * @param yPosition The y-coordinate for positioning the game over image on the screen.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		// Load the game over image from the resource path and set it
		setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_PATH)).toExternalForm()));
		// Set the position of the image
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
