package rst;

/**
 * The <code>Lock</code> class models a lock. It contains the lock's clue, hint, combination
 * (combo), state, lock number and hint count. It can be unlocked.
 */
public class Lock {
	// Instance data fields
	private String clue, hint, combo;
	private boolean state;	// false if locked, true if unlocked
	private int lockNumber, hintCount;
	
	/**
	 * This constructor creates a Lock with its specified clue, hint,
	 * combination and lock number; false state (locked); and a hint count of 0
	 * 
	 * @param lockClue
	 * 			Lock's clue (a broad hint as to what the combination is)
	 * 
	 * @param lockHint
	 * 			Lock's hint (a clear hint as to what the combination is)
	 * 
	 * @param lockCombo
	 * 			Lock's correct combination
	 * 
	 * @param lockNum
	 * 			Lock's number (relating the other locks)
	 */
	public Lock(String lockClue, String lockHint, String lockCombo, int lockNum) {
		clue = lockClue;
		hint =lockHint;
		state = false;
		combo = lockCombo;
		lockNumber = lockNum;
		hintCount = 0;
	}
	
	/**
	 * This overloaded constructor creates a Lock with its specified clue, hint
	 * and lock number; a null combination; false state (locked); and
	 * a hint count of 0.
	 * 
	 * @param lockClue
	 * 			Lock's clue (a broad hint as to what the combination is)
	 * 
	 * @param lockHint
	 * 			Lock's hint (a clear hint as to what the combination is)
	 * 
	 * @param lockNum
	 * 			Lock's number (relating the other locks)
	 */
	public Lock(String lockClue, String lockHint, int lockNum) {
		clue = lockClue;
		hint = lockHint;
		state = false;
		combo = null;
		lockNumber = lockNum;
		hintCount = 0;
	}
	
	/**
	 * This setter/mutator method sets the lock's combination.
	 * 
	 * @param newCombo
	 * 			The lock's combination.
	 */
	public void setCombo(String newCombo) {
		combo = newCombo;
	}
	
	/**
	 * Uses given comboAttempt to try to open (only if this lock is the actual lock that
	 * needs to be unlocked). Adjusts this Lock's state to true if it unlocked and false if
	 * it locked.
	 * 
	 * @param comboAttempt
	 * 			The combination attempt by the user.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>String</code> representing the passenger's amount of money left.
	 */
	public boolean unlock(String comboAttempt, int actualLockNum) {
		// Lock can only be unlocked if the combination entered by user is matches its combination
		// AND its lock number matches the actual lock number
		if (comboAttempt.equals(combo) && lockNumber == actualLockNum) {	 // if able to unlock
			state = true;	// set state to unlocked
			return true;
		} else {	// if unable to unlock
			state = false;	// set state to locked
			return false;
		}
	}
	
	/**
	 * This getter/accessor method returns the lock's state. (true if
	 * unlocked, false if locked)
	 * 
	 * @return A <code>boolean</code> containing the lock's state.
	 */
	public boolean isOpen() {
		return state;
	}
	
	/**
	 * This getter/accessor method returns the lock's state as a String.
	 * 
	 * @return A <code>String</code> representing the lock's state.
	 */
	public String getStrState() {
		if (state) {	// if unlocked (true state)
			return "UNLOCKED";
		} else {		// if locked (false state)
			return "LOCKED";
		}
	}
	
	/**
	 * This getter/accessor method returns the lock's combination.
	 * 
	 * @return A <code>String</code> containing the lock's combination.
	 */
	public String getCombo() {
		return combo;
	}
	
	/**
	 * This getter/accessor method returns the lock's clue.
	 * 
	 * @return A <code>String</code> containing the lock's clue.
	 */
	public String getClue() {
		return clue;
	}
	
	/**
	 * Returns lock's hint eligibility (depending on hint count). Lock can only
	 * provide another hint if it hasn't already given its hint. 
	 * 
	 * @return A <code>boolean</code> containing the lock's hint eligibility.
	 */
	public boolean isEligibleForHint() {
		if (hintCount == 0) {	// if hint hasn't been given yet
			return true;
		} else {	// if hint has already been given
			return false;
		}
	}
	
	/**
	 * This special getter/accessor method returns the lock's hint and
	 * increments the hint counter by 1.
	 * 
	 * @return A <code>String</code> containing the lock's hint.
	 */
	public String getHint() {
		hintCount++;
		return "Hint (-$3): " + hint;
	}
	
	/**
	 * This getter/accessor method returns the lock's number.
	 * 
	 * @return An <code>int</code> containing the lock's number.
	 */
	public int getLockNumber() {
		return lockNumber;
	}
	
	/**
	 * This overridden method returns a string that represents the Lock object.
	 * 
	 * @return A <code>String</code> representing the Lock.
	 */
	public String toString() {
		String lockString = "Lock " + lockNumber + " (";	// add its lock number to the String
		// Add the number of digits (or characters) in its combination
		int numDigits = String.valueOf(combo).length();
		lockString += numDigits + "-digit combo)";
		
		return lockString;
	}
}