package twentytwenty.day12;

public class Ship {
	private int ns = 0;
	private int ew = 0;
	private Direction dir = Direction.E;
	
	public Ship() {
	}
	
	public void forward(int units) {
		if (this.dir.equals(Direction.N)) {
			north(units);
		} else if (this.dir.equals(Direction.S)) {
			south(units);
		} else if (this.dir.equals(Direction.E)) {
			east(units);
		} else {
			west(units);
		}
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
	public void left(int degrees) {
		this.dir = this.dir.turn(degrees);
	}
	public void right(int degree) {
		left(-degree);
	}
	public int manhattanDistance() {
		return Math.abs(ns) + Math.abs(ew);
	}
}
