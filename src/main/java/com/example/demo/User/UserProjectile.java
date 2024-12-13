package com.example.demo.User;

import com.example.demo.Actor.Projectile;

/**
 * The UserProjectile class represents the projectiles fired by the user's plane.
 * It extends the Projectile class and handles the movement and updating of the user's projectiles.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";  // The image used for the projectile
	private static final int IMAGE_HEIGHT = 10;  // The height of the projectile image
	private static final int HORIZONTAL_VELOCITY = 15;  // The horizontal velocity of the projectile

	/**
	 * Constructor for UserProjectile. Initializes the projectile with the specified position.
	 *
	 * @param initialXPos The initial x-coordinate of the projectile.
	 * @param initialYPos The initial y-coordinate of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the projectile by calling updatePosition().
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
