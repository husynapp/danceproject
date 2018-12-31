package astaire;

public class ApplicationRunner {

	public static void main(String[] args) {
		Controller danceShowController = new DanceController();
		TUI textUI = new TUI(danceShowController);
	}

}
