package rst;

/**
 * The <code>ActivitiesScenario</code> class, a subclass of the Scenario class, handles the logic of
 * an activities scenario. It calculates the change in money based on the number of times each activity
 * is performed by the passenger.
 */
public class ActivitiesScenario extends Scenario {

	// Class data field that contains all possible activities for user to be involved in
	public static final String[] ACTIVITIES = {"1h - Solarium Hot Tub", "1 ride - Carousel", "1 climb - Rock Climbing Wall",
			"1 game - Oasis Dunes Mini Golf", "1 game - Archery", "1h - Arcade", "1h - Ice Skating",
			"1h - Vitality at Sea Spa & Fitness Centre:", "1h - FlowRider", "1 ride - ZipLine", "1 game - Laser Tag",
			"1 ride - Perfect Storm Waterslide", "1 ride - Ultimate Abyss Slide", "1 tour - All Access Cruise Tour"};
	
	// Instance data field that contains the number of times the user performs each activity
	private int[] numOfEachActivity;
	
	/**
	 * This constructor creates an ActivitiesScenario with the specified number of times
	 * each activity is performed. Also, sets change in money for this scenario to $0.
	 */
	public ActivitiesScenario(int[] numOfEachActivity) {
		this.numOfEachActivity = numOfEachActivity;
		changeInMoney = 0;		// set change in money to $0
	}
	
	/**
	 * This class method returns a string that contains the ActivitiesScenario's instructions.
	 * 
	 * @return A <code>String</code> containing the ActivitiesScenario's instructions.
	 */
	public static String showInstructions() {
		return "Pick the number of times you would like to participate in each activity.";
	}
	
	/**
	 * Calculates the change in money based on the number of times each activity is performed.
	 */
	public void calculateCost() {
		// Use an algorithm to calculate the cost
		for (int i = 0; i < ACTIVITIES.length; i++) {
			// The total cost of an activity is found by multiplying the cost (i, which represents
			// its cost ($0-$13 for all 14 activities)) by the number of times the activity was performed.
			changeInMoney -= (i * numOfEachActivity[i]);	// subtract the activity's total cost from the change in money
		}
	}
	
	/**
	 * Returns a string that shows the total cost of the activities performed.
	 * 
	 * @return A <code>String</code> representing the total cost of the activities performed.
	 */
	public String showChangeInMoney() {
		return "Total cost: $" + (-1 * changeInMoney);	// multiply the negative changeInMoney value by 1 to make it positive for output
	}	
}