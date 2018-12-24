package astaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;

public class DanceAndPerformers {
	private TreeMap<String, ArrayList<String>> dancesAndPerformers;
	
	public DanceAndPerformers(String fileName) {
		dancesAndPerformers = new TreeMap<String, ArrayList<String>>();
		readFile(fileName);
		
	}
	
	public void readFile(String fileName) {
		String performerList;
		String danceName;
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

				danceName = attributes[0];
				danceName = danceName.trim();
				performerList = attributes[1];
				addToTreeMap(danceName, performerList);

				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	
	public void addToTreeMap(String danceName, String performers) {
		
	}

	public static void main(String[] args) {
		DanceAndPerformers dances = new DanceAndPerformers("astaireDataFiles/danceShowData_dances.csv"); 

	}

}
