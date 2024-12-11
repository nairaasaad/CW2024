	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.gif";
	private static final String NEXT_LEVEL = "level2.LevelTwo";
	private Text killCountText;
	private void initializeKillCountText() {
		killCountText = new Text();
		killCountText.setFont(new Font("Impact", 24));
		killCountText.setStyle("-fx-fill: black;");  // Set the fill color to black
		killCountText.setStrokeWidth(1);  // Set the stroke width
		killCountText.setStroke(javafx.scene.paint.Color.WHITE);  // Set the stroke (border) color to white
		killCountText.setLayoutX(1200);
		killCountText.setLayoutY(40);
		getRoot().getChildren().add(killCountText);
	}
	private void updateKillCountText() {
		killCountText.setText("Kills: " + getUser().getNumberOfKills());
	}
	protected void updateScene() {
		super.updateScene();
		updateKillCountText(); // Update the kill count text on each scene update
