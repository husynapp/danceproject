package astaire;

import java.util.ArrayList;

import exceptions.InvalidDanceNameException;

/**
 * 
 * @author Abdullah
 * @version 02/01/2019
 *
 */

public class FeasibilityChecker {

	private ArrayList<ArrayList<String>> danceOrder_;
	private ArrayList<String> danceWithProblem_;
	private ArrayList<Integer> problematicDanceNumber_;
	public ArrayList<String> peopleWithProblems_ = new ArrayList<String>();

	private ArrayList<String> arrayOfDances_;
	// make this private and change initialisation in the constructor
	private DanceList runningOrderToCheck_;
	private boolean isSuitable = true;

	public FeasibilityChecker() throws InvalidDanceNameException {
		arrayOfDances_ = new ArrayList<String>();
		danceOrder_ = new ArrayList<ArrayList<String>>();
		runningOrderToCheck_ = new DanceList();
		danceWithProblem_ = new ArrayList<String>();
		problematicDanceNumber_ = new ArrayList<Integer>();

//		addToArrayList(fileName);
	}

	/**
	 * Adds the danceOrders to an ArrayList
	 * 
	 * @param Please provide the link to a CSV file
	 * @throws InvalidDanceNameException
	 * 
	 */
	public void addToArrayListCSV(String filename) throws InvalidDanceNameException {
		ParseCSV runningOrder = new ParseCSV(filename, false);
		arrayOfDances_ = runningOrder.getDanceNames();
//		System.out.println(arrayOfDances.toString());
		for (int i = 0; i < arrayOfDances_.size(); i++) {
			danceOrder_.add(runningOrderToCheck_.searchSelectedPerformers(arrayOfDances_.get(i)));
		}

	}

	public void addToArrayWithoutCSV(ArrayList<String> array) throws InvalidDanceNameException {
		arrayOfDances_ = array;
		for (int i = 0; i < arrayOfDances_.size(); i++) {
			danceOrder_.add(runningOrderToCheck_.searchSelectedPerformers(arrayOfDances_.get(i)));
		}
	}

	/**
	 * Checks to see if the given dance order is suitable for the gap
	 * 
	 * @param numberOfGaps
	 */
	public boolean feasiblityChecker(int numberOfGaps) {
		peopleWithProblems_.clear();
		problematicDanceNumber_.clear();
		for (int danceCount = 0; danceCount < danceOrder_.size(); danceCount++) {
		
			ArrayList<String> nextPerformers = new ArrayList<String>();
			for (int gap = danceCount + 1; gap < danceCount + 1 + numberOfGaps; gap++) {
				if (danceOrder_.size() > danceCount + gap + 1) {
					nextPerformers.addAll(danceOrder_.get(gap));

				}
			}
			for (int nextPerformerCounter = 0; nextPerformerCounter < nextPerformers.size(); nextPerformerCounter++) {
				if (danceOrder_.get(danceCount).contains(nextPerformers.get(nextPerformerCounter))) {
					isSuitable = false;
					problematicDanceNumber_.add(danceCount);
					peopleWithProblems_.add(nextPerformers.get(nextPerformerCounter));
//					break;
				}

			}
		}
		return isSuitable;
	}

	public String danceWithProblem() {
		String result = "";
		if (!problematicDanceNumber_.isEmpty()) {
			result += "No suitable running order has been found, \nPlease reduce the gap and try again.\n";
			for (int i = 0; i < danceWithProblem_.size(); i++) {
				result += danceWithProblem_.get(i);
				result += "\n";
			}

		} else {
			result += "Order is suitable\n";
			for (int j = 0; j < arrayOfDances_.size(); j++) {
				result += arrayOfDances_.get(j) + "\n";
			}
		}
		return result;
	}

	public boolean suitable() {
		return isSuitable;
	}

	public ArrayList<String> getGeneratedOrder() {
		return arrayOfDances_;
	}

}
