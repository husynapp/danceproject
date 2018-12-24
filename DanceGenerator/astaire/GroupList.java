package astaire;

import java.util.ArrayList;
import java.util.TreeMap;

public class GroupList {
	
	private TreeMap<String, ArrayList<String>> dancesTreeMap;
	
	public GroupList() {
		
		ParseCSV groupsCSV = new ParseCSV("astaireDataFiles/danceShowData_danceGroups.csv");
		dancesTreeMap = new TreeMap<String, ArrayList<String>>();
		
		dancesTreeMap = groupsCSV.getTreeMap();
		
	}
	
	public ArrayList<String> getPerformers(String groupName){
		
		return dancesTreeMap.get(groupName);
	}
	
	public TreeMap<String, ArrayList<String>> getFullGroup(){
		return dancesTreeMap;
	}

}
