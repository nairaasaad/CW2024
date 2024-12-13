package com.example.demo.Levels;

import java.util.*;

import com.example.demo.Actor.FighterPlane;
import com.example.demo.User.UserPlane;
import com.example.demo.Actor.ActiveActorDestructible;
import com.example.demo.UI.PauseMenu;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * Abstract class representing a level in the game.
 * This class manages the game loop, actors, collisions, and level transitions.
 */
public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private final LevelView levelView;
	private boolean levelCompleted = false;

	/**
	 * Constructor for initializing a level.
	 *
	 * @param backgroundImageName The background image for the level.
	 * @param screenHeight Height of the screen.
	 * @param screenWidth Width of the screen.
	 * @param playerInitialHealth The initial health of the player's plane.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
		PauseMenu pauseMenu = new PauseMenu(root, scene);
		pauseMenu.initializePauseHandler(this);
	}

	/**
	 * Abstract method to initialize friendly units (e.g., user and allies).
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the level view (UI elements).
	 *
	 * @return A LevelView instance.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * @return true if the level is completed, false otherwise.
	 */
	protected boolean isLevelCompleted() {
		return levelCompleted;
	}

	/**
	 * Marks the level as completed.
	 */
	protected void setLevelCompleted() {
		this.levelCompleted = true;
	}

	/**
	 * Initializes the scene for the level.
	 *
	 * @return The initialized Scene object.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by playing the timeline.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Stops the game timeline.
	 */
	public void stopTimeline() {
		timeline.stop();
	}

	/**
	 * Starts the timeline for the game.
	 */
	public void startTimeline() {
		timeline.play();
	}

	/**
	 * Notifies observers to move to the next level.
	 *
	 * @param levelName The name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		if (!isLevelCompleted()) {
			setChanged();
			notifyObservers(levelName);
		}
	}

	/**
	 * Updates the scene every frame (handles game logic).
	 */
	protected void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the timeline with a game loop that updates the scene.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image and key event handlers.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(createKeyPressHandler());
		background.setOnKeyReleased(createKeyReleaseHandler());
		root.getChildren().add(background);
	}

	/**
	 * Creates a key press handler for controlling the user plane.
	 *
	 * @return EventHandler for key press events.
	 */
	private EventHandler<KeyEvent> createKeyPressHandler() {
		return e -> {
			KeyCode kc = e.getCode();
			if (kc == KeyCode.UP) user.moveUp();
			if (kc == KeyCode.DOWN) user.moveDown();
			if (kc == KeyCode.SPACE) fireProjectile();
			if (kc == KeyCode.RIGHT) user.moveForward();
			if (kc == KeyCode.LEFT) user.moveBackward();
		};
	}

	/**
	 * Creates a key release handler for controlling the user plane.
	 *
	 * @return EventHandler for key release events.
	 */
	private EventHandler<KeyEvent> createKeyReleaseHandler() {
		return e -> {
			KeyCode kc = e.getCode();
			if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stopVerticalMovement();
			if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.stopHorizontalMovement();
		};
	}

	/**
	 * Fires a projectile from the user's plane and adds it to the scene.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy projectiles by having each enemy fire one.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the scene.
	 *
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates all actors (planes, projectiles, etc.) each frame.
	 */
	private void updateActors() {
		friendlyUnits.forEach(ActiveActorDestructible::updateActor);
		enemyUnits.forEach(ActiveActorDestructible::updateActor);
		userProjectiles.forEach(ActiveActorDestructible::updateActor);
		enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
	}

	/**
	 * Removes all destroyed actors from the scene and updates the lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the provided list and the scene.
	 *
	 * @param actors The list of actors to check and remove.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(ActiveActorDestructible::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between planes (both friendly and enemy).
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles generic collisions between two sets of actors.
	 *
	 * @param actors1 The first set of actors.
	 * @param actors2 The second set of actors.
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		actors2.forEach(actor -> actors1.stream()
				.filter(otherActor -> !otherActor.isDestroyed() && actor.getBoundsInParent().intersects(otherActor.getBoundsInParent()))
				.forEach(otherActor -> {
					actor.takeDamage();
					otherActor.takeDamage();
				}));
	}

	/**
	 * Handles cases where enemy units penetrate the user plane's defenses.
	 */
	private void handleEnemyPenetration() {
		enemyUnits.forEach(enemy -> {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		});
	}

	/**
	 * Updates the level view UI (e.g., health display).
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count based on the number of enemies destroyed.
	 */
	private void updateKillCount() {
		int killedEnemies = currentNumberOfEnemies - enemyUnits.size();
		for (int i = 0; i < killedEnemies; i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated the defenses (i.e., moved past the user plane).
	 *
	 * @param enemy The enemy to check.
	 * @return true if the enemy has penetrated the defenses, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Handles the scenario where the player wins the game.
	 * Stops the game timeline and displays the win screen.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Handles the scenario where the player loses the game.
	 * Stops the game timeline and displays the game over screen.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * @return The user plane (player's plane).
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * @return The root group for the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * @return The current number of active enemies in the level.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the level.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * @return The maximum Y position for enemy units on the screen.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @return true if the user plane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the current number of enemies.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/**
	 * Checks if a new enemy plane overlaps with any existing enemy.
	 *
	 * @param newEnemy The new enemy to check.
	 * @return true if there is an overlap, false otherwise.
	 */
	protected boolean isEnemyPlaneOverlapping(ActiveActorDestructible newEnemy) {
		return enemyUnits.stream().anyMatch(enemy -> newEnemy.getBoundsInParent().intersects(enemy.getBoundsInParent()));
	}
}
