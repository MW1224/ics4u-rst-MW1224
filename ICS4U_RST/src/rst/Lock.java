package rst;

public class Lock {
	private String clue, hint, combo;
	private boolean state;	// false if locked, true if unlocked
	private int lockNumber, hintCount;
	
	public Lock(String lockClue, String lockHint, String lockCombo, int lockNum) {
		clue = lockClue;
		hint =lockHint;
		state = false;
		combo = lockCombo;
		lockNumber = lockNum;
		hintCount = 0;
	}
	
	public Lock(String lockClue, String lockHint, int lockNum) {
		clue = lockClue;
		hint =lockHint;
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
	
	public boolean equals(Object obj) {
		Lock testLock = (Lock) obj;
		if (testLock.getLockNumber() == lockNumber && testLock.getCombo().equals(combo) && testLock.getClue().equals(clue) && testLock.getHint().equals(hint)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String lockString = "Lock " + lockNumber + " (";
		
		int numDigits = String.valueOf(combo).length();
		lockString += numDigits + "-digit combo)";
		
		return lockString;
	}
}