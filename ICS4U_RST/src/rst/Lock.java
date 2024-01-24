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
	 * a hint count of 0
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
	
	public void setCombo(String newCombo) {
		combo = newCombo;
	}
	
	public boolean unlock(String comboAttempt, int actualLockNum) {
		if (comboAttempt.equals(combo) && lockNumber == actualLockNum) {
			state = true;
			return true;
		} else {
			state = false;
			return false;
		}
	}
	
	public boolean isOpen() {
		return state;
	}
	
	public String getStrState() {
		if (state) {
			return "UNLOCKED";
		} else {
			return "LOCKED";
		}
	}
	
	public String getCombo() {
		return combo;
	}
	
	public String getClue() {
		return clue;
	}
	
	public boolean isEligibleForHint() {
		if (hintCount == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getHint() {
		hintCount++;
		return "Hint (-$3): " + hint;
	}
	
	public int getLockNumber() {
		return lockNumber;
	}
	
	public String toString() {
		String lockString = "Lock " + lockNumber + " (";
		
		int numDigits = String.valueOf(combo).length();
		lockString += numDigits + "-digit combo)";
		
		return lockString;
	}
}