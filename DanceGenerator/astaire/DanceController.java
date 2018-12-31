package astaire;

public class DanceController implements Controller {
	DanceList dances;
	
	public DanceController() {
		dances = new DanceList();
	}

	@Override
	public String listAllDancersIn(String dance) {
			
		return dances.getPerformers(dance).toString();

	}

	@Override
	public String listAllDancesAndPerformers() {
	
//		return dances.getFullDanceList().toString();
		return formatText();
	}
	
	private String formatText() {
		String list = dances.getFullDanceList().toString();
		String[] split = list.split("],");
		String result = "";
		for (int index = 0; index < split.length; index++) {
			result += split[index];
			result += "]\n";
		}
		return result;
	}

	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

}
