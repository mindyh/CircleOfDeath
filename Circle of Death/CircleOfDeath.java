import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import acm.graphics.*;
import acm.program.*;

public class CircleOfDeath extends GraphicsProgram {
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
		this.addMouseListeners();
		add(background);
		//add(new Bike());
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

	public void MousePressed(MouseEvent e) {
		System.out.println("Mouse pressed!");
		GObject gobj = getElementAt(e.getX(), e.getY());
		if (gobj != background) {
			isBike = true;
			for (int i = 0; i < bikes.size(); i++) {
				if (bikes.get(i).contains(e.getX(), e.getY())) {
					whichBike = bikes.get(i);
				}
			}
			path = new ArrayList<GPoint>();
			GPoint point = new GPoint(e.getX(), e.getY());
			path.add(point);
		}
	}
	
	public void MouseEntered(MouseEvent e) {
		System.out.println("Mouse entered!");
	}
	
	public void MouseExited(MouseEvent e) {
		System.out.println("Mouse exited");
	}
	
	public void MouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked.");
	}
	
	public void MouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged!");
		if (!isBike) return;
		GPoint point = new GPoint(e.getX(), e.getY());
		path.add(point);
		//showLine();
	}
	
	public void MouseReleased(MouseEvent e) {
		System.out.println("Mouse released!");
		if (isBike) {
			ConsolidatePath consolidate = new ConsolidatePath();
			path = consolidate.consolidatePath(path, lastPoint, PAUSE_TIME);
			whichBike.setPath(path);
		}
	}
	
	/*private void showLine() {
		if (path.size() == 1) return;
		GLine line = new GLine(path.get(path.size() - 2).getX(), path.get(path.size() - 2).getY(), path.get(path.size() - 1).getX(), path.get(path.size() - 1).getY());
		drawnPath.add(line);
		for (int i = 0; i < drawnPath.size(); i++) {
			add(drawnPath.get(i));
		}
	}*/
	
	private void moveBikes() {
		for (int i = 0; i < bikes.size(); i++) {
			Bike bike = bikes.get(i);
			bike.updateBiker();
			//add(bike);
			if (hasExited(bike)) {
				bikes.remove(i);
				i--;
			}
			else bikes.set(i, bike);
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
	
	/*public enum Direction {
		N, S, E, W;
	}*/

}
