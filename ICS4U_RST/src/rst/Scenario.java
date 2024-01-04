package rst;

/**
 * The abstract <code>Scenario</code> class
 */
public abstract class Scenario {

	// Instance data field containing the cost a scenario results in
	protected int changeInMoney;
	
	public int getChangeInMoney() {
		return changeInMoney;
	}
	
	public abstract String showChangeInMoney();
}