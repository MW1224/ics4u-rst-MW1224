package rst;

/**
 * The <code>PackingScenario</code> class, a subclass of the <code>Scenario</code> class, handles
 * the logic of a packing scenario. It calculates the user's suitcase's weight, then the change in
 * money based on the weight of the suitcase (if it is overweight, on the limit or under weight).
 */
public class PackingScenario extends Scenario {
	// Class data fields
	public static final int WEIGHT_LIMIT = 40;			// weight limit of suitcase is always 40 lbs
	public static final int NUM_OF_ITEMS = 35;			// number of distinct items to pack
	private static final int FEE = -15, BONUS = 10;		// fee of 15$ if overweight suitcase, bonus of 10$ if under
	
	// Instance data field
	private Suitcase suitcase;	// reference for global Suitcase object
	
	/**
	 * This constructor creates a PackingScenario with a cost/bonus of $0. Also,
	 * instantiates a Suitcase object using its default constructor.
	 */
	public PackingScenario() {
		suitcase = new Suitcase();
		changeInMoney = 0;			// set change in money to $0
	}
	
	/**
	 * This class method returns a string array that contains the possible items to pack with their
	 * corresponding weights in pounds (lbs). This is the same for all packing scenarios.
	 * 
	 * @return A <code>String</code> array containing the Cruise Story Game's goal & winning circumstances.
	 */
	public static String[] getItems() {
		// Local constant (packing items' names)
		final String[] ITEMS = {"Jackets", "Shorts", "Jeans", "Pants", "Dresses", "Skirts", "Tops",
				"Books", "Pairs of Socks", "Pyjama Sets", "Pairs of Shoes", "Scarves", "Hats", "Cell Phones",
				"Empty Backpacks", "Makeup Products", "Lotion Tubes", "Bug Spray Bottles", "Hand Sanitizer Bottles",
				"Bandaid Boxes", "Computers", "Tablets/iPads", "Portable Chargers", "Cameras", "Headphones",
				"Earphones", "Sunglasses", "Travel Games", "Snorkels", "Watches", "Swimsuits", "Suit Sets", "Towels",
				"Water Bottles", "Hair Accessories"};
		
		// Array to hold the updated list of packing items with their weights in lbs
		String[] itemsWithWeights = new String[NUM_OF_ITEMS];
		
		// Loop through all items in ITEMS array
		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			// Set the element at position i in local array to the item's name & its corresponding weight
			// (The WEIGHTS array in the Suitcase has the weights in the same order as the ITEMS array)
			itemsWithWeights[i] = ITEMS[i] + " (" + Suitcase.WEIGHTS[i] + "):";
		}
		
		return itemsWithWeights;
	}
	
	/**
	 * This class method returns a string that contains the PackingScenario's instructions, overview and goal.
	 * 
	 * @return A <code>String</code> containing the PackingScenario's instructions, overview and goal.
	 */
	public static String showInstructions() {
		return "Below is a list of possible items to pack with their corresponding weights (per 1 item) in pounds. "
				+ "Enter the number of each item you would like to pack for your two-day trip. Your goal is to "
				+ "keep your suitcase below the weight limit. Each suitcase starts with " + Suitcase.STARTING_WEIGHT
				+ " lbs (suitcase itself, toothbrush, other essentials).";
	}
	
	/**
	 * This class method returns the suitcase's weight limit in a phrase for output.
	 * 
	 * @return A <code>String</code> containing the suitcase's weight limit in a phrase for output.
	 */
	public static String showWeightLimit() {
		return "Weight Limit: " + WEIGHT_LIMIT + " lbs";
	}
	
	/**
	 * Checks if the suitcase's weight is on, over or under the weight limit of 40 lbs. Returns
	 * a message indicating the corresponding result.
	 * 
	 * @param numOfEachItem
	 * 			The number of each item the user packs.
	 * 
	 * @return A <code>String</code> containing the result of the suitcase's weight in regards
	 * to the weight limit.
	 */
	public String checkWeight(int[] numOfEachItem) {
		String result;	// local variable to hold the result output
		
		// Call the suitcase's method to calculate & update its total weight
		suitcase.calculateWeight(numOfEachItem);
		double weight = suitcase.getWeight();
		
		// Compare suitcase's weight with weight limit and add corresponding result output (with how much the difference the two weights)
		if (weight > WEIGHT_LIMIT) {			// if over weight limit
			changeInMoney = FEE;	// set the changeInMoney to the fee of $-15
			result = "Oh no! Your suitcase is " + ((double)Math.round((weight - WEIGHT_LIMIT) * 100)/100) + " lbs over the weight limit.";
		} else if (weight < WEIGHT_LIMIT) {		// if under weight limit
			changeInMoney = BONUS;	// set the changeInMoney to the bonus of $10
			result = "Good job! Your suitcase is " + ((double)Math.round((WEIGHT_LIMIT - weight) * 100)/100) + " lbs under the weight limit.";
		} else {		// if right on weight limit
			result = "Your suitcase is right on the weight limit of " + WEIGHT_LIMIT + " lbs.";
		}
		
		return result;
	}
	
	/**
	 * This getter/accessor method returns the suitcase's weight.
	 * 
	 * @return A <code>double</code> containing the suitcase's weight.
	 */
	public double getWeight() {
		return (double)Math.round(suitcase.getWeight() * 100)/100;
	}
	
	/**
	 * This overridden method returns a string that shows the change in money from the scenario.
	 * The message depends on scenario's change in money (over, on or under weight limit)
	 * 
	 * @return A <code>String</code> representing the change in money from the scenario.
	 */
	public String showChangeInMoney() {
		if (changeInMoney < 0) {	// if suitcase was over the weight limit (resulted in negative change in money)
			return "You lost $" + (-1 * FEE) + "!";	// multiply fee by -1 to show the number but without the negative symbol
		} else if (changeInMoney > 0) {		// if suitcase was under the weight limit (resulted in positive change in money)
			return "You got a $" + BONUS + " bonus!";
		} else {	// if suitcase was right on the weight limit of 40 lbs.
			return "You didn't win or lose any money.";
		}
	}
	
	/**
	 * This overridden method returns a string that represents the PackingScenario.
	 * 
	 * @return A <code>String</code> representing the PackingScenario.
	 */
	public String toString() {
		return "Packing Scenario";
	}
}