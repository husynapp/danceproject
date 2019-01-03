package astaire;

import java.util.ArrayList;

import exceptions.InvalidDanceNameException;

/**
 * 
 * @author Abdullah
 * @version 02/01/2019
 *
 */

public class RunningOrderChecker {

	private ArrayList<ArrayList<String>> danceOrder_;
	private ArrayList<Integer> problematicDanceNumber_;
	private ArrayList<String> arrayOfDances_;
	//make this private and change initialisation in the constructor
	public ArrayList<String> peopleWithProblems_ = new ArrayList<String>();

	private DanceList runningOrderToCheck;

	public RunningOrderChecker(String fileName) throws InvalidDanceNameException {
		arrayOfDances_ = new ArrayList<String>();
		problematicDanceNumber_ = new ArrayList<Integer>();
		danceOrder_ = new ArrayList<ArrayList<String>>();
		runningOrderToCheck = new DanceList();
		addToArrayList(fileName);
	}

	/**
	 * Adds the danceOrders to an ArrayList
	 * 
	 * @throws InvalidDanceNameException
	 * 
	 */
	private void addToArrayList(String filename) throws InvalidDanceNameException {
		ParseCSV runningOrder = new ParseCSV(filename, true);
		arrayOfDances_ = runningOrder.getDanceNames();
//		System.out.println(arrayOfDances.toString());
		for (int i = 0; i < arrayOfDances_.size(); i++) {
			danceOrder_.add(runningOrderToCheck.searchSelectedPerformers(arrayOfDances_.get(i)));
		}

	}

	/**
	 * Checks to see if the given dance order is suitable for the gap
	 * 
	 * @param numberOfGaps
	 */
	public void loopChecker(int numberOfGaps) {
		for (int danceCount = 0; danceCount < danceOrder_.size(); danceCount++) {
			ArrayList<String> nextPerformers = new ArrayList<String>();
			for (int gap = danceCount + 1; gap < danceCount + 1 + numberOfGaps; gap++) {
				if (danceOrder_.size() > danceCount + gap + 1) {
					nextPerformers.addAll(danceOrder_.get(gap));
				}
			}
			for (int nextPerformerCounter = 0; nextPerformerCounter < nextPerformers.size(); nextPerformerCounter++) {
				if (danceOrder_.get(danceCount).contains(nextPerformers.get(nextPerformerCounter))) {
					problematicDanceNumber_.add(danceCount);
					peopleWithProblems_.add(nextPerformers.get(nextPerformerCounter));

				}

			}
		}
	}

	public ArrayList<Integer> getProblematicDance() {
		return problematicDanceNumber_;
	}

	public String danceWithProblem() {
		String result = "";
		if (!problematicDanceNumber_.isEmpty()) {
			result += "Dances with problems are: ";
			for(int index = 0; index < problematicDanceNumber_.size(); index++) {
				//This next line adds the dance name where the problem occurs
				result += "\n" + arrayOfDances_.get(problematicDanceNumber_.get(index));
				//Next line adds the person for which the problem occurs
				result += "\t" + peopleWithProblems_.get(index);
			}
		}else {
			result += "There is enough time to change";
		}
		return result;
	}

	public static void main(String[] args) throws InvalidDanceNameException {
		RunningOrderChecker x = new RunningOrderChecker("astaireDataFiles/danceShowData_runningOrder.csv");
		x.loopChecker(3);
		System.out.println(x.danceWithProblem());
	}

}
