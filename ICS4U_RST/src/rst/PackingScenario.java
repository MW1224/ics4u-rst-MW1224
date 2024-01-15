package rst;

public class PackingScenario extends Scenario {
	
	public static final int WEIGHT_LIMIT = 40;
	public static final String[] ITEMS = {"Jackets", "Shorts", "Jeans", "Pants", "Dresses", "Skirts", "Tops",
			"Books", "Pairs of Socks", "Pyjama Sets", "Pairs of Shoes", "Scarves", "Hats", "Cell Phones",
			"Empty Backpacks", "Makeup Products", "Lotion Tubes", "Bug Spray Bottles", "Hand Sanitizer Bottles",
			"Bandaid Boxes", "Computers", "Tablets/iPads", "Portable Chargers", "Cameras", "Headphones",
			"Earphones", "Sunglasses", "Travel Games", "Snorkels", "Watches", "Swimsuits", "Suit Sets", "Towels",
			"Water Bottles", "Hair Accessories"};
	
	private Suitcase suitcase;
	
	public PackingScenario() {
		suitcase = new Suitcase();
	}
	
	public static String[] getItems() {
		String[] items = new String[ITEMS.length];
		
		return items;
	}
	
	public static String showInstructions() {
		return "Below is a list of possible items to pack with their corresponding weights\n(per 1 item) in pounds. "
				+ "Enter the number of each item you would like to\npack for your two-day trip. Your goal is to "
				+ "keep your suitcase below the\nweight limit. Each suitcase starts with 15 lbs (suitcase itself, "
				+ "toothbrush, other essentials).";
	}
	
	public static String showWeightLimit() {
		return "Weight Limit: " + WEIGHT_LIMIT + " lbs";
	}
	
	public String checkWeight(int[] numOfEachItem) {
		String result;
		
		suitcase.calculateWeight(numOfEachItem);
		
		double weight = suitcase.getWeight();
		if (weight > WEIGHT_LIMIT) {
			changeInMoney = -10;
			result = "Oh no! Your suitcase is " + (weight - WEIGHT_LIMIT) + " lbs over the weight limit.";
		} else if (weight < WEIGHT_LIMIT) {
			changeInMoney = 10;
			result = "Good job! Your suitcase is " + (WEIGHT_LIMIT - weight) + " lbs under the weight limit.";
		} else {
			changeInMoney = 0;
			result = "Your suitcase is right on the weight limit of " + WEIGHT_LIMIT + " lbs.";
		}
		
		return result;
	}
	
	public double getWeight() {
		return suitcase.getWeight();
	}
	
	public String showChangeInMoney() {
		if (changeInMoney < 0) {
			return "You lost $10!";
		} else if (changeInMoney > 0) {
			return "You got a $10 bonus!";
		} else {
			return "You didn't win or lose any money.";
		}
	}
	
	public String toString() {
		return "Packing Game";
	}
}