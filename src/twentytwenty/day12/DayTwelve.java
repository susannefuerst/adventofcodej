package twentytwenty.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import twentytwenty.Constants;

public class DayTwelve {
	
	public static void main(String[] args) throws IOException {
		part2();
	}

	private static void part1() throws NumberFormatException, IOException {
		Ship ship = new Ship();
		BufferedReader reader = new BufferedReader(new FileReader(Constants.DIR + "day12/12.inp"));
		String line;
		while ((line = reader.readLine()) != null) {
			Integer value = Integer.valueOf(line.substring(1));
			char operation = line.charAt(0);
			switch (operation) {
				case 'N': ship.north(value); break;
				case 'S': ship.south(value); break;
				case 'E': ship.east(value); break;
				case 'W': ship.west(value); break;
				case 'L': ship.left(value); break;
				case 'R': ship.right(value); break;
				case 'F': ship.forward(value); break;
			}
		}
		reader.close();
		System.out.println(ship.manhattanDistance());
	}
	private static void part2() throws NumberFormatException, IOException {
		Ship2 ship = new Ship2(new Waypoint(1,10));
		BufferedReader reader = new BufferedReader(new FileReader(Constants.DIR + "day12/12.inp"));
		String line;
		while ((line = reader.readLine()) != null) {
			Integer value = Integer.valueOf(line.substring(1));
			char operation = line.charAt(0);
			switch (operation) {
				case 'N': ship.waypoint.north(value); break;
				case 'S': ship.waypoint.south(value); break;
				case 'E': ship.waypoint.east(value); break;
				case 'W': ship.waypoint.west(value); break;
				case 'L': ship.waypoint.left(value); break;
				case 'R': ship.waypoint.right(value); break;
				case 'F': ship.forward(value); break;
			}
		}
		reader.close();
		System.out.println(ship.manhattanDistance());
	}

}
