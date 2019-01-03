package astaire;

import java.util.ArrayList;
import java.util.TreeMap;

import exceptions.InvalidDanceNameException;
import exceptions.InvalidGroupNameException;

/**
 * Creates a Group with ArrayList of Performers
 * @author Abdullah
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
	 * Getter Method to return performers with specific group name
	 * @param groupName	name of the group for which performers are returned
	 * @return		performers in a group
	 * @throws InvalidDanceNameException 
	 * @throws InvalidGroupNameException 
	 */
//	public ArrayList<String> getSelectedPerformers(String groupName) throws InvalidGroupNameException{
//		
//		if(!groupAndPerformers_.containsKey(groupName)) {
//			throw new InvalidGroupNameException();
//		} else {
//			return groupAndPerformers_.get(groupName);
//		}
//	}
	
	public TreeMap<String, ArrayList<String>> getFullGroup(){
		return groupAndPerformers_;
	}

}
