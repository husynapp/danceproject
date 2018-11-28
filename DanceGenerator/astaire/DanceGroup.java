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

/**
 * DanceGroup Object, to return all of the dancers in a specified group
 * 
 * @author Abdullah Hussain
 * @version 27/11/2018
 */
public class DanceGroup {

	/**
	 * Group name and all performers are stored as a HashMap
	 */
	private static HashMap<Dance, ArrayList<Performer>> groupAndPerformers;

	/**
	 * Constructor
	 * 
	 * @param filePath Location to the CSV file
	 */
	public DanceGroup(String filePath) {
		groupAndPerformers = new HashMap<Dance, ArrayList<Performer>>();
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
				performerList = attributes[1];
				addToHashMap(tempDance, performerList);

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
	private void addToHashMap(String tempDance, String performers) {
		ArrayList<Performer> performerArrayList = new ArrayList<Performer>();

		Dance newDance = new Dance(tempDance);
		String[] performArray = performers.split(",");
		Arrays.sort(performArray);
		for (int i = 0; i < performArray.length; i++) {
			performerArrayList.add(new Performer(performArray[i]));
		}

		groupAndPerformers.put(newDance, performerArrayList);

	}

	/**
	 * @return HashMap of all group and performers
	 */
	public ArrayList<Performer> getGroupDetails(String groupName) {
		Dance temp = new Dance(groupName);
		groupAndPerformers.containsKey(temp.getDanceName());
		return groupAndPerformers.get(temp);
	}

	/**
	 * Prints out all the dance groups and their respective performers
	 */
	public void groupDetails() {
		for (Dance group : groupAndPerformers.keySet()) {
			String key = group.getDanceName();
			String value = groupAndPerformers.get(group).toString();
			System.out.println("Group name: " + key + "\t Performers: " + value);
		}
	}

	public static void main(String[] args) {
		DanceGroup x = new DanceGroup("astaire/danceShowData_danceGroups.csv");
		x.groupDetails();
		System.out.println(x.getGroupDetails("Juniors"));
	}

}
