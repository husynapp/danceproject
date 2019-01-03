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
	
	private void addToArrayList(String danceName) {
		this.danceNames_.add(danceName.trim());
	}
	
	private void addToTreeMap(String name, String performers) {
		ArrayList<String> arrayList = new ArrayList<String>();


		String[] performArray = performers.split(",");
		
		Arrays.sort(performArray);
		for (int i = 0; i < performArray.length; i++) {
			
			arrayList.add(performArray[i].trim());
		}

		treeMapOfPerformers_.put(name.trim(), arrayList);
	}
	
	public TreeMap<String, ArrayList<String>> getTreeMap(){
		return treeMapOfPerformers_;
	}
	
	public ArrayList<String> getDanceNames(){
		return danceNames_;
	}


}
