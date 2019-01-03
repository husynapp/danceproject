package astaire;

import java.util.ArrayList;
import java.util.Random;

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

	public void removeWithOutRep(int number) {
		int numberOfElements =number;

		for (int i = 0; i < numberOfElements; i++) {
			int randomIndex = randomNumber_.nextInt(listToPickFrom_.size());
			randomNumbers_.add(min + listToPickFrom_.get(randomIndex));
			listToPickFrom_.remove(randomIndex);
		}
	}

	public void createListOfNumbers() {
		for (int numbers = 0; numbers < max; numbers++) {
			listToPickFrom_.add(numbers);
		}
	}

	public ArrayList<Integer> randomNumbers() {
		return randomNumbers_;
	}

}
