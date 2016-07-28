import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class Mouse extends GraphicsProgram {
	
	public Mouse(){
		
	}
	
	private static int WAIT = 10;
	int x = 0;
	int y = 0;
	int x1 = 0;
	int y1 = 0;
	GLine line = new GLine(x, y, x1, y1);

	public void init() {
		addMouseListeners();
	}

	public void run() {
		while (true) {

		}
	}

	// ArrayList<Location> locations = new ArrayList<Location>();

	public void mouseDragged(MouseEvent e) {
		x1 = x;
		y1 = y;

		x = e.getX();
		y = e.getY();

		// Location loc = new Location(x,y);
		// locations.add(loc);

		addLine();
		pause(WAIT);
	}

	private void addLine() {
		line = new GLine(x, y, x1, y1);
		add(line);
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		x1 = x;
		y1 = y;
	}
}