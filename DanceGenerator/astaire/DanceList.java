package astaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

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
	 */
	public void addRemainingMembers() {
		GroupList groups = new GroupList();
		TreeMap<String,ArrayList<String>> tempGroupDetails = groups.getFullGroup();
		
		//Search through one of the danceTreeMap or tempGroupDetails and replace the words
		
		for(Entry<String, ArrayList<String>> entry : danceTreeMap.entrySet()) {
				ArrayList<String> temp = entry.getValue();
				for(int index = 0; index < temp.size(); index ++) {
					if(tempGroupDetails.containsKey(temp.get(index) )){
//						temp.remove(index);
						temp.addAll(tempGroupDetails.get(temp.get(index)));
//						temp.remove(index);
					}
				}
			Collections.sort(temp);
			}
		
		
	
		
	}
	
	
	public ArrayList<String> getPerformers(String groupName){
		if(!danceTreeMap.containsKey(groupName)) {
			throw new IllegalArgumentException();
		}
		return danceTreeMap.get(groupName);
	}
	
	public TreeMap<String, ArrayList<String>> getFullGroup(){
		return danceTreeMap;
	}
	
	public static void main(String[] args) {
		DanceList newDance = new DanceList();
		System.out.println(newDance.getFullGroup());
//		newDance.addRemainingMembers();
		System.out.println(newDance.getPerformers("A Spoonful of Sugar"));
		System.out.println(newDance.getPerformers("ANY Dream Will Do"));
		System.out.println(newDance.getPerformers("Alice in Disney Land"));
		System.out.println(newDance.getPerformers("Good Morning"));
	}

}
