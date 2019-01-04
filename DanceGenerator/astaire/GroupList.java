package astaire;

import java.util.ArrayList;
import java.util.TreeMap;

import exceptions.InvalidDanceNameException;
import exceptions.InvalidGroupNameException;

/**
 * Creates a Group with ArrayList of Performers
 * @author Abdullah
 * @version 04/01/2019
 *
 */
public class GroupList {
	
	private TreeMap<String, ArrayList<String>> groupAndPerformers_;
	
	/**
	 * Constructor, Parses danceSHowData_danceGroups.csv file, adds the result to a TreeMap
	 */
	public GroupList() {
		
		ParseCSV groupsCSV = new ParseCSV("astaireDataFiles/danceShowData_danceGroups.csv", true);
		groupAndPerformers_ = new TreeMap<String, ArrayList<String>>();
		
		groupAndPerformers_ = groupsCSV.getTreeMap();
		
	}
	
	/**
	 * Returns the generated TreeMap of group names and its performers
	 * @return		groupAndPerformers_
	 */
	public TreeMap<String, ArrayList<String>> getFullGroup(){
		return groupAndPerformers_;
	}

}
