package level2;
import com.example.demo.Actor.ActiveActorDestructible;
import com.example.demo.FighterPlane;
import java.util.*;

public class Boss extends FighterPlane {

	private final ShieldImage shieldImage;
	private static final double BOSS_SHIELD_PROBABILITY = .005;
	private static final int SHIELD_X_POSITION_OFFSET = 80;
	public Boss() {
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		shieldImage = new ShieldImage(INITIAL_X_POSITION - SHIELD_X_POSITION_OFFSET, INITIAL_Y_POSITION);
		initializeMovePattern();
	}
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (Math.random() < 0.001) { // 0.1% chance to deactivate the shield
				deactivateShield();
			} else {
				shieldImage.showShield();
			}
		} else {
			if (shieldShouldBeActivated()) {
				activateShield();
			}
		}

		// Ensure the shield deactivates after MAX_FRAMES_WITH_SHIELD
		if (framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD) {
			deactivateShield();
		}
	}
		isShielded = true;
	public ShieldImage getshieldImage() {
		return shieldImage;
		isShielded = false;
