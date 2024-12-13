package com.example.demo.Actor;

/**
 * The {@code BossProjectile} class represents a projectile fired by the boss plane.
 * This class extends {@code Projectile} and provides functionality for the movement of the projectile
 * and updates to its state during the game.
 * <p>
 * The {@code BossProjectile} moves horizontally at a constant velocity and updates its position
 * as it moves across the screen. The projectile is fired from the boss plane at a fixed horizontal position,
 * and its vertical position is passed when the projectile is created.
 * </p>
 */
public class BossProjectile extends Projectile {

	/**
	 * The image file name for the boss projectile.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the boss projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the boss projectile, which controls how fast the projectile moves.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial horizontal position of the boss projectile when it is fired.
	 * This is a fixed value where the projectile starts.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a {@code BossProjectile} with the specified initial vertical position.
	 * The horizontal position is fixed, and the vertical position is provided as an argument.
	 *
	 * @param initialYPos the initial vertical position of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the boss projectile by moving it horizontally at a constant velocity.
	 * The projectile moves towards the left of the screen.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the boss projectile by updating its position.
	 * This method is called to ensure that the projectile moves across the screen.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
