package rst;

public class Lock {
	private String clue, hint;
	private boolean state;	// false if locked, true if unlocked
	private int combo, hintCount;
	
	public Lock(String lockClue, String lockHint, int lockCombo) {
		clue = lockClue;
		hint =lockHint;
		state = false;
		combo = lockCombo;
		hintCount = 0;
	}
	
	
}