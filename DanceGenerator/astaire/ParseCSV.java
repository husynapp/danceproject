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

public class ParseCSV {
	
	private TreeMap<String, ArrayList<String>> treeMap;
	
	public ParseCSV(String fileName) {
		treeMap = new TreeMap<String, ArrayList<String>>();
		readFile(fileName);
	}
	
	
	public void readFile(String fileName) {
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
//				firstColoumn = firstColoumn.trim();
				secondColoumn = attributes[1];
//				secondColoumn = secondColoumn.trim();
						
				addToTreeMap(firstColoumn, secondColoumn);

				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	
	private void addToTreeMap(String name, String performers) {
		ArrayList<String> arrayList = new ArrayList<String>();

		//Dance newDance = new Dance(tempDance);
		String[] performArray = performers.split(",");
		
		Arrays.sort(performArray);
		for (int i = 0; i < performArray.length; i++) {
			
			arrayList.add(performArray[i].trim());
		}

		treeMap.put(name.trim(), arrayList);
	}
	
	public TreeMap<String, ArrayList<String>> getTreeMap(){
		return treeMap;
	}


}
