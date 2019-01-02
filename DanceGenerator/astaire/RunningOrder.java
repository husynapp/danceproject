package astaire;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RunningOrder {

	private ArrayList<ArrayList<String>> danceOrder;

	private DanceList runningOrderToCheck;

	public RunningOrder() {
		danceOrder = new ArrayList<ArrayList<String>>();
		runningOrderToCheck = new DanceList();
	}

	public void addToArrayList() {
		ParseCSV runningOrder = new ParseCSV("astaireDataFiles/danceShowData_runningOrder.csv", true);
		ArrayList<String> arrayOfDances = runningOrder.getDanceNames();
		System.out.println(arrayOfDances.toString());
		for(int i =0; i < arrayOfDances.size(); i++) {
			danceOrder.add(runningOrderToCheck.getPerformers(arrayOfDances.get(i)));
		}


	}

	public void loopChecker(int numberOfGaps) {
		for (int danceCount = 0; danceCount < danceOrder.size(); danceCount++) {
			ArrayList<String> nextPerformers = new ArrayList<String>();
			for (int gap = danceCount + 1; gap < danceCount + 1 + numberOfGaps; gap++) {
				if (danceOrder.size() > danceCount + gap + 1) {
					nextPerformers.addAll(danceOrder.get(gap));
				}
			}
			for (int nextPerformerCounter = 0; nextPerformerCounter < nextPerformers.size(); nextPerformerCounter++) {
				if (danceOrder.get(danceCount).contains(nextPerformers.get(nextPerformerCounter))) {
					System.out.println("Not enough gap" + danceCount);

				}

			}
		}
		System.out.println("Complete");
	}

	public static void main(String[] args) {
		RunningOrder x = new RunningOrder();
		x.addToArrayList();
		x.loopChecker(2);
		System.out.println(x.danceOrder.size());

	}

}
