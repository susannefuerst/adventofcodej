package twentytwenty.day12;

public class Ship2 {
	int ns = 0;
	int ew = 0;
	Waypoint waypoint;
	
	public Ship2(Waypoint waypoint) {
		this.waypoint = waypoint;
	}
	
	public void forward(int units) {
		ns += units*waypoint.ns;
		ew += units*waypoint.ew;
	}
	
	public void north(int units) {
		this.ns += units;
	}
	public void south(int units) {
		this.ns -= units;
	}
	public void east(int units) {
		this.ew += units;
	}
	public void west(int units) {
		this.ew -= units;
	}
	public int manhattanDistance() {
		return Math.abs(ns) + Math.abs(ew);
	}

}
