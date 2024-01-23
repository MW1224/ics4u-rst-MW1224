package rst;

/**
 * The <code>Suitcase</code> class models a suitcase and its weight. Its weight can be adjusted
 * depending on the number of items in it.
 */
public class Suitcase {
	// Class data fields
	public static final double[] WEIGHTS = {2.29, 0.7, 1.81, 1.1, 0.8, 0.7, 0.6, 1.1, 0.1, 0.9,
			1.3, 0.52, 0.4, 2.4, 1.87, 0.3, 0.2, 0.26, 0.2, 0.1, 5.61, 4.12, 1.9, 2.6, 1.29, 0.2,
			0.1, 0.9, 0.56, 0.32, 1.1, 2.71, 1.1, 2.5, 0.2};	// weights of packing items
	public static final int STARTING_WEIGHT = 15;	// suitcase starts with 15 lbs (suitcase's weight itself, toothbrush, etc..)
	
	// Instance data field (suitcase's total weight)
	private double totalWeight;
	
	/**
	 * This default constructor creates a Suitcase with its starting
	 * weight of 15 lbs.
	 */
	public Suitcase() {
		totalWeight = STARTING_WEIGHT;
	}
	
	/**
	 * Updates Suitcase's total weight depending on number of each item packed
	 * 
	 * @param numOfEachItem
	 * 			Number of each packing item that the user packed
	 */
	public void calculateWeight(int[] numOfEachItem) {
		// For each packing item, multiply its quantity with its weight and add it to the suitcase's total weight
		for (int i = 0; i < numOfEachItem.length; i++) {
			totalWeight += (numOfEachItem[i] * WEIGHTS[i]);
		}
	}
	
	/**
	 * This getter/accessor method returns Suitcase's total weight.
	 * 
	 * @return A <code>double</code> containing Suitcase's total weight.
	 */
	public double getWeight() {
		return totalWeight;
	}
}