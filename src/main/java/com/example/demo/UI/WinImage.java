package com.example.demo.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * WinImage is a class that represents the "You Win" image displayed when the player wins the game.
 * It extends ImageView to display the win image at a specific position and manage its visibility.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";  // Path to the "You Win" image
	private static final int HEIGHT = 500;  // The height of the win image
	private static final int WIDTH = 600;   // The width of the win image

	/**
	 * Constructor for WinImage. Initializes the image, size, position, and visibility of the win image.
	 *
	 * @param xPosition The x-coordinate of the win image's position on the screen.
	 * @param yPosition The y-coordinate of the win image's position on the screen.
	 */
	public WinImage(double xPosition, double yPosition) {
		// Set the image using the specified path
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially, the win image is not visible
		this.setFitHeight(HEIGHT);  // Set the height of the win image
		this.setFitWidth(WIDTH);    // Set the width of the win image
		this.setLayoutX(xPosition);  // Set the x-coordinate of the win image
		this.setLayoutY(yPosition);  // Set the y-coordinate of the win image
	}

	/**
	 * Makes the win image visible on the screen.
	 * This method is called when the player wins the game and the "You Win" image should be displayed.
	 */
	public void showWinImage() {
		this.setVisible(true);  // Make the win image visible
	}
}
