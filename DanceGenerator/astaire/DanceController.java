package astaire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import exceptions.InvalidDanceNameException;

/**
 * Implementation of the interface Controller.
 * 
 * @author Abdullah
 * @version 02/01/2019
 */

public class DanceController implements Controller {
	private DanceList dances;

	public DanceController() throws InvalidDanceNameException {
		dances = new DanceList();
	}

	@Override
	public String listAllDancersIn(String dance) throws InvalidDanceNameException {
		String dancers = dances.searchSelectedPerformers(dance).toString();
		String[] splitResult = dancers.split(",");
		String result = "Performers in " + dance + " are: \n";
		int tabCount = 1;
		for (int index = 0; index < splitResult.length; index++) {
			result += splitResult[index];
			result += "\n";
			for (int j = 0; j < tabCount; j++) {
				result += "\t";
			}

			tabCount++;
		}

		return result;

	}

	@Override
	public String listAllDancesAndPerformers() {

		return formatText();
	}

	/**
	 * Helper method to print data
	 * 
	 * @return Formatted String where it's more readable
	 */
	private String formatText() {
		String list = dances.getFullDanceList().toString();
		String[] split = list.split("], ");
		String result = "";
		for (int index = 0; index < split.length; index++) {
			result += split[index];
			result += "]\n";
		}
		return result;
	}

	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) throws InvalidDanceNameException {
		FeasibilityChecker feasibility = new FeasibilityChecker();

		feasibility.addToArrayListCSV(filename);
		feasibility.feasiblityChecker(gaps);
		return feasibility.danceWithProblem();

	}

	@Override
	public String generateRunningOrder(int gaps, int numberToGenerate) throws InvalidDanceNameException, IOException {
		String result = "";
		long startTime = (new Date()).getTime();
		long elapsedTime = 0;

        File file = new File("outputText.txt");
        FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        BufferedWriter bw = new BufferedWriter(fw);
		boolean isComplete = false;
		while (isComplete == false && elapsedTime < 30000) {
			FeasibilityChecker feasibility = new FeasibilityChecker();
			RunningOrderTester runOrder = new RunningOrderTester(0, dances.getFullDanceList().size(), numberToGenerate);
			feasibility.addToArrayWithoutCSV(runOrder.getdanceListToTest());
			feasibility.feasiblityChecker(gaps);
			isComplete = feasibility.suitable();
			result = feasibility.danceWithProblem();

			long endTime = (new Date()).getTime();
			elapsedTime = endTime - startTime;
			System.out.println("Generating random order. Checking for feasiblity");
			System.out.println(feasibility.getGeneratedOrder().toString());
			System.out.println("Time elapsed so far: " + elapsedTime + " ms\n");
			try {

			        // if file doesnt exists, then create it
		        if (!file.exists()) {
		            file.createNewFile();
		        }

		
		        bw.write("Generating random order. Checking for feasiblity" + System.getProperty( "line.separator"));
		        bw.write(feasibility.getGeneratedOrder().toString() +  System.getProperty( "line.separator"));
		        bw.write("Time elapsed so far: " + elapsedTime + " ms\n" + System.getProperty( "line.separator"));
		        

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		bw.write(result);
		
		bw.close();
		return result;
		
	}

}
