package astaire;

import java.util.ArrayList;
import java.util.TreeMap;
/**
 * Generates a order of dance, which is then passed on
 * @author Abdullah
 * @version 03/02/2019
 */
public class RunningOrderGenerator {

	private GenerateRandomNumbers orderCreated_;
	private ArrayList<String> danceListToTest_;

	/**
	 * Constructor initiates the fields
	 * @param min				minimum number for random order generating
	 * @param max				maximum number of items
	 * @param requiredNumber	number of items required in the random order
	 */
	public RunningOrderGenerator(int min, int max, int requiredNumber) {
		danceListToTest_ = new ArrayList<String>();
		orderCreated_ = new GenerateRandomNumbers(min, max, requiredNumber);
		generateRunningOrder();
	}

	/**
	 * generates the running order
	 */
	private void generateRunningOrder() {
		DanceList tempDanceList = new DanceList();
		TreeMap<String, ArrayList<String>> list = tempDanceList.getFullDanceList();
		ArrayList<String> keyset = new ArrayList<String>(list.keySet());
		ArrayList<Integer> order = orderCreated_.randomNumbers();
		for (int number = 0; number < order.size(); number++) {
			danceListToTest_.add(keyset.get(order.get(number)));
		}
	}
	
	/**
	 *  Order that has been created is returned
	 * @return		danceListToTest_ 
	 */
	public ArrayList<String> getdanceListToTest(){
		return danceListToTest_;
	}

}
