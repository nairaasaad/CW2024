package com.example.demo.Actor;

/**
 * The {@code EnemyPlane} class represents an enemy fighter plane in the game. It extends {@code FighterPlane}
 * and provides functionality for moving the enemy plane, firing projectiles, and updating its state during the game.
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image file name for the enemy plane.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";

	/**
	 * The height of the enemy plane's image.
	 */
	private static final int IMAGE_HEIGHT = 60;

	/**
	 * The horizontal velocity of the enemy plane.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The X position offset for the projectile fired by the enemy plane.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/**
	 * The Y position offset for the projectile fired by the enemy plane.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/**
	 * The initial health of the enemy plane.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of the enemy plane, which determines the probability of firing a projectile.
	 */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an {@code EnemyPlane} object with the specified initial positions for X and Y.
	 *
	 * @param initialXPos the initial horizontal position of the enemy plane
	 * @param initialYPos the initial vertical position of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane if the fire rate condition is met.
	 *
	 * @return a new {@code EnemyProjectile} if the enemy plane fires, otherwise {@code null}
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPos = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPos = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPos, projectileYPos);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane by updating its position and firing projectiles if necessary.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
