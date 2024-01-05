package rst;

import java.util.ArrayList;

public class EscapeRoom extends MiniGame {
	public static final int START_BONUS = 20;
	public static final int[] FIRST_FIVE_LOCK_COMBOS = {16, 6, 10210, 312, 2610};
	public static final String[] LOCK_CLUES = {"Passenger decks on Oasis of the Seas", "Ignore the pink and green beach umbrellas",
			"Oasis of the Seas (it's not the rows...)", "Only formal nights", "'Life is better on a cruise' - I,A,S", "Height of pyramid"};
	public static final String[] LOCK_HINTS = {"Lock 1 - Do a quick Google search!", "Lock 2 - Number of blue beach umbrellas.",
			"Lock 3 - Number of times Oasis OTS appears in each column.", "Lock 4  - Number of times each letter (i/a/s) appears.",
			"Lock 5 - Night NUMBERS of formal nights in increasing order", "Pyramid's number of rows."};
	
	private ArrayList<Lock> allLocks;
	private boolean all5LocksOpened;
	private ArrayList<String> pyramidOutput;
	
	public EscapeRoom() {
		allLocks = new ArrayList<Lock>();
		all5LocksOpened = false;
		pyramidOutput = new ArrayList<String>();
		
		for (int i = 0; i < FIRST_FIVE_LOCK_COMBOS.length; i++) {
			allLocks.add(new Lock(LOCK_CLUES[i], LOCK_HINTS[i], FIRST_FIVE_LOCK_COMBOS[i]));
		}
	}
}