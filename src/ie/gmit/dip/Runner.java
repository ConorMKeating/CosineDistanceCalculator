package ie.gmit.dip;

/**
 * This is the <b>Runner</b> class for the application.
 * 
 * @author Conor Keating
 * @version 1.0
 */
public class Runner {

	/**
	 * This is the main entry point of the application.
	 * 
	 * @param args - Standard main method
	 */
	public static void main(String[] args) {
		try {
			new Menu().myMenu();
		} catch (InterruptedException e) {
			System.out.println("ERROR - Thread execution was interrupted\n");
			e.printStackTrace();
		}
	}

}
