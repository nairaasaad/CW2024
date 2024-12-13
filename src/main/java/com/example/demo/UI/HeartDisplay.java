package com.example.demo.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.Objects;

/**
 * HeartDisplay is a class that manages the display of hearts (representing health) in the game.
 * It uses an HBox container to arrange heart images horizontally and provides methods to add or remove hearts.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";  // Path to the heart image
	private static final int HEART_HEIGHT = 50;  // The height of each heart image
	private static final int INDEX_OF_FIRST_ITEM = 0;  // Index used to remove hearts
	private HBox container;  // HBox container to hold the heart images
	private final double containerXPosition;  // X-coordinate for the heart container position
	private final double containerYPosition;  // Y-coordinate for the heart container position
	private final int numberOfHeartsToDisplay;  // The number of hearts to initially display

	/**
	 * Constructor for HeartDisplay. Initializes the container and populates it with the specified number of heart images.
	 *
	 * @param xPosition The x-coordinate for positioning the heart container on the screen.
	 * @param yPosition The y-coordinate for positioning the heart container on the screen.
	 * @param heartsToDisplay The initial number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) that will hold the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();  // Create a new HBox to hold the heart images
		container.setLayoutX(containerXPosition);  // Set the X position of the container
		container.setLayoutY(containerYPosition);  // Set the Y position of the container
	}

	/**
	 * Initializes the heart images based on the number specified in the constructor.
	 * The heart images are loaded and added to the container.
	 * If the image fails to load, a fallback image will be used.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			Image heartImage = new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm());

			// Ensure the image is loaded correctly
			if (heartImage.isError()) {
				System.err.println("Error loading heart image. Please check the image path.");
				// Set a fallback image if the original image cannot be loaded
				heartImage = new Image("/path/to/default/heart.png");  // Use fallback image
			}

			ImageView heart = new ImageView(heartImage);  // Create an ImageView for each heart
			heart.setFitHeight(HEART_HEIGHT);  // Set the height of the heart image
			heart.setPreserveRatio(true);  // Preserve the aspect ratio of the heart image
			container.getChildren().add(heart);  // Add the heart to the container
		}
	}

	/**
	 * Removes the first heart from the display, typically used to represent a loss of health.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);  // Removes the first heart
		}
	}

	/**
	 * Gets the container (HBox) holding the heart images.
	 *
	 * @return The HBox container containing the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
