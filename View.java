import javax.swing.*;

public class View extends JPanel {
	Controller controller;

	public View(Controller controller) {
		this.controller = controller;
		this.controller.frame = this;
		addMouseListener(this.controller);
	}
	
	public void update() {
		// Redraw modified objects
	}
}