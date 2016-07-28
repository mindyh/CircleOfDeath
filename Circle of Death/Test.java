import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;


public class Test extends GraphicsProgram {
	
	public static final int APPLICATION_WIDTH = 960;
	public static final int APPLICATION_HEIGHT = 640;
	private ArrayList<GPoint> path = new ArrayList<GPoint>();
	//private ArrayList<ArrayList<GPoint>> allPaths;
	private ArrayList<GLine> drawnPath = new ArrayList<GLine>();
	private ArrayList<Bike> bikes = new ArrayList<Bike>();
	private GImage background = new GImage("bg.png");
	private static final double PAUSE_TIME = 1000 / 30;
	
	private GPoint lastPoint;
	private Bike whichBike;
	boolean isBike = false;
	
	public void init() {
		addMouseListeners();
		add(background);
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run() {
		int counter = 0; //so it waits 3 sec before it produces another biker
		while(true) {
			if (counter == 0) {
				Bike bike = new Bike();
				add(bike);
				bikes.add(bike);
				counter = 100;
			}
			moveBikes();
			counter--;
			pause(PAUSE_TIME);
			revalidate();
			repaint();
		}
	}
	
	
	public void printPath(ArrayList<GPoint> path) {
		System.out.println("{");
		for (int i = 0; i < path.size(); i++) {
			double x = path.get(i).getX();
			double y = path.get(i).getY();
			System.out.print("(" + x + ", " + y + "), ");
		}
		System.out.println("}\n");
	}
	
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked!");
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed!");
		GObject gobj = getElementAt(e.getX(), e.getY());
		//if (gobj != background) {
			//isBike = true;
			for (int i = 0; i < bikes.size(); i++) {
				Bike bike = bikes.get(i);
				//double distance = Math.sqrt(Math.pow(e.getX() - bike.getLocation().getX(), 2) + 
				//							Math.pow(e.getY() - bike.getLocation().getY(), 2));
				if (bikes.get(i).contains(e.getX(), e.getY())) {
				//if (distance <= 30) {	
					whichBike = bikes.get(i);
				}
			}
			path = new ArrayList<GPoint>();
			GPoint point = new GPoint(e.getX(), e.getY());
			path.add(point);
		//}
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged!");
		//if (!isBike) return;
		GPoint point = new GPoint(e.getX(), e.getY());
		path.add(point);
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.print("Mouse released!");
		if (whichBike != null) {
			ConsolidatePath consolidate = new ConsolidatePath();
			printPath(path);
			path = consolidate.consolidatePath(path, path.get(path.size() - 1), PAUSE_TIME);
			printPath(path);
			whichBike.setPath(path);
		}
	}
	
	private void moveBikes() {
		for (int i = 0; i < bikes.size(); i++) {
			Bike bike = bikes.get(i);
			bike.updateBiker();
			add(bike);
			if (hasExited(bike)) {
				bikes.remove(i);
				i--;
			}
			// Check for collisions
			for (int j = 0; j < bikes.size(); j++) {
				Bike bike2 = bikes.get(i);
				if (j == i) continue;
				GRectangle bounds = bike.getBounds();
				GRectangle bounds2 = bike2.getBounds();
				if (bounds.intersects(bounds2)) {
					bikes.remove(j);
					bikes.remove(i);
					i--;
					break;
				}
			}
		}
	}
	
	private boolean hasExited(Bike bike) {
		boolean retval = false;
		if (bike.getDestination() == 2) {
			if (bike.getX() < 106) return true;
		} else if (bike.getDestination() == 4) {
			if (bike.getX() > 543) return true;
		} else if (bike.getDestination() == 1) {
			if (bike.getY() < 265) return true;
		} else {
			if (bike.getY() > 701) return true;
		}
		return retval;
	}
}
