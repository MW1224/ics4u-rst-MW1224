package rst;

public class Suitcase {

	public static final double[] WEIGHTS = {2.29, 0.7, 1.81, 1.1, 0.8, 0.7, 0.6, 1.1, 0.1, 0.9,
			1.3, 0.52, 0.4, 2.4, 1.87, 0.3, 0.2, 0.26, 0.2, 0.1, 5.61, 4.12, 1.9, 2.6, 1.29, 0.2,
			0.1, 0.9, 0.56, 0.32, 1.1, 2.71, 1.1, 2.5, 0.2};
	public static final int STARTING_WEIGHT = 15;
	
	private double totalWeight;
	
	public Suitcase() {
		totalWeight = STARTING_WEIGHT;
	}
	
	public void calculateWeight(int[] numOfEachItem) {
		for (int i = 0; i < numOfEachItem.length; i++) {
			totalWeight += (numOfEachItem[i] * WEIGHTS[i]);
		}
	}
	
	public double getWeight() {
		return totalWeight;
	}
}