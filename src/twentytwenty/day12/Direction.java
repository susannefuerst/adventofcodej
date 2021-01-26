package twentytwenty.day12;

public enum Direction {
	E(0), N(1), W(2), S(3);
	
	private int value;
	
	private Direction(int value) {
		this.value = value;
	}
	
	public Direction turn(int degrees) {
		int newValue = (this.value + degrees/90 + 4) % 4;
		for (Direction dir : Direction.values()) {
			if (dir.value == newValue) {
				return dir;
			}
		}
		// could not turn, do nothing
		return this;
	}
}
