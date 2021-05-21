package ie.gmit.dip;

import java.io.*;
import java.util.*;

/**
 * This class performs file parsing for the application and then returns the Map
 * generated to the <b>Menu</b> class.
 * 
 * @author Conor Keating
 * @version 1.0
 * @see Menu
 */
public class FileParser {

	/**
	 * Method parses a file based on one of two options. Either each separate word
	 * is mapped, or each sentence is broken down into 'words' of 5 character length
	 * (kmer). Before generating the hashcode of each string, all punctuation is
	 * also removed and the remaining letters are converted to lower case.
	 * 
	 * @param file   - The file to be parsed
	 * @param choice - By word or kmer
	 * @return myMap composed of hashcode, frequency of occurrence
	 */
	public Map<Integer, Integer> parseFile(File file, int choice) {
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = br.readLine()) != null) {
				String words[];
				if (choice == 1) {
					words = line.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");// All punctuation removed and
																						// split by word
				} else {
					words = line.toLowerCase().replaceAll("[^a-z0-9 ]", "").split("(?<=\\G.{5})"); // All punctuation
																									// removed and split
																									// into kmer length
																									// of 5 characters.
				}
				for (String word : words) {
					if (word.isBlank() == false) {
						int hash = word.hashCode();
						int frequency = 1;
						if (myMap.containsKey(hash)) {
							frequency += myMap.get(hash);
						}
						myMap.put(hash, frequency);
//						System.out.println(word);
					}
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(myMap);
		return myMap;
	}

	/**
	 * Method parses an input file into strings of 2 word combinations. If there are
	 * an odd number of words in the line read, the last element of the ArrayList
	 * will contain a single word. As in the parseFile method, all punctuation is
	 * removed and remaining letters are converted to lower case.
	 *
	 * @param file - The file to be parsed
	 * @return myMap composed of hashcode, frequency of occurrence
	 */
	public Map<Integer, Integer> parseFileWordPairs(File file) {
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");
				List<String> wordPairs = new ArrayList<>();

				for (int count = 1; count < words.length; count += 2) {
					wordPairs.add(words[count - 1] + " " + words[count]); // This list separates words array into word
																			// pairs and adds the last word if necessary
				}
				if (words.length % 2 == 1) {
					wordPairs.add(words[words.length - 1]);
				}
				for (String word : wordPairs) {
					if (word.isBlank() == false) {
						int hash = word.hashCode();
						int frequency = 1;
						if (myMap.containsKey(hash)) {
							frequency += myMap.get(hash);
						}
						myMap.put(hash, frequency);
//						System.out.println(word);
					}
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(myMap);
		return myMap;
	}

}
