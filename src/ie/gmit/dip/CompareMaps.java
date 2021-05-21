package ie.gmit.dip;

import java.util.*;

/**
 * <b>CompareMaps</b> class takes in two Maps from <b>Menu</b> class. Compares
 * the frequency of occurrence of each individual hashcode in each file.
 * 
 * @author Conor Keating
 * @version 1.0
 * @see Menu
 */
public class CompareMaps {

	/**
	 * This method takes two parameter Maps and compares the occurrence of hashcodes
	 * in each Map, generating a similarity percentage based on cosine mathematics.
	 * 
	 * @param myMapFile1 - First map to be compared.
	 * @param myMapFile2 - Second map to be compared.
	 */
	public void getSimilarity(Map<Integer, Integer> myMapFile1, Map<Integer, Integer> myMapFile2) {

		long startTime = System.currentTimeMillis();
		Set<Integer> keys = new HashSet<Integer>(myMapFile1.keySet());
		keys.addAll(myMapFile2.keySet());

		float dotProduct = 0;
		float map1Val = 0;
		float map2Val = 0;
		int match1 = 0;
		int match2 = 0;
		int match3 = 0;

		for (int key : keys) {
			if (myMapFile1.containsKey(key) && myMapFile2.containsKey(key)) {
				match1++;
				map1Val += Math.pow(myMapFile1.get(key), 2);
				map2Val += Math.pow(myMapFile2.get(key), 2);
				dotProduct += myMapFile1.get(key) * myMapFile2.get(key);
			} else if (myMapFile1.containsKey(key) && (myMapFile2.containsKey(key) == false)) {
				match2++;
				map1Val += Math.pow(myMapFile1.get(key), 2);
			} else {
				match3++;
				map2Val += Math.pow(myMapFile2.get(key), 2);
			}
		}

		map1Val = (float) Math.sqrt(map1Val);
		map2Val = (float) Math.sqrt(map2Val);

		float result = (dotProduct / (map1Val * map2Val)) * 100;
		result = Math.round(result * 100);
		long endTime = System.currentTimeMillis();

		System.out.println("----------------------------------------------------------------------");
		System.out.println("\n               A Little Bit of Interesting Information                ");
		System.out.println("Hashcodes which were matched in both Files: " + match1);
		System.out.println("Hashcodes which were unique in File 1: " + match2);
		System.out.println("Hashcodes which were unique in File 2: " + match3);
		System.out.println(
				"\nMap comparison took: " + (endTime - startTime) + " milliseconds. Wasn't that fast..  hopefully!");
		System.out.println("\n----------------------------------------------------------------------");
		System.out.println("\n                       Similarity: " + result / 100 + "%");
		System.out.println("----------------------------------------------------------------------");
	}
}
