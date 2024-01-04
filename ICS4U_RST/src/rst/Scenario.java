package rst;

/**
 * The abstract <code>Scenario</code> class
 */
public abstract class Scenario {

	// Instance data field containing the cost a scenario results in
	protected int cost;
	
	protected int getCost() {
		return cost;
	}
	
	protected abstract String showCost();
}