package astaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Adds the performers to a dance list.
 * 
 * @author Abdullah
 * @version 31/01/2018
 */

public class DanceList {

	private TreeMap<String, ArrayList<String>> danceTreeMap;

	public DanceList() {
		ParseCSV danceCSV = new ParseCSV("astaireDataFiles/danceShowData_dances.csv");
		danceTreeMap = new TreeMap<String, ArrayList<String>>();
		danceTreeMap = danceCSV.getTreeMap();
		addRemainingMembers();

	}

	/**
	 * replace the "juniors" "seniors" stuff to actual names of people in the group
	 * Helper method
	 */
	private void addRemainingMembers() {
		GroupList groups = new GroupList();
		TreeMap<String, ArrayList<String>> tempGroupDetails = groups.getFullGroup();

		// Search through one of the danceTreeMap or tempGroupDetails and replace the
		// words

		for (Entry<String, ArrayList<String>> entry : danceTreeMap.entrySet()) {
			ArrayList<String> temp = entry.getValue();
			int numberToIterate = temp.size();
			for (int index = 0; index < numberToIterate; index++) {
				if (tempGroupDetails.containsKey(temp.get(index))) {
					temp.addAll(tempGroupDetails.get(temp.get(index)));
					temp.remove(index);
					index--;

				}
			}
			Collections.sort(temp);
		}

	}

	/**
	 * 
	 * @param groupName String taken in
	 * @return Returns the ArrayList<String> of performers for the dance
	 */
	public ArrayList<String> getPerformers(String groupName) {
		if (!danceTreeMap.containsKey(groupName)) {
			throw new IllegalArgumentException();
		}
		return danceTreeMap.get(groupName);
	}

	/**
	 * 
	 * @return
	 */
	public TreeMap<String, ArrayList<String>> getFullDanceList() {
		return danceTreeMap;

	}

}
