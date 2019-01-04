package astaire;

import java.util.ArrayList;
import java.util.Random;
/**
 * Generates a random ArrayList of integers between given numbers for the number of items required 
 * @author Abdullah
 * @version 04/01/2019
 */
public class GenerateRandomNumbers {

	private ArrayList<Integer> randomNumbers_;
	private ArrayList<Integer> listToPickFrom_;
	private Random randomNumber_;
	private int min, max;

	public GenerateRandomNumbers(int minimum, int maximum, int requiredNumber) {
		this.min = minimum;
		this.max = maximum;
		listToPickFrom_ = new ArrayList<Integer>();
		randomNumbers_ = new ArrayList<Integer>();
		randomNumber_ = new Random();
		createListOfNumbers();
		removeWithOutRep(requiredNumber);
	}

	/**
	 * Removes the number of elements required from the list of numbers
	 * adds to a new arrayList 
	 * @param number
	 */
	private void removeWithOutRep(int number) {
		int numberOfElements =number;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = randomNumber_.nextInt(listToPickFrom_.size());
			randomNumbers_.add(min + listToPickFrom_.get(randomIndex));
			listToPickFrom_.remove(randomIndex);
		}
	}

	/**
	 * creates a list of numbers for a given minimum and maximum
	 */
	private void createListOfNumbers() {
		for (int numbers = 0; numbers < max; numbers++) {
			listToPickFrom_.add(numbers);
		}
	}

	/**
	 * returns the list of generated number numbers
	 * @return	randomNumbers_
	 */
	public ArrayList<Integer> randomNumbers() {
		return randomNumbers_;
	}

}
