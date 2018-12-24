package astaire;

import java.util.ArrayList;
import java.util.TreeMap;

public class DanceList {
	
	private TreeMap<String, ArrayList<String>> danceTreeMap;
	
	
	
	public DanceList() {
		ParseCSV danceCSV = new ParseCSV("astaireDataFiles/danceShowData_dances.csv");
		danceTreeMap = new TreeMap<String, ArrayList<String>>();
		danceTreeMap = danceCSV.getTreeMap();
	
	}
	
	/**
	 * replace the "juniors" "seniors" stuff to actual names of people in the group
	 */
	private void addRemainingMembers() {
		GroupList groups = new GroupList();
		TreeMap<String,ArrayList<String>> tempGroupDetails = groups.getFullGroup();
		
		//Search through one of the danceTreeMap or tempGroupDetails and replace the words
		
		
		
		
	
		
	}
	
	
	public ArrayList<String> getPerformers(String groupName){
		
		return danceTreeMap.get(groupName);
	}
	
	public TreeMap<String, ArrayList<String>> getFullGroup(){
		return danceTreeMap;
	}
	
	public static void main(String[] args) {
		DanceList newDance = new DanceList();
		System.out.println(newDance.getFullGroup());
		System.out.println(newDance.getPerformers("A Spoonful of Sugar"));
	}

}
