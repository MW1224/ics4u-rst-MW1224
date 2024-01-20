package rst;

import java.util.ArrayList;

public class EscapeRoom extends MiniGame {
	public static final String NAME = "Royal Escape Room";
	public static final int START_BONUS = 20, INCORRECT_FEE = 6, HINT_FEE = 3;
	public static final int [] LOCK_NUMS = {2, 1, 3, 5, 4};
	private static final int[] FIRST_FIVE_LOCK_COMBOS = {16, 6, 10210, 312, 2610};
	private static final String[] LOCK_CLUES = {"Passenger decks on Oasis of the Seas", "Ignore the pink and green beach umbrellas",
			"Oasis of the Seas (it's not the rows...)", "Only formal nights (left -> right; top -> bottom)", "'Life is better on a"
			+ "cruise' - I,A,S", "Height of pyramid"};
	private static final String[] LOCK_HINTS = {"Lock 1 - Do a quick Google search!", "Lock 2 - Number of blue beach umbrellas.",
			"Lock 3 - Number of times Oasis OTS appears in each column.", "Lock 4  - Number of times each letter (i/a/s) appears.",
			"Lock 5 - Night NUMBERS of formal nights in increasing order (left to right; top to bottom - each night # increments by 1",
			"Pyramid's number of rows."};
	private static final String UNLOCKED = "UNLOCKED", LOCKED = "LOCKED";
	
	private ArrayList<Lock> allLocks;
	private ArrayList<String> pyramidOutput;
	
	public EscapeRoom() {
		allLocks = new ArrayList<Lock>();
		pyramidOutput = new ArrayList<String>();
		bonusMoney = START_BONUS;
		
		for (int i = 0; i < FIRST_FIVE_LOCK_COMBOS.length; i++) {
			allLocks.add(new Lock(LOCK_CLUES[i], LOCK_HINTS[i], FIRST_FIVE_LOCK_COMBOS[i], (i + 1)));
		}
	}
	
	public static String showInstructions() {
		return "You are on a ship that is about to sink. You must find your life boat to escape and survive. However, it is locked, and in"
				+ " order to find that lock combination, you first need to unlock 5 other locks. You start with $" + START_BONUS + " bonus"
				+ " money. For each incorrect guess, $" + INCORRECT_FEE + " gets subtracted from the bonus. For each hint, $"
				+ HINT_FEE + " gets subtracted.";
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
	
	public String getStrLockState(int lockNum) {
		Lock lock = allLocks.get(lockNum - 1);
		if (lock.isOpen()) {
			return UNLOCKED;
		} else {
			return LOCKED;
		}
	}
	
	public boolean getLockState(int lockNum) {
		return allLocks.get(lockNum - 1).isOpen();
	}
	
	public String getLockClue(int lockNum) {
		return allLocks.get(lockNum - 1).getClue();
	}
	
	public String getLockHint(int lockNum) {
		Lock lock = allLocks.get(lockNum - 1);
		
		if (lock.isEligibleForHint()) {
			bonusMoney -= HINT_FEE;
			return lock.getHint();
		} else {
			return "Hint already received.";
		}
	}
	
	public String getConsonantPyramid(String word) {
		String pyramid = "";
		
		word.toUpperCase();
		
		consonantPyramid(word + "a", word.length());
		
		for (String row : pyramidOutput) {
			pyramid += row + "\n";
		}
		
		return pyramid;
	}
	
	private void consonantPyramid(String word, int vowelIndex) {
		final char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
		boolean containsVowel = false;
		
		// First, check if word contains a vowel in order to create a pyramid by removing vowels
		for (char vowel: VOWELS) {
			if (word.contains(String.valueOf(vowel))) {
				containsVowel = true;
			}
		}
		
		if (containsVowel) {
			checkVowelFromEnd:
			for (int i = word.length() - 1; i >= 0; i--) {
				for (char testVowel : VOWELS) {
					if (word.charAt(i) == testVowel) {
						vowelIndex = i;
						break checkVowelFromEnd;
					}
				}
			}
			
			String shortenedWord = word.substring(0, vowelIndex) + word.substring(vowelIndex + 1);;		
			consonantPyramid(shortenedWord, vowelIndex);
			pyramidOutput.add(shortenedWord);
		}
	}
	
	public boolean validateWord(String word) {
		boolean isValidWord = true;
		
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isLetter(word.charAt(i))) {
				isValidWord = false;
			}
		}
		
		return isValidWord;
	}
	
	public boolean needsVisual(int lockNum) {
		if (lockNum == 2 || lockNum == 3 || lockNum == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public String [][] getLockVisual(int lockNum) {
		final String BLUE_UMBRELLA = "BLUE_UMBRELLA", PINK_UMBRELLA = "PINK_UMBRELLA", GREEN_UMBRELLA = "GREEN_UMBRELLA",
				OASIS = "OASIS_OF_THE_SEAS", ICON = "ICON_OF_THE_SEAS", ODYSSEY = "ODYSSEY_OF_THE_SEAS", FORMAL = "FORMAL_ATTIRE",
				CASUAL = "CASUAL_ATTIRE", SMART_CASUAL = "SMART_CASUAL_ATTIRE";
		
		if (lockNum == 2) {
			String [][] UMBRELLA_GRID = {{GREEN_UMBRELLA, BLUE_UMBRELLA, BLUE_UMBRELLA, PINK_UMBRELLA, GREEN_UMBRELLA},
					{BLUE_UMBRELLA, BLUE_UMBRELLA, PINK_UMBRELLA, BLUE_UMBRELLA, GREEN_UMBRELLA}};
			return UMBRELLA_GRID;
			
		} else if (lockNum == 3) {
			final String [][] CRUISE_SHIP_GRID = {{OASIS, ODYSSEY, OASIS, ICON, ODYSSEY}, {ICON, ICON, OASIS, OASIS, ICON}};
			return CRUISE_SHIP_GRID;
			
		} else if (lockNum == 5) {
			String [][] ATTIRE_GRID = {{CASUAL, FORMAL, SMART_CASUAL, SMART_CASUAL, SMART_CASUAL},
					{FORMAL, CASUAL, CASUAL, SMART_CASUAL, FORMAL}};
			return ATTIRE_GRID;
		} else {
			return null;
		}
	}
}