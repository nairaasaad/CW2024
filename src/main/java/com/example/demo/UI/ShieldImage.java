package com.example.demo.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ShieldImage is a class that represents the shield image displayed in the game.
 * It extends ImageView to display the shield image at a specific position and control its visibility.
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";  // Path to the shield image
	private static final int SHIELD_SIZE = 100;  // The size of the shield image (both width and height)

	/**
	 * Constructor for ShieldImage. Initializes the position, image, and visibility of the shield.
	 *
	 * @param xPosition The x-coordinate of the shield image's position on the screen.
	 * @param yPosition The y-coordinate of the shield image's position on the screen.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);  // Set the x-coordinate for the shield's position
		this.setLayoutY(yPosition);  // Set the y-coordinate for the shield's position

		// Log the image path for debugging
		System.out.println("Image path: " + getClass().getResource(IMAGE_NAME));

		// Attempt to load the image from the specified path and set it to the ImageView
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially, the shield is not visible
		this.setFitHeight(SHIELD_SIZE);  // Set the height of the shield image
		this.setFitWidth(SHIELD_SIZE);   // Set the width of the shield image
	}

	/**
	 * Makes the shield image visible on the screen.
	 * This method is called when the shield is activated or should be shown.
	 */
	public void showShield() {
		System.out.println("shield is visible!");  // Debugging message
		this.setVisible(true);  // Make the shield visible
	}

	/**
	 * Hides the shield image from the screen.
	 * This method is called when the shield is deactivated or should be hidden.
	 */
	public void hideShield() {
		System.out.println("shield is invisible!");  // Debugging message
		this.setVisible(false);  // Hide the shield
	}
}
