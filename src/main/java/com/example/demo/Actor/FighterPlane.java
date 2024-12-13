package com.example.demo.Actor;

import com.example.demo.Actor.ActiveActorDestructible;

/**
 * The {@code FighterPlane} class represents a fighter plane in the game. It extends {@code ActiveActorDestructible}
 * and provides functionality for managing health, taking damage, and firing projectiles.
 * <p>
 * The fighter plane has a health attribute that decreases when the plane takes damage. Once the health reaches zero,
 * the plane is destroyed. The class also includes methods to calculate the position of projectiles fired by the plane.
 * </p>
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/**
	 * The current health of the fighter plane.
	 */
	private int health;

	/**
	 * Constructs a {@code FighterPlane} object with the specified image name, height, initial position, and health.
	 *
	 * @param imageName the name of the image representing the fighter plane
	 * @param imageHeight the height of the image representing the fighter plane
	 * @param initialXPos the initial horizontal position of the fighter plane
	 * @param initialYPos the initial vertical position of the fighter plane
	 * @param health the initial health of the fighter plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane. This method is abstract and must be implemented by subclasses
	 * to define the specific projectile behavior.
	 *
	 * @return the projectile fired by the fighter plane
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the fighter plane's health by one when it takes damage.
	 * If health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (health == 0) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for firing a projectile, taking into account the plane's current position and
	 * an additional horizontal offset.
	 *
	 * @param xPositionOffset the horizontal offset to adjust the projectile's X position
	 * @return the calculated X position for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for firing a projectile, taking into account the plane's current position and
	 * an additional vertical offset.
	 *
	 * @param yPositionOffset the vertical offset to adjust the projectile's Y position
	 * @return the calculated Y position for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return the current health of the fighter plane
	 */
	public int getHealth() {
		return health;
	}
}
