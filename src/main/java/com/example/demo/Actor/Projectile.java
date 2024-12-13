package com.example.demo.Actor;

/**
 * The {@code Projectile} class represents a projectile in the game, such as bullets or fireballs.
 * This class extends {@code ActiveActorDestructible} and provides functionality for movement and destruction of the projectile.
 * <p>
 * Projectiles are objects that can be fired by different actors in the game. This class handles the destruction of the projectile
 * when it takes damage and provides an abstract method for updating its position, which is to be implemented by subclasses.
 * </p>
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a {@code Projectile} with the specified image name, height, and initial position.
	 *
	 * @param imageName the name of the image representing the projectile
	 * @param imageHeight the height of the image representing the projectile
	 * @param initialXPos the initial horizontal position of the projectile
	 * @param initialYPos the initial vertical position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the destruction of the projectile when it takes damage. This implementation calls the
	 * {@code destroy()} method, removing the projectile from the game.
	 */
	@Override
	public void takeDamage() {
		destroy();
	}

	/**
	 * Updates the position of the projectile. This method is abstract and should be implemented by subclasses
	 * to define the specific movement behavior of the projectile.
	 */
	@Override
	public abstract void updatePosition();
}
