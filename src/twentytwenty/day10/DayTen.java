package twentytwenty.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import twentytwenty.Constants;

public class DayTen {
	public static void main(String[] args) {
		part2();
	}
	
	public static void part2() {
		try {
			ArrayList<Integer> data = getData(Constants.DIR + "day10/10.inp");
			toDifferences(data);
			long numberOfCombination = 1;
			int max = data.size();
			System.out.println(data);
			for (int i = 0; i < max; i++) {
				if(i + 1 < max && data.get(i) == 1 && data.get(i + 1) == 1) {
					boolean begin = false;
					if (i==0) begin = true; 
					int numOnes = 2;
					i++;
					while (i + 1 < max && data.get(i + 1) == 1) {
						numOnes++;
						i++;
					}
					System.out.println("Ones: " + numOnes);
					int fac = 0;
					if (begin) {
						for (int j = 0; j < 3; j++) {
							fac = fac + binom(numOnes, j);
						}
					} else {
						for (int j = 0; j < 3; j++) {
							fac = fac + binom(numOnes - 1, j);
						}
					}
					System.out.println("Factor: " + fac);
					numberOfCombination = numberOfCombination * fac;	
				}
			}
			System.out.println(numberOfCombination);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void part1() {
		try {
			ArrayList<Integer> data = getData(Constants.DIR + "day10/10.inp");
			int ones = 1; // outlet
			int threes = 1; // device's built-in joltage adapter
			int max = data.size();
			for (int index = 1; index < max; index++) {
				if ((data.get(index) - data.get(index - 1)) == 1) ones++;
				if ((data.get(index) - data.get(index - 1)) == 3) threes++;
			}
			System.out.println(ones*threes);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void toDifferences(ArrayList<Integer> data) {
		int max = data.size();
		for (int index = 0; index < max - 1; index++) {
			Integer diff = data.get(index + 1) - data.get(index);
			data.set(index, diff);
		}
		data.remove(max -1);
	}
	
	private static ArrayList<Integer> getData(String file) throws NumberFormatException, IOException {
		ArrayList<Integer> data = new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			Integer newNumber = Integer.valueOf(line);
			data.add(newNumber);
		}
		reader.close();
		Collections.sort(data);
		return data;
	}
	
	public static int binom(int n, int k) {
		int binom = 1;
		for (int i = 1; i <= k; i++) {
			binom = binom * (n + 1 - i) / i;
		}
		return binom;
	}
}
