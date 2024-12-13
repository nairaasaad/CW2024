package com.example.demo.Levels;

import com.example.demo.Actor.ActiveActorDestructible;
import com.example.demo.Actor.EnemyPlane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The {@code LevelOne} class represents the first level in the game.
 * It handles the initialization of the level, including spawning enemies, managing the player's progress,
 * checking for win/lose conditions, and transitioning to the next level when the kill target is met.
 */
public class LevelOne extends LevelParent {

	/**
	 * The background image file for level one.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.gif";

	/**
	 * The class name of the next level to load after this one.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelTwo";

	/**
	 * The total number of enemies to spawn in level one.
	 */
	private static final int TOTAL_ENEMIES = 10;

	/**
	 * The number of kills required to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 7;

	/**
	 * The probability of spawning an enemy in each cycle.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .05;

	/**
	 * The initial health of the player in level one.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The text element to display the player's kill count.
	 */
	private Text killCountText;

	/**
	 * Constructs a {@code LevelOne} with the specified screen height and width.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over based on the player's status and progress.
	 * The level ends when the player is destroyed or when the kill target is reached.
	 * If either condition is met, the level transitions or the game ends.
	 */
	@Override
	protected void checkIfGameOver() {
		if (isLevelCompleted()) {
			return; // If the level is already marked as completed, do nothing
		}

		if (userIsDestroyed()) {
			loseGame();
			System.out.println("Game Over!");
			setLevelCompleted(); // Mark level as completed to prevent further updates
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
			System.out.println("Next level: " + NEXT_LEVEL);
			setLevelCompleted(); // Mark level as completed to prevent further updates
		}
	}

	/**
	 * Initializes the player's friendly units (such as the player's character) for this level.
	 * Also initializes the kill count text that displays the player's progress.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		initializeKillCountText(); // Initialize the kill counter text
	}

	/**
	 * Spawns enemy units based on the defined probability and limits.
	 * Ensures that the number of spawned enemies does not exceed the total enemies available for the level.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		int enemiesToSpawn = Math.min(10 - currentNumberOfEnemies, TOTAL_ENEMIES - currentNumberOfEnemies);
		if (currentNumberOfEnemies < 5) {
			enemiesToSpawn = Math.min(5 - currentNumberOfEnemies, TOTAL_ENEMIES - currentNumberOfEnemies);
		}
		for (int i = 0; i < enemiesToSpawn; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				if (!isEnemyPlaneOverlapping(newEnemy)) {
					addEnemyUnit(newEnemy);
				} else {
					i--;
				}
			}
		}
	}

	/**
	 * Instantiates the {@code LevelView} for this level.
	 *
	 * @return the level view for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the player has reached the kill target to advance to the next level.
	 *
	 * @return {@code true} if the player has reached the required number of kills, {@code false} otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Initializes the kill count text that will be displayed on the screen.
	 */
	private void initializeKillCountText() {
		killCountText = new Text();
		killCountText.setFont(new Font("Impact", 24));
		killCountText.setStyle("-fx-fill: black;");  // Set the fill color to black
		killCountText.setStrokeWidth(1);  // Set the stroke width
		killCountText.setStroke(javafx.scene.paint.Color.WHITE);  // Set the stroke (border) color to white
		killCountText.setLayoutX(1200);
		killCountText.setLayoutY(40);
		getRoot().getChildren().add(killCountText);
	}

	/**
	 * Updates the kill count text based on the player's number of kills.
	 */
	private void updateKillCountText() {
		killCountText.setText("Kills: " + getUser().getNumberOfKills());
	}

	/**
	 * Updates the scene by calling the superclass method and updating the kill count text.
	 */
	@Override
	protected void updateScene() {
		super.updateScene();
		updateKillCountText(); // Update the kill count text on each scene update
	}
}
