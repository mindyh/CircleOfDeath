public class Model {
	Controller controller;
	ArrayList<ArrayList<GPoint>> allPaths = new ArrayList<ArrayList<GPoint>>();
	ArrayList<GPoint> currentPath;
	
	public Model(Controller controller) {
		this.controller = controller;
	}
	
	public void update() {
		// Move objects to new locations
	}
}