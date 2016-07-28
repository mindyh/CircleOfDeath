import acm.graphics.*;
import acm.util.*;
import java.util.*;

public class Bike extends GCompound {
	private RandomGenerator rgen = RandomGenerator.getInstance();
	/** Whether the bike starts from N,E,S,W */
	int initPosition = rgen.nextInt(1,4);
	double initX, initY = 0;
	
	/** Whether the bike ends: N,E,S,W */
	int destination = 0;
	
	/** index of path */
	int index;
	GPoint lastLocation;
	private ArrayList<GPoint> path = new ArrayList<GPoint>();
	
	GCompound biker = new GCompound();
	
	public Bike() {
		genBiker(initPosition, isAsshole());
		genDestination(initPosition);
		genInitPosition(initPosition);
		add(biker);
		index = 0;
	}
	
	/** generate a destination */
	private void genDestination(int initPosition){
		destination = initPosition;
		while(destination == initPosition){
			destination = rgen.nextInt(1,4);
		}
	}
	
	private void genBiker(int initPosition, boolean isAsshole){
		if (!isAsshole){
			genRedBiker(initPosition);
		} else {
			genBlueBiker(initPosition);
		}
	}
	
	private void genRedBiker(int initPosition){
		switch (initPosition){
		case 1: biker.add(new GImage("redbiker_left.png"));
				break;
		case 2: biker.add(new GImage("redbiker_front.png"));
				break;
		case 3: biker.add(new GImage("redbiker_right.png"));
				break;
		case 4: biker.add(new GImage("redbiker.png"));
				break;
		}		
	}
	
	private void genBlueBiker(int initPosition){
		switch (initPosition){
		case 1: biker.add(new GImage("bluebiker_left.png"));
				break;
		case 2: biker.add(new GImage("bluebiker_front.png"));
				break;
		case 3: biker.add(new GImage("bluebiker_right.png"));
				break;
		case 4: biker.add(new GImage("bluebiker.png"));
				break;
		}
	}	
	
	private boolean isAsshole(){
		return rgen.nextBoolean(.25);
	}
	
	private void genInitPosition(int initPosition){
		switch (initPosition){
		//west
		case 1: initX = 50 - 45/2.0;
				initY = 350 - 29;
				break;
		//north
		case 2: initX = 475 - 45/2.0;
				initY = 50 - 29;
				break;
		//east
		case 3: initX = 900 - 45/2.0;
				initY = 325 - 29;
				break;
		//south
		case 4: initX = 475 - 45/2.0;
				initY = 600 - 29;
				break;
		}
		
		biker.setLocation(initX, initY);

	}
	
	private void genInitPath(int initPosition){
		GPoint point = new GPoint(initX, initY);
		switch (initPosition){
		//west
		case 1: initX = 50 - 45/2.0;
				initY = 350 - 29;
				for (int i = 0; i < 1000; i++) {
					point = new GPoint(initX + i, initY);
					path.add(point);
				}
				break;
		//north
		case 2: initX = 475 - 45/2.0;
				initY = 50 - 29;
				for (int i = 0; i < 1000; i++) {
					point = new GPoint(initX, initY + i);
					path.add(point);
				}
				break;
		//east
		case 3: initX = 900 - 45/2.0;
				initY = 325 - 29;
				for (int i = 0; i < 1000; i++) {
					point = new GPoint(initX - i, initY);
					path.add(point);
				}
				break;
		//south
		case 4: initX = 475 - 45/2.0;
				initY = 600 - 29;
				for (int i = 0; i < 1000; i++) {
					point = new GPoint(initX, initY - i);
					path.add(point);
				}
				break;
		}
		
	}
	
	/** update image of biker to reflect the general direction he's facing */
	public void updateBiker(){
		if (path == null || path.size() == 0)
			return;
		if (index == path.size() - 2) {
			lastLocation = path.get(index);
		}
		if (index <= path.size() - 1) {
		/*	double dx = path.get(index).getX() - path.get(index - 1).getX();
			double dy = path.get(index).getY() - path.get(index-1).getY();
			GPoint point = new GPoint(path.get(index).getX() + dx, path.get(index).getY() + dy);
			path.add(point);
		}
		else*/
			biker.setLocation(new GPoint(path.get(index).getX(), path.get(index).getY())); 
		}
		else {
			GPoint loc = biker.getLocation();
			/*if (loc.getX() > 960 || loc.getY() > 640) {
				remove(biker);
				return;
			}*/
			double dx = loc.getX() - lastLocation.getX();
			double dy = loc.getY() - lastLocation.getY();
			biker.setLocation(loc.getX() + dx, loc.getY() + dy);
		}
		
		/*double theta = calcTheta(path.get(index), path.get(index+1));
		
		if ((theta > 0) && (theta < 40)){
			remove(biker);
			biker.add(new GImage("bluebiker_Left.png"));
			add(biker);
		} else if ((theta >= 40) && (theta < 180)){
			remove(biker);
			biker.add(new GImage("bluebiker.png"));
			add(biker);
		} else if ((theta >= 180) && (theta > 220)){
			remove(biker);
			biker.add(new GImage("bluebiker_right.png"));
			add(biker);
		} else if ((theta >= 220) && (theta < 360)){
			remove(biker);
			biker.add(new GImage("bluebiker_front.png"));
			add(biker);
		} 
		
		biker.movePolar(100, theta);*/
		
		index++;
	}
	
	
	public void setPath(ArrayList<GPoint> newPath) {
		path = newPath;
	}
	
	public ArrayList<GPoint> getPath() {
		return path;
	}
	
	public double calcTheta (GPoint point1, GPoint point2){
		double x1 = point1.getX();
		double y1 = point1.getY();
		double x2 = point2.getX();
		double y2 = point2.getY();
		
		double theta = Math.atan((y1-y2)/(x1-x2));
		
		return theta;
	}
	
	public int getDestination(){
		return destination;
	}
}
