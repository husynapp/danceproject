package astaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.HashMap;

/**
 * DanceGroup Object, to return all of the dancers in a specified group
 * 
 * @author Abdullah Hussain
 * @version 27/11/2018
 */
public class DanceGroups {

	/**
	 * Group name and all performers are stored as a HashMap
	 */
	private TreeMap<String, ArrayList<String>> groupAndPerformers;
	
	 ;

	/**
	 * Constructor
	 * 
	 * @param filePath Location to the CSV file
	 */
	public DanceGroups(String filePath) {
		groupAndPerformers = new TreeMap<String, ArrayList<String>>();
		readFile(filePath);
	}

	/**
	 * Reading and Parsing the file
	 * 
	 * @param fileName Location to the CSV file
	 */
	public void readFile(String fileName) {
		String performerList;
		String tempDance;
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = br.readLine();
			boolean firstLineRead = false;

			while (line != null) {
				if (firstLineRead == false) {
					line = br.readLine();
					firstLineRead = true;
				}

				String[] attributes = line.split("\t");

				tempDance = attributes[0];
				tempDance = tempDance.trim();
				performerList = attributes[1];
				addToTreeMap(tempDance, performerList);

				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Method splits the performers by comma and adds them to the HashMap
	 * 
	 * @param tempDance  Dance group name
	 * @param performers List of all performers as String with comma separated
	 */
	private void addToTreeMap(String tempDance, String performers) {
		ArrayList<String> performerArrayList = new ArrayList<String>();

		//Dance newDance = new Dance(tempDance);
		String[] performArray = performers.split(", ");
		Arrays.sort(performArray);
		for (int i = 0; i < performArray.length; i++) {
			performerArrayList.add(performArray[i]);
		}

		groupAndPerformers.put(tempDance, performerArrayList);

	}

	/**
	 * @return HashMap of all group and performers
	 */
	public ArrayList<String> getGroupDetails(String groupName) {
	
			return groupAndPerformers.get(groupName);
	
	}

	/**
	 * Prints out all the dance groups and their respective performers
	 */
	public void groupDetails() {
		for (String group : groupAndPerformers.keySet()) {
			String key = group;
			String value = groupAndPerformers.get(group).toString();
			System.out.println(key+ value);
		}
	}
	

	/**
	 * Allows to get the full hashmap where the Group and their performers are
	 * stored.
	 * 
	 * @return groupAndPerformers
	 */
	public TreeMap<String, ArrayList<String>> getFullDetails() {
		return groupAndPerformers;
	}

}
