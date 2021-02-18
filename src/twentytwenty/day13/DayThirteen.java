package twentytwenty.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twentytwenty.Constants;

public class DayThirteen {
	public static void main(String[] args) throws IOException {
		part2();
	}
	
	static void part1() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Constants.DIR + "day13/13.inp"));
		Integer time = Integer.valueOf(reader.readLine());
		String busLine = reader.readLine();
		String regex = "([^0-9]*)([0-9]{1,10})([^0-9]*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(busLine);
		TreeMap<Integer,Integer> busTimes = new TreeMap<>();
		while(matcher.find()) {
			Integer busID = Integer.valueOf(matcher.group(2));
			if (time % busID == 0) {
				busTimes.put(0, busID);
			} else {
				busTimes.put((time/busID + 1) * busID - time, busID);
			}
		}
		reader.close();
		Entry<Integer,Integer> first = busTimes.firstEntry();
		System.out.println(first.getKey()*first.getValue());
	}
	/*
	 * If we denote the searched time point as x, then we want that there exists a natural number k_n, 
	 * so that x + n = k_n*I_n, where n denotes the bus offset and I_n the bus ID.
	 * Let us write k_n = 1 - a_n for some a_n. Then the equation reads
	 * x + n = (1-a_n)I_n
	 * => I_n - n = k_n*I_n + x
	 * => x = (I_n - n) mod I_n
	 * => x can be computed by the chinese algorithm
	 */
	static void part2() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(Constants.DIR + "day13/13.inp"));
		reader.readLine();
		String busLine = reader.readLine();
		String regex = "(,?)([x0-9]{1,10})(,?)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(busLine);
		int offset = 0;
		ArrayList<Long> moduli = new ArrayList<Long>();
		ArrayList<Long> a = new ArrayList<Long>();
		while(matcher.find()) {
			String busID = matcher.group(2);
			if (!busID.equals("x")) {
				Long id = Long.valueOf(busID);
				moduli.add(id);
				a.add(id-offset);
			}
			offset++;
		}
		reader.close();
		HashMap<String,Long> result = chinese(moduli, a);
		System.out.println(result.get("x"));
	}
	
	static HashMap<String,Long> extendedEuclid(long a, long b) {
		long[] xs = new long[] {1,0};
		long[] ys = new long[] {0,1};
		long sign = 1;
		while (b != 0) {
			long rest = a % b;
			long q = a / b;
	        a = b;
	        b = rest;
	        long xx = xs[1];
	        long yy = ys[1];
	        xs[1] = q*xs[1] + xs[0];
	        ys[1] = q*ys[1] + ys[0];
	        xs[0] = xx;
	        ys[0] = yy;
	        sign = -sign;
		}
		HashMap<String,Long> out = new HashMap<String, Long>();
		out.put("gcd", a);
		out.put("x", sign*xs[0]);
		out.put("y", -sign*ys[0]);
		return out;
	}
	
	static HashMap<String,Integer> extendedEuclidRec(int a, int b) {
		HashMap<String,Integer> out = new HashMap<String, Integer>();
		if (b == 0) {
			out.put("gcd", a);
			out.put("x", 1);
			out.put("y", 0);
			return out;
		}
		HashMap<String,Integer> tmp = extendedEuclidRec(b, a%b);
		out.put("gcd", tmp.get("gcd"));
		out.put("x", tmp.get("y"));
		out.put("y", tmp.get("x") - (a / b)*tmp.get("y"));
		return out;
	}
	
	static HashMap<String,Long> chinese(ArrayList<Long> moduli, ArrayList<Long> a) {
		HashMap<String,Long> out = new HashMap<String, Long>();
	    long totalmodulus = 1;
	    ArrayList<Long> multipliers = new ArrayList<Long>();
	    for (Long modulus : moduli) {
	    	totalmodulus = totalmodulus*modulus;
	    }
	    for (Long modulus: moduli) {
	    	long m = totalmodulus / modulus;
	    	HashMap<String,Long> xresult = extendedEuclid(m, modulus);
			long inverse = xresult.get("x");
			long prod = inverse*m;
			while (prod < 0) {
				prod += totalmodulus;
			}
			multipliers.add((prod) % totalmodulus);
	    }
	    long x = 0;
	    for (int i = 0; i < moduli.size(); i++) {
	    	x = (x + multipliers.get(i) * a.get(i));
	    	while ( x < 0) {
	    		x += totalmodulus;
	    	}
	    }
	    out.put("x", x % totalmodulus);
	    out.put("m", totalmodulus);
		return out;
	}


}
