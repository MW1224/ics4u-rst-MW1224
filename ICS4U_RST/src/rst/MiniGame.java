package rst;

/**
 * The <code>MiniGame</code> class handles the logic of a general mini game and deals with
 * its bonus money.
 */
public class MiniGame {
	// Instance data field
	protected int bonusMoney;
	
	/**
	 * This getter/accessor method returns the MiniGame's bonus money.
	 * 
	 * @return An <code>int</code> containing the MiniGame's bonus money.
	 */
	public int getBonusMoney() {
		return bonusMoney;
	}
	
	/**
	 * Returns the MiniGame's bonus money as output.
	 * 
	 * @return An <code>int</code> containing the MiniGame's bonus money as output.
	 */
	public String showBonusAmount() {
		return "Bonus Money: $" + bonusMoney;	// shows $+/- bonus money
	}
}