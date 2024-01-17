package rst;

public class PackingScenario extends Scenario {
	
	public static final int WEIGHT_LIMIT = 40;
	public static final int NUM_OF_ITEMS = 35;
	private static final int FEE = -15, BONUS = 10;
	
	private Suitcase suitcase;
	
	public PackingScenario() {
		suitcase = new Suitcase();
		changeInMoney = 0;
	}
	
	public static String[] getItems() {
		final String[] ITEMS = {"Jackets", "Shorts", "Jeans", "Pants", "Dresses", "Skirts", "Tops",
				"Books", "Pairs of Socks", "Pyjama Sets", "Pairs of Shoes", "Scarves", "Hats", "Cell Phones",
				"Empty Backpacks", "Makeup Products", "Lotion Tubes", "Bug Spray Bottles", "Hand Sanitizer Bottles",
				"Bandaid Boxes", "Computers", "Tablets/iPads", "Portable Chargers", "Cameras", "Headphones",
				"Earphones", "Sunglasses", "Travel Games", "Snorkels", "Watches", "Swimsuits", "Suit Sets", "Towels",
				"Water Bottles", "Hair Accessories"};
		String[] itemsWithWeights = new String[NUM_OF_ITEMS];
		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			itemsWithWeights[i] = ITEMS[i] + " (" + Suitcase.WEIGHTS[i] + "):";
		}
		return itemsWithWeights;
	}
	
	public static String showInstructions() {
		return "Below is a list of possible items to pack with their corresponding weights (per 1 item) in pounds. "
				+ "Enter the number of each item you would like to pack for your two-day trip. Your goal is to "
				+ "keep your suitcase below the weight limit. Each suitcase starts with 15 lbs (suitcase itself, "
				+ "toothbrush, other essentials).";
	}
	
	public static String showWeightLimit() {
		return "Weight Limit: " + WEIGHT_LIMIT + " lbs";
	}
	
	public String checkWeight(int[] numOfEachItem) {
		String result;
		
		suitcase.calculateWeight(numOfEachItem);
		
		double weight = Math.round(suitcase.getWeight() * 100)/100;
		if (weight > WEIGHT_LIMIT) {
			changeInMoney = FEE;
			result = "Oh no! Your suitcase is " + (weight - WEIGHT_LIMIT) + " lbs over the weight limit.";
		} else if (weight < WEIGHT_LIMIT) {
			changeInMoney = BONUS;
			result = "Good job! Your suitcase is " + (WEIGHT_LIMIT - weight) + " lbs under the weight limit.";
		} else {
			result = "Your suitcase is right on the weight limit of " + WEIGHT_LIMIT + " lbs.";
		}
		
		return result;
	}
	
	public double getWeight() {
		return Math.round(suitcase.getWeight() * 100)/100;
	}
	
	public String showChangeInMoney() {
		if (changeInMoney < 0) {
			return "You lost $" + (-1 * FEE) + "!";
		} else if (changeInMoney > 0) {
			return "You got a $" + BONUS + " bonus!";
		} else {
			return "You didn't win or lose any money.";
		}
	}
	
	public String toString() {
		return "Packing Game";
	}
}