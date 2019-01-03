package astaire;

import exceptions.InvalidDanceNameException;

/**
 * Implementation of the interface Controller.
 * @author Abdullah
 * @version 02/01/2019
 */

public class DanceController implements Controller {
	DanceList dances;
	
	public DanceController() {
		dances = new DanceList();
	}

	@Override
	public String listAllDancersIn(String dance) throws InvalidDanceNameException {			
			String dancers = dances.searchSelectedPerformers(dance).toString();
			String[] splitResult = dancers.split(",");
			String result = "Performers in " + dance + " are: \n";
			int tabCount = 1;
			for(int index = 0; index < splitResult.length; index ++) {
				result += splitResult[index];
				result +="\n";
				for(int j =0; j <tabCount; j++) {
					result+= "\t";
				}

				tabCount++;
			}
			
			return result;
			
	}
	

	@Override
	public String listAllDancesAndPerformers() {
	
		return formatText();
	}
	
	/**
	 * Helper method to print data
	 * @return 		Formatted String where it's more readable
	 */
	private String formatText() {
		String list = dances.getFullDanceList().toString();
		String[] split = list.split("], ");
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
