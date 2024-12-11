package level2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 100;
	
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		//this.setImage(new Image(IMAGE_NAME));
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);

	}

	public void showShield() {
		System.out.println("shield is visible!");
		this.setVisible(true);

	}

	
	public void hideShield() {
		System.out.println("shield is invisible!");
		this.setVisible(false);
	}

}
