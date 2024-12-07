package level2;
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 100;
	public void showShield() {
		System.out.println("shield is visible!");
		this.setVisible(true);

	}
	public void hideShield() {
		System.out.println("shield is invisible!");
		this.setVisible(false);
