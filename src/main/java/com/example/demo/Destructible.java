package com.example.demo;

/**
 * The Destructible interface represents objects that can take damage and be destroyed.
 * Any class implementing this interface must provide behavior for taking damage and being destroyed.
 */
public interface Destructible {

	/**
	 * Method to handle the object taking damage.
	 * This method should define what happens when the object is damaged (e.g., health reduction).
	 */
	void takeDamage();

	/**
	 * Method to handle the destruction of the object.
	 * This method should define what happens when the object is destroyed (e.g., removal from the game).
	 */
	void destroy();
}
