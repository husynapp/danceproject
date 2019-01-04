package astaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
/**
 * 
 * @author Abdullah
 * @version 03/01/2019
 */
public class ParseCSV {
	
	private TreeMap<String, ArrayList<String>> treeMapOfPerformers_;
	private ArrayList<String> danceNames_;
	
	public ParseCSV(String fileName, boolean isRunningOrder) {
		treeMapOfPerformers_ = new TreeMap<String, ArrayList<String>>();
		danceNames_ =  new ArrayList<String>();
		readFile(fileName, isRunningOrder);
	}
	
	/**
	 * Reads the text line by line from a CSV file and calls helper method to add to 
	 * required data type
	 * @param fileName			takes in the file name
	 * @param normalFunction	takes in a boolean value if the function adds everything normally
	 * or just adds the dance names
	 */
	public void readFile(String fileName, boolean normalFunction) {
		String secondColoumn;
		String firstColoumn;
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

				firstColoumn = attributes[0];
				secondColoumn = attributes[1];

				/**
				 * Decides according to boolean value if it should add both first coloumn and
				 * second coloumn, or just first coloumn as the dance names to the arrayList		
				 */
				if(normalFunction==true) {
					addToTreeMap(firstColoumn, secondColoumn);
				}else {
					addToArrayList(firstColoumn);
				}

				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	/**
	 * Either this or addToTreeMap is ran depending on the option required.
	 * Adds only the dance names to the ArrayList
	 * @param danceName	name of the dances
	 */
	private void addToArrayList(String danceName) {
		this.danceNames_.add(danceName.trim());
	}
	
	/**
	 * private helper method to add groupname/dance name to the treemap
	 * @param name		name of the group/ or dance name
	 * @param performers	performers for above 
	 */
	private void addToTreeMap(String name, String performers) {
		ArrayList<String> arrayList = new ArrayList<String>();


		String[] performArray = performers.split(",");
		
		Arrays.sort(performArray);
		for (int i = 0; i < performArray.length; i++) {
			
			arrayList.add(performArray[i].trim());
		}

		treeMapOfPerformers_.put(name.trim(), arrayList);
	}
	
	/**
	 * returns the generated TreeMap of names and its performers
	 * @return		treeMapOfPerformers_ 
	 */
	public TreeMap<String, ArrayList<String>> getTreeMap(){
		return treeMapOfPerformers_;
	}
	
	/**
	 * Returns an ArrayList of Dance names
	 * @return		danceNames_
	 */
	public ArrayList<String> getDanceNames(){
		return danceNames_;
	}


}
