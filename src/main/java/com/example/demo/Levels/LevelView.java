package com.example.demo.Levels;

import com.example.demo.UI.GameOverImage;
import com.example.demo.UI.HeartDisplay;
import com.example.demo.UI.WinImage;
import javafx.scene.Group;

/**
 * LevelView is responsible for managing the visual aspects of the game interface during a level.
 * It includes displaying the heart count, win image, and game over screen.
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -50;
	private static final int LOSS_SCREEN_Y_POSITION = -350;

	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructor for LevelView. Initializes the components that manage game visual elements,
	 * such as the heart display, win image, and game over image.
	 *
	 * @param root The root Group that holds all the game elements.
	 * @param heartsToDisplay The initial number of hearts to display on the screen.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(
				HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay
		);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}

	/**
	 * Displays the heart display on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen if it hasn't already been displayed.
	 */
	public void showWinImage() {
		if (!root.getChildren().contains(winImage)) {
			root.getChildren().add(winImage);
			winImage.showWinImage(); // Show the win image
		}
	}

	/**
	 * Displays the game over image on the screen if it hasn't already been displayed.
	 */
	public void showGameOverImage() {
		if (!root.getChildren().contains(gameOverImage)) {
			root.getChildren().add(gameOverImage); // Add the game over image to the root
		}
	}

	/**
	 * Removes hearts from the heart display based on the remaining number of hearts.
	 *
	 * @param heartsRemaining The number of hearts remaining to be displayed.
	 */
	public void removeHearts(int heartsRemaining) {
		int heartsToRemove = heartDisplay.getContainer().getChildren().size() - heartsRemaining;
		for (int i = 0; i < heartsToRemove; i++) {
			heartDisplay.removeHeart(); // Remove hearts from the display
		}
	}
}
