package com.example.demo.Actor;



/**
 * The {@code EnemyProjectile} class represents a projectile fired by an enemy plane in the game.
 * It extends {@code Projectile} and provides functionality for moving the projectile and updating its state during the game.
 */
public class EnemyProjectile extends Projectile {

	/**
	 * The image file name for the enemy projectile.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * The height of the enemy projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 40;

	/**
	 * The horizontal velocity of the enemy projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an {@code EnemyProjectile} with the specified initial positions for X and Y.
	 *
	 * @param initialXPos the initial horizontal position of the projectile
	 * @param initialYPos the initial vertical position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally according to its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
