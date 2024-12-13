package com.example.demo.Actor;

import com.example.demo.UI.ShieldImage;

import java.util.*;

/**
 * The {@code Boss} class represents a powerful boss fighter plane in the game.
 * This class extends {@code FighterPlane} and adds additional functionality for movement patterns,
 * firing projectiles, and activating/deactivating shields. The boss can also take damage,
 * with the shield preventing damage during activation.
 */
public class Boss extends FighterPlane {

	/**
	 * The image file name for the boss plane.
	 */
	private static final String IMAGE_NAME = "bossplane.png";

	/**
	 * The initial horizontal position of the boss plane.
	 */
	private static final double INITIAL_X_POSITION = 1000.0;

	/**
	 * The initial vertical position of the boss plane.
	 */
	private static final double INITIAL_Y_POSITION = 400;

	/**
	 * The vertical offset for the boss projectile's Y position.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/**
	 * The fire rate of the boss plane's projectiles.
	 */
	private static final double BOSS_FIRE_RATE = 0.05;

	/**
	 * The height of the boss plane's image.
	 */
	private static final int IMAGE_HEIGHT = 70;

	/**
	 * The vertical velocity (movement speed) of the boss plane.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The health of the boss plane.
	 */
	private static final int HEALTH = 20;

	/**
	 * The frequency of moves per cycle.
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/**
	 * A constant representing zero movement.
	 */
	private static final int ZERO = 0;

	/**
	 * The maximum number of frames in which the boss moves in the same direction.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * The upper vertical bound for the boss plane's movement.
	 */
	private static final int Y_POSITION_UPPER_BOUND = -100;

	/**
	 * The lower vertical bound for the boss plane's movement.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 450;

	/**
	 * The maximum number of frames during which the shield can remain active.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	/**
	 * The probability of the boss plane activating its shield each frame.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.005;

	/**
	 * The horizontal offset of the shield image relative to the boss plane.
	 */
	private static final int SHIELD_X_POSITION_OFFSET = 80;

	/**
	 * The probability that the shield will deactivate during its activation period.
	 */
	private static final double SHIELD_DEACTIVATION_CHANCE = 0.001;

	/**
	 * A list representing the movement pattern of the boss plane.
	 */
	private final List<Integer> movePattern;

	/**
	 * The shield image associated with the boss plane.
	 */
	private final ShieldImage shieldImage;

	/**
	 * A flag indicating whether the boss plane is currently shielded.
	 */
	private boolean isShielded;

	/**
	 * The number of consecutive frames the boss has moved in the same direction.
	 */
	private int consecutiveMovesInSameDirection;

	/**
	 * The index of the current move in the movement pattern.
	 */
	private int indexOfCurrentMove;

	/**
	 * The number of frames the boss's shield has been active.
	 */
	private int framesWithShieldActivated;

	/**
	 * Constructs a {@code Boss} object with predefined attributes such as image, position, health, and movement pattern.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.movePattern = new ArrayList<>();
		this.consecutiveMovesInSameDirection = 0;
		this.indexOfCurrentMove = 0;
		this.framesWithShieldActivated = 0;
		this.isShielded = false;
		this.shieldImage = new ShieldImage(INITIAL_X_POSITION - SHIELD_X_POSITION_OFFSET, INITIAL_Y_POSITION);
		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss plane, adjusting its vertical movement and updating the shield's position.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		shieldImage.setLayoutY(currentPosition);

		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's position and shield state.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the boss plane if the fire rate condition is met.
	 *
	 * @return the newly created projectile if the boss fires, or {@code null} if it doesn't
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Applies damage to the boss plane, unless it is shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the movement pattern for the boss, creating a shuffled list of vertical movements.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield state, either activating or deactivating it based on probabilities and frame count.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (shouldDeactivateShield()) {
				deactivateShield();
			} else {
				shieldImage.showShield();
			}
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}

		if (framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD) {
			deactivateShield();
		}
	}

	/**
	 * Determines if the shield should deactivate based on a random chance.
	 *
	 * @return {@code true} if the shield should deactivate, {@code false} otherwise
	 */
	private boolean shouldDeactivateShield() {
		return Math.random() < SHIELD_DEACTIVATION_CHANCE;
	}

	/**
	 * Gets the next vertical move from the move pattern, ensuring the boss moves according to its pattern.
	 *
	 * @return the next vertical movement value
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;

		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}

		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}

		return currentMove;
	}

	/**
	 * Determines whether the boss should fire a projectile in the current frame based on its fire rate.
	 *
	 * @return {@code true} if the boss fires a projectile, {@code false} otherwise
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the initial Y position for the projectile to be fired.
	 *
	 * @return the Y position where the projectile should be fired
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the boss should activate its shield based on a random chance.
	 *
	 * @return {@code true} if the shield should be activated, {@code false} otherwise
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Activates the boss's shield, making it invulnerable to damage.
	 */
	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
	}

	/**
	 * Deactivates the boss's shield and resets the shield frame count.
	 */
	private void deactivateShield() {
		isShielded = false;
		shieldImage.hideShield();
		framesWithShieldActivated = 0;
	}

	/**
	 * Gets the shield image associated with the boss plane.
	 *
	 * @return the {@code ShieldImage} object associated with the boss
	 */
	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}
