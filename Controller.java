import javax.swing.*;
import javax.swing.Timer;
import java.awt.event;

public class Controller implements ActionListener, MouseListener
{
	private JFrame frame;
	private Model model;
	private View view;

	public Controller(JFrame frame) {
		this.frame = frame;
		model = new Model(this);
		view = new View(this);
		timer = new Timer(0); // Continuous refreshing, or near-continuous
		timer.start(); // Fires actionEvent objects
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			model.update();
			view.update();
		}
	}
	
		
	public void mouseReleased(MouseEvent e) {
		model.allPaths.add(model.currentPath);
	}
	
	public void mouseDragged(MouseEvent e) {
		x1 = x;
		y1 = y;

		x = e.getX();
		y = e.getY();

		GPoint loc = new GPoint(x,y);
		model.currentPath.add(loc);

		addLine();
		pause(WAIT);
	}
	
	public void mousePressed(MouseEvent e) {
		currentPath = new ArrayList<GPoint>();
	}
}