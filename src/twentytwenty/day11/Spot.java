package twentytwenty.day11;

import java.util.ArrayList;
import java.util.List;

public class Spot {
	private boolean isSeat = true;
	private boolean isOccupied = false;
	private List<Spot> adjacents = new ArrayList<Spot>();
	
	void Spot() {
		
	}
	public void empty() {
		this.isOccupied = false;
	}		
	public void occupy() {
		this.isOccupied = true;
	}		
	public void toFloor() {
		this.isSeat = false;
	}	
	public void addAdjacent(Spot spot) {
		if (spot.isSeat) {
			this.adjacents.add(spot);
		}
	}	
	public int occupiedAdjacents() {
		int out = 0;
		for (Spot spot : this.adjacents) {
			if (spot.isOccupied) {
				out++;
			}
		}
		return out;
	}
	public boolean isSeat() {
		return this.isSeat;
	}
	public boolean isOccupied() {
		return this.isOccupied;
	}
	@Override
	public String toString() {
		if (!this.isSeat()) {
			return ".";
		}
		if (this.isOccupied()) {
			return "#";
		}
		return "L";
	}

}
