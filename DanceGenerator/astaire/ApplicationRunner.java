package astaire;

/**
 * @author Abdullah
 * @version 02/01/2019
 * Main Class
 */

public class ApplicationRunner {

	public static void main(String[] args) {
		Controller danceShowController = new DanceController();
		TUI textUI = new TUI(danceShowController);
	}

}
