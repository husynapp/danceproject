package astaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;
import exceptions.InvalidDanceNameException;

/**
 * Adds the performers to a dance list. All of the dance numbers and their
 * performers gets added to a TreeMap, then any group name also gets replaced
 * with respective performers
 * 
 * @author Abdullah
 * @version 31/01/2018
 */

public class DanceList {

	private TreeMap<String, ArrayList<String>> danceAndPerformers_;

	/**
	 * Constructor - Parses the danceShowData_dances.csv file, returns the treeMap
	 * from the parsed file and stores in global variable danceTreeMap call the
	 * method addRemainigMembers() to replace the group names to member names
	 */
	public DanceList() {
		ParseCSV danceCSV = new ParseCSV("astaireDataFiles/danceShowData_dances.csv", true);
		danceAndPerformers_ = new TreeMap<String, ArrayList<String>>();
		danceAndPerformers_ = danceCSV.getTreeMap();
		replaceGroupWithPerformers();

	}

	/**
	 * replace the "juniors" "seniors" stuff to actual names of people in the group
	 * Helper method
	 */
	private void replaceGroupWithPerformers() {
		GroupList groups = new GroupList();
		TreeMap<String, ArrayList<String>> tempGroupDetails = groups.getFullGroup();

		// Search through one of the danceTreeMap or tempGroupDetails and replace the
		// words

		for (Entry<String, ArrayList<String>> treeMap : danceAndPerformers_.entrySet()) {
			ArrayList<String> performerNames = treeMap.getValue();
			int numberToIterate = performerNames.size();
			for (int index = 0; index < numberToIterate; index++) {
				if (tempGroupDetails.containsKey(performerNames.get(index))) {
					performerNames.addAll(tempGroupDetails.get(performerNames.get(index)));
					performerNames.remove(index);
					index--;

				}
			}
			//Sorts by alphabetical order
			Collections.sort(performerNames);
		}

	}

	/**
	 * Searching through TreeMap to get the performers in a dance
	 * @param groupName String taken in
	 * @return Returns the ArrayList<String> of performers for the dance
	 * @throws InvalidDanceNameException
	 */
	public ArrayList<String> searchSelectedPerformers(String groupName) throws InvalidDanceNameException {
		if (!danceAndPerformers_.containsKey(groupName)) {
			throw new InvalidDanceNameException();
		}
		return danceAndPerformers_.get(groupName);
	}

	/**
	 * Getter method to allow returning of the private field
	 * @return		danceTreeMap, which is a private variable. 
	 */
	public TreeMap<String, ArrayList<String>> getFullDanceList() {
		return danceAndPerformers_;

	}

}
