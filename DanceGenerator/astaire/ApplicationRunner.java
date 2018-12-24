package astaire;


public class ApplicationRunner {

	public static void main(String[] args) {
		Controller danceShowController = new DanceController();
		TUI textUI = new TUI(danceShowController);
		
//		ParseCSV parseFile = new ParseCSV("astaireDataFiles/danceShowData_danceGroups.csv");
//		System.out.print(parseFile.getTreeMap());

	}

}
