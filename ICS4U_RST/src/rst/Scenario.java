package rst;

/**
 * The abstract <code>Scenario</code> class contains a general scenario's change in money ($-/+).
 * It contains an abstract method that must be overridden depending on the scenario to show the
 * change in money output.
 */
public abstract class Scenario {
	// Instance data field
	protected int changeInMoney;	// contains the change in money that a scenario results in
	
	/**
	 * This getter/accessor method returns the scenario's change in money.
	 * 
	 * @return An <code>int</code> containing the scenario's change in money.
	 */
	public int getChangeInMoney() {
		return changeInMoney;
	}
	
	/**
	 * This abstract method will return the change in money in a sentence for output, depending on
	 * the specific scenario subclass; therefore, it must be overridden.
	 * 
	 * @return A <code>String</code> containing the change in money in a sentence for output.
	 */
	public abstract String showChangeInMoney();
}