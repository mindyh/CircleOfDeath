public Bot extends GImage {
	boolean alive;
	GPoint lastLocation;
	
	public Bot(Image im) {
		this.location = location;
		alive = true;
	}
	
	public void move(double dx, double dy) {
		lastLocation = this.getLocation();
		super.move(dx, dy);
	}
	
	public void movePolar(double r, double theta) {
		lastLocation = this.getLocation();
		super.movePolar(r, theta);
	}
	
	public void paint(Graphics g) {
		// Do some affine transforms
		// Draw image
	}
}