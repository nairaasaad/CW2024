package com.example.demo.User;

import com.example.demo.Actor.ActiveActorDestructible;
import com.example.demo.Actor.FighterPlane;
import com.example.demo.User.UserProjectile;

/**
 * The UserPlane class represents the player's plane in the game.
 * It extends the FighterPlane class and manages the player's movement, projectile firing,
 * and tracking the number of kills.
 */
public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";  // The image used for the user plane
	private static final double Y_UPPER_BOUND = -40;  // The upper limit of the vertical movement
	private static final double Y_LOWER_BOUND = 600.0;  // The lower limit of the vertical movement
	private static final double INITIAL_X_POSITION = 5.0;  // The initial x-coordinate of the plane
	private static final double INITIAL_Y_POSITION = 300.0;  // The initial y-coordinate of the plane
	private static final int IMAGE_HEIGHT = 50;  // The height of the plane's image
	private static final int VERTICAL_VELOCITY = 8;  // The vertical velocity of the plane
	private static final int HORIZONTAL_VELOCITY = 8;  // The horizontal velocity of the plane
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;  // The vertical offset for the projectile's starting position

	private int verticalVelocityMultiplier;  // Multiplier for vertical movement speed
	private int horizontalVelocityMultiplier;  // Multiplier for horizontal movement speed
	private int numberOfKills;  // The count of kills made by the player

	/**
	 * Constructor for UserPlane. Initializes the user plane with the given health.
	 *
	 * @param initialHealth The initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on its movement direction and speed.
	 * It also ensures that the plane stays within the defined vertical boundaries.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the user plane's state by calling updatePosition().
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return The projectile fired by the user plane.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		double PROJECTILE_X_POSITION = getLayoutX() + getTranslateX() + 110;
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the plane is moving (either vertically or horizontally).
	 *
	 * @return true if the plane is moving, false otherwise.
	 */
	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	/**
	 * Moves the plane upwards by setting the vertical velocity multiplier to -1.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the plane downwards by setting the vertical velocity multiplier to 1.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane forward by setting the horizontal velocity multiplier to 1.
	 */
	public void moveForward() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Moves the plane backwards by setting the horizontal velocity multiplier to -1.
	 */
	public void moveBackward() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Stops the vertical movement of the plane by setting the vertical velocity multiplier to 0.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops the horizontal movement of the plane by setting the horizontal velocity multiplier to 0.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills made by the user plane.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count of the user plane.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}
