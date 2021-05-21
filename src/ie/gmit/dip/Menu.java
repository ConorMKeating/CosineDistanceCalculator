package ie.gmit.dip;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Class contains code related to the menu of the cosine application. Threads
 * are used to parse two separate files individually. Two maps are returned from
 * the <b>FileParser</b> class, and then sent to the <b>CompareMaps</b> class
 * for similarity comparison.
 * 
 * @author Conor Keating
 * @version 1.0
 * @see FileParser
 * @see CompareMaps
 */
public class Menu {

	private int menuChoice;
	private Scanner s = new Scanner(System.in);
	private File tempFile, queryFile, subjectFile;
	private FileParser fp1 = new FileParser();
	private Map<Integer, Integer> myMapFile1 = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> myMapFile2 = new HashMap<Integer, Integer>();
	private CompareMaps cMaps = new CompareMaps();

	/**
	 * Prints and runs all menu related output and input.
	 * 
	 * @throws InterruptedException
	 */
	public void myMenu() throws InterruptedException {

		System.out.println("**********************************************************************");
		System.out.println("********************  Document Comparison System  ********************");
		System.out.println("**********************************************************************");
		System.out.println("***  This application can compare text files in a number of ways.  ***");
		System.out.println("**********************************************************************");

		System.out.println("\n       Please enter the number for your chosen option.");
		System.out.println("1) to compare files based on occurence of same individual words.");
		System.out.println("2) to compare files based on a kmer length of 5 characters.");
		System.out.println("3) to compare files based on occurence of word pairs.");

		boolean keepRunning = true;
		while (keepRunning) {
			try {
				menuChoice = Integer.parseInt(s.next());
				if (menuChoice == 1 || menuChoice == 2 || menuChoice == 3) {
					keepRunning = false;
				} else {
					System.out.println("***Invalid selection.... Please enter 1, 2 or 3!");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("***That was not a number. Please enter a number: ");
			} catch (InputMismatchException ime) {
				System.out.println("***That was not a number. Please enter a number: ");
			}
		}

		System.out.print("Enter Query File> ");
		queryFile = enterFileSource();
		Thread t1 = new Thread(new Task());
		t1.start();

		System.out.print("Enter Subject File> ");
		subjectFile = enterFileSource();
		Thread t2 = new Thread(new Task());
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Processing...Please Wait...\n");
		cMaps.getSimilarity(myMapFile1, myMapFile2);
	}

	/**
	 * Inner class responsible for running file parsing in separate threads.
	 * 
	 * @author Conor Keating
	 * @version 1.0
	 */
	private class Task implements Runnable {

		/**
		 * Overrides the interface <i>Runnable</i> run method. Method assigns map
		 * returned from <b>FileParser</b> to Map objects.
		 */
		@Override
		public void run() {

			if (menuChoice == 1 || menuChoice == 2) {
				if (myMapFile1.isEmpty() && subjectFile == null) {
//					try {
//						Thread.sleep(10000); System.out.println("Thread 1 finished sleeping");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					myMapFile1 = fp1.parseFile(queryFile, menuChoice);
				} else {
//					try {
//						Thread.sleep(10000); System.out.println("Thread 2 finished sleeping");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					myMapFile2 = fp1.parseFile(subjectFile, menuChoice);
				}
			} else {
				if (myMapFile1.isEmpty() && subjectFile == null) {
//					try {
//						Thread.sleep(10000); System.out.println("Thread 1 finished sleeping");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					myMapFile1 = fp1.parseFileWordPairs(queryFile);
				} else {
//					try {
//						Thread.sleep(10000); System.out.println("Thread 2 finished sleeping");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					myMapFile2 = fp1.parseFileWordPairs(subjectFile);
				}
			}
		}

	}

	/**
	 * Verifies validity of filename. If a valid filename was not entered, it
	 * recalls the getValidFile method. Returns a File.
	 */
	private File enterFileSource() {
		getValidFile();
		if (tempFile.isFile() == false) {
			while (tempFile.isFile() == false) {
				System.out.println("That is not a valid file!");
				System.out.print("Please enter filename again> ");
				getValidFile();
			}
		}
		return tempFile;
	}

	/**
	 * Reads in a filename from the user prompt.
	 */
	private void getValidFile() {
		String source;
		source = s.next();
		tempFile = new File(source);
	}

}
