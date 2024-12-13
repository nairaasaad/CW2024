package com.example.demo.Actor;

import com.example.demo.UI.ShieldImage;

import java.util.*;

public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = 0.05;
	private static final int IMAGE_HEIGHT = 70;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 20;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 450;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;
	private static final double BOSS_SHIELD_PROBABILITY = 0.005;
	private static final int SHIELD_X_POSITION_OFFSET = 80;
	private static final double SHIELD_DEACTIVATION_CHANCE = 0.001;

	private final List<Integer> movePattern;
	private final ShieldImage shieldImage;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;

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

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

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

	private boolean shouldDeactivateShield() {
		return Math.random() < SHIELD_DEACTIVATION_CHANCE;
	}

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

	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
	}

	private void deactivateShield() {
		isShielded = false;
		shieldImage.hideShield();
		framesWithShieldActivated = 0;
	}

	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}
