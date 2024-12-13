package com.example.demo.Levels;

import com.example.demo.Actor.Boss;

/**
 * LevelTwo class represents the second level of the game, featuring a boss battle.
 * It extends the abstract LevelParent class and manages specific game mechanics
 * and interactions for this level, including the boss unit.
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.gif";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;

    /**
	 * Constructor for LevelTwo. Initializes the level with the specified screen dimensions
	 * and sets up the boss unit for this level.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss(); // Initialize the boss for this level
	}

	/**
	 * Initializes the friendly units for this level, which currently includes only the user plane.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser()); // Add user plane to the game root
	}

	/**
	 * Checks if the game is over, either by the user plane being destroyed or the boss being defeated.
	 * If the game is over, triggers the corresponding win or lose condition.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame(); // User plane destroyed, game over
		} else if (boss.isDestroyed()) {
			winGame(); // Boss defeated, user wins
		}
	}

	/**
	 * Spawns enemy units for this level. Specifically, spawns the boss if no other enemies are present.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss); // Add boss as the enemy unit
			getRoot().getChildren().add(boss.getShieldImage()); // Add boss shield image to the root
		}
	}

	/**
	 * Instantiates the LevelView for this level, which is specific to LevelTwo.
	 *
	 * @return The LevelView for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
        LevelViewLevelTwo levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}
}
