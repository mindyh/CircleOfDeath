import acm.graphics.*;
import java.util.*;
import java.lang.*;

public class ConsolidatePath {
	
	/**
	 * 
	 * @param locations the ArrayList<GPoint> from the initial mouse presses
	 * @param currentLocation current location of the bike
	 * @param pauseTime number of milliseconds between each screen update
	 * @return consolidated path
	 */
	public ArrayList<GPoint> consolidatePath(ArrayList<GPoint> locations, GPoint currentLocation, double pauseTime) {
		ArrayList<GPoint> retval = new ArrayList<GPoint>();
		int lastLoc = 0;
		double minDistance = distance(currentLocation, locations.get(0));
		GPoint closest = locations.get(0);
		for (int i = 1; i < locations.size(); i++) {
			if (minDistance > distance(closest, locations.get(i))) {
				closest = locations.get(i);
				minDistance = distance(closest, locations.get(i));
				lastLoc = i;
			}
		}
		retval.add(currentLocation);
		double dx = currentLocation.getX() / minDistance;
		double dy = currentLocation.getY() / minDistance;
		for (int i = 0; i < minDistance; i++) {
			GPoint loc = new GPoint();
			loc.setLocation(currentLocation.getX() + i * dx, currentLocation.getY() + i * dy);
			retval.add(loc);
		}
		retval.add(locations.get(lastLoc));
		for (int i = lastLoc + 1; i < locations.size(); i++) {
			if (distance(locations.get(i), locations.get(lastLoc)) >= 1) {
				retval.add(locations.get(i));
				lastLoc = i;
				i++;
			}
		}
		
		return retval;
	}
	
	private double distance(GPoint location1, GPoint location2) {
		double x1 = location1.getX();
		double x2 = location2.getX();
		double y1 = location1.getY();
		double y2 = location2.getY();
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}

}