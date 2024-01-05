package rst;

import java.util.ArrayList;

public class EscapeRoom extends MiniGame {
	public static final int START_BONUS = 20;
	public static final String[] HINTS = {"Lock 2 - Number of blue umbrellas.", "Lock 1 - Do a quick Google search!",
			"Lock 3 - Number of times Oasis OTS appears in each column.", "Lock 5 - Night NUMBERS of formal nights in increasing order",
			"Lock 4  - Number of times each letter (i/a/s) appears.", "Pyramid's number of rows."};
	
	private ArrayList<Lock> allLocks;
	private int[] hintCount;
	private boolean all5LocksOpened;
	private ArrayList<String> pyramidOutput;
}