package twentytwenty.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import twentytwenty.Constants;

public class DayEleven {
	
	public static void main(String[] args) {
		part1(Constants.DIR + "day11/11.inp");
	}
	
	public static void part1(String inFile) {
		try {
			ArrayList<ArrayList<Spot>> spots = getSpots(inFile);
			determineAdjacent(spots);
			boolean changed = true;
			while (changed == true) {
				changed = change(spots);
			}
			System.out.println(numOfOccupied(spots));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void print(ArrayList<ArrayList<Spot>> spots) {
		for (ArrayList<Spot> spotsLine : spots) {
			System.out.println(spotsLine);
		}
		System.out.println("");
	}

	public static ArrayList<ArrayList<Spot>> getSpots(String inFile) throws IOException {
		ArrayList<ArrayList<Spot>> spots = new ArrayList<ArrayList<Spot>>();
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		String line;
		while ((line = reader.readLine()) != null) {
			ArrayList<Spot> spotsLine = new ArrayList<Spot>();
			for (int i= 0; i < line.length(); i++) {
				Spot spot = new Spot();
				if (line.charAt(i) == '.') {
					spot.toFloor();
				}	
				spotsLine.add(spot);
			}
			spots.add(spotsLine);
		}
		reader.close();
		return spots;
	}
	
	public static void determineAdjacent(ArrayList<ArrayList<Spot>> spots) {
		int rowMax = spots.size() - 1;
		int colMax = spots.get(0).size() - 1;
		for (ArrayList<Spot> spotsLine : spots) {
			for (Spot spot : spotsLine) {
				if(spot.isSeat) {
					int row = spots.indexOf(spotsLine);
					int column = spotsLine.indexOf(spot);
					if (spot.isSeat()) {
						for (int i = Math.max(0, row - 1); i <= Math.min(rowMax, row + 1); i++) {
							for (int j = Math.max(0, column - 1); j <= Math.min(colMax, column + 1); j++) {
								if (!(i == row && j == column)) {
									spot.addAdjacent(spots.get(i).get(j));
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static boolean change(ArrayList<ArrayList<Spot>> spots) {
		boolean changed = true;
		ArrayList<int[]> toOccupy = new ArrayList<int[]>();
		for (ArrayList<Spot> spotsLine : spots) {
			for (Spot spot : spotsLine) {
				if (spot.isSeat() && !spot.isOccupied() && spot.occupiedAdjacents() == 0) {
					int[] indices = {spots.indexOf(spotsLine), spotsLine.indexOf(spot)};
					toOccupy.add(indices);
				}
			}
		}
		if (!toOccupy.isEmpty()) {
			for (int[] indices : toOccupy) {
				spots.get(indices[0]).get(indices[1]).occupy();
			}
		} else {
			return false;
		}
		ArrayList<int[]> toEmpty = new ArrayList<int[]>();
		for (ArrayList<Spot> spotsLine : spots) {
			for (Spot spot : spotsLine) {
				if (spot.isSeat() && spot.isOccupied() && spot.occupiedAdjacents() >= 4) {
					int[] indices = {spots.indexOf(spotsLine), spotsLine.indexOf(spot)};
					toEmpty.add(indices);
				}
			}
		}
		if (!toEmpty.isEmpty()) {
			for (int[] indices : toEmpty) {
				spots.get(indices[0]).get(indices[1]).empty();
			}
		} else {
			return false;
		}
		return changed;
	}
	
	public static int numOfOccupied(ArrayList<ArrayList<Spot>> spots) {
		int occupied = 0;
		for (ArrayList<Spot> spotsLine : spots) {
			for (Spot spot : spotsLine) {
				if (spot.isSeat() && spot.isOccupied()) {
					occupied++;
				}
			}
		}
		return occupied;
	}

}
