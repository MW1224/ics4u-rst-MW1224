package rst;

import java.util.ArrayList;

public class EscapeRoom extends MiniGame {
	public static final int START_BONUS = 20, INCORRECT_FEE = 6, HINT_FEE = 3;
	private static final int[] FIRST_FIVE_LOCK_COMBOS = {16, 6, 10210, 312, 2610};
	private static final String[] LOCK_CLUES = {"Passenger decks on Oasis of the Seas", "Ignore the pink and green beach umbrellas",
			"Oasis of the Seas (it's not the rows...)", "Only formal nights", "'Life is better on a cruise' - I,A,S", "Height of pyramid"};
	private static final String[] LOCK_HINTS = {"Lock 1 - Do a quick Google search!", "Lock 2 - Number of blue beach umbrellas.",
			"Lock 3 - Number of times Oasis OTS appears in each column.", "Lock 4  - Number of times each letter (i/a/s) appears.",
			"Lock 5 - Night NUMBERS of formal nights in increasing order", "Pyramid's number of rows."};
	
	private ArrayList<Lock> allLocks;
	private ArrayList<String> pyramidOutput;
	
	public EscapeRoom() {
		allLocks = new ArrayList<Lock>();
		pyramidOutput = new ArrayList<String>();
		
		for (int i = 0; i < FIRST_FIVE_LOCK_COMBOS.length; i++) {
			allLocks.add(new Lock(LOCK_CLUES[i], LOCK_HINTS[i], FIRST_FIVE_LOCK_COMBOS[i], (i + 1)));
		}
	}
	
	public static String showInstructions() {
		return "You are on a ship that is about to sink. You must find\nyour life boat to escape and survive. However, it is\nlocked, and in"
				+ " order to find that lock combination,\nyou first need to unlock 5 other locks.\nYou start with $" + START_BONUS + " bonus"
				+ " money. For each incorrect guess,\n$6 gets subtracted from the bonus. For each hint,\n$3 gets subtracted.";
	}
	
	public String attemptUnlock(String strLock, int code) {
		Lock lock = null;
		
		for (Lock eachLock : allLocks) {
			if (eachLock.toString().equals(strLock)) {
				lock = eachLock;
			}
		}
		
		boolean isUnlocked = lock.unlock(code);
		
		if (isUnlocked) {
			allLocks.remove(lock);
			return "Correct!";
		} else {
			bonusMoney -= INCORRECT_FEE;
			return "Incorrect! Please try again. (-$" + INCORRECT_FEE + ")";
		}
	}
	
	public String[] getStrLocks() {
		String[] strLocks = new String[allLocks.size()];
		
		for (int i = 0; i < strLocks.length; i++) {
			strLocks[i] = allLocks.get(i).toString();
		}
		
		return strLocks;
	}
	
	public String getLockClue(int lockNum) {
		return allLocks.get(lockNum - 1).getClue();
	}
	
	public String getLockHint(int lockNum) {
		return allLocks.get(lockNum - 1).getHint();
	}
}