package twentytwenty.day12;

public class Waypoint {
	int ns;
	int ew;
	
	public Waypoint(int ns, int ew) {
		this.ns = ns;
		this.ew = ew;
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
	public void right(int degree) {
		if (degree == 90 || degree == -270) {
			int tmp = ew;
			ew = ns;
			ns = -tmp;
		} else if (degree == 180 || degree == -180) {
			ew = -ew;
			ns = -ns;
		} else if (degree == 270 || degree == -90) {
			int tmp = ew;
			ew = -ns;
			ns = tmp;
		}
	}
	public void left(int degrees) {
		right(-degrees);
	}

}
