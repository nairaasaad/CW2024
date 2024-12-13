package com.example.demo.Levels;

import com.example.demo.UI.ShieldImage;
import javafx.scene.Group;

/**
 * LevelViewLevelTwo is a subclass of LevelView that manages the specific visual elements for level two,
 * such as adding the shield image for the boss unit.
 */
public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructor for LevelViewLevelTwo. Initializes the level view with the specified root and hearts to display,
	 * and adds level-specific images such as the shield image.
	 *
	 * @param root The root Group that holds all the game elements for this level.
	 * @param heartsToDisplay The initial number of hearts to display on the screen.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay); // Call the constructor of the parent class (LevelView)
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot(); // Add level-specific images (e.g., shield) to the root
	}

	/**
	 * Adds all the level-specific images (such as the shield image) to the root Group.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage); // Add the shield image to the root container
		System.out.println("shield added"); // Debugging output
	}
}
