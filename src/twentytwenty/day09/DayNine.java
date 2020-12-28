package twentytwenty.day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import twentytwenty.Constants;

public class DayNine {
	
	public static void main(String[] args) {
		part2();
	}
	
	private static void part2() {
		try {
			ArrayList<Long> preamble = getPreamble("day09/09.inp");
			long out;
			long target = 731031916;
			for (int index = 0; index < preamble.size(); index++) {
				out = findWeakness(index, preamble, target);
				if (out > 0) {
					System.out.print(out);
					System.exit(0);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("No weakness found!");
	}
	
	private static void part1() {
		ArrayList<Integer> preamble = new ArrayList<Integer>();
		int preambleNum = 25;
		BufferedReader reader;
		try {
			File file = new File(Constants.DIR + "day09/09.inp");
			reader = new BufferedReader(new FileReader(file));
			String line;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				Integer newNumber = Integer.valueOf(line);
				if (lineNumber <= preambleNum) {
					preamble.add(newNumber);
				} else {
					if (!isSumOfTwo(preamble, newNumber)) {
						System.out.print(newNumber);
						System.exit(0);
					}
					int replace = (lineNumber - 1) % preambleNum;
					preamble.set(replace, newNumber);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Long findWeakness(int offset, ArrayList<Long> preamble, long target) {
		long sum = 0;
		for (int index = offset; index < preamble.size(); index++) {
			sum = sum + preamble.get(index);
			if (sum > target) return (long) 0;
			if (sum == target) {
				List<Long> sublist = preamble.subList(offset, index + 1);
				Collections.sort(sublist);
				return sublist.get(0) + sublist.get(index - offset);
			}
		}
		return (long) 0;
	}
	
	private static ArrayList<Long> getPreamble(String inFile) throws NumberFormatException, IOException {
		ArrayList<Long> preamble = new ArrayList<Long>();
		File file = new File(Constants.DIR + inFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			Long newNumber = Long.valueOf(line);
			preamble.add(newNumber);
		}
		reader.close();
		return preamble;
	}
	
	private static boolean isSumOfTwo(ArrayList<Integer> preamble, Integer target) {
		Set<Integer> complements = new HashSet<Integer>();
		for (Integer number : preamble) {
			if (complements.contains(number)) {
				return true;
			}
			complements.add(target - number);
		}
		return false;
	}

}
