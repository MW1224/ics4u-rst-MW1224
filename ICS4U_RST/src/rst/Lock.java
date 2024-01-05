package rst;

public class Lock {
	private String clue, hint;
	private boolean state;	// false if locked, true if unlocked
	private int lockNumber, combo, hintCount;
	
	public Lock(String lockClue, String lockHint, int lockCombo, int lockNum) {
		clue = lockClue;
		hint =lockHint;
		state = false;
		combo = lockCombo;
		lockNumber = lockNum;
		hintCount = 0;
	}
	
	public boolean unlock(int comboAttempt) {
		if (comboAttempt == combo) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isOpen() {
		return state;
	}
	
	public int getCombo() {
		return combo;
	}
	
	public String getClue() {
		return clue;
	}
	
	public String getHint() {
		if (hintCount == 0) {
			hintCount++;
			return "Hint (-$3): " + hint;
		} else {
			return "Already got hint. (" + hint + ")";
		}
	}
	
	public int getLockNumber() {
		return lockNumber;
	}
	
	public boolean equals(Object obj) {
		Lock testLock = (Lock) obj;
		if (testLock.getLockNumber() == lockNumber && testLock.getCombo() == combo && testLock.getClue().equals(clue) && testLock.getHint().equals(hint)) {
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