package rst;

public class ActivitiesScenario extends Scenario {

	public static final String[] ACTIVITIES = {"1h - Solarium Hot Tub", "1 ride - Carousel", "1 climb - Rock Climbing Wall",
			"1 game - Oasis Dunes Mini Golf", "1 game - Archery", "1h - Arcade", "1h - Ice Skating",
			"1h - Vitality at Sea Spa & Fitness Centre:", "1h - FlowRider", "1 ride - ZipLine", "1 game - Laser Tag",
			"1 ride - Perfect Storm Waterslide", "1 ride - Ultimate Abyss Slide", "1 tour - All Access Cruise Tour"};
	
	private int[] numOfEachActivity;
	
	public ActivitiesScenario(int[] numOfEachActivity) {
		this.numOfEachActivity = numOfEachActivity;
		changeInMoney = 0;
	}
	
	public static String showInstructions() {
		return "Pick the number of times you would like to participate in each activity.";
	}
	
	public void calculateCost() {
		for (int i = 0; i < ACTIVITIES.length; i++) {
			changeInMoney -= (i * numOfEachActivity[i]);
		}
	}
	
	public String showChangeInMoney() {
		return "Total cost: $" + (-1 * changeInMoney);
	}
	
}