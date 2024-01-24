package rst;

import java.util.ArrayList;

/**
 * The <code>EscapeRoom</code> class, a subclass of the <code>MiniGame</code> class, manages
 * the logic of the Royal Escape Room mini game. It contains the locks left for the user to pick, as
 * well as the pyramid output for the final lock.
 */
public class EscapeRoom extends MiniGame {
	// Class data fields
	public static final String NAME = "Royal Escape Room";
	public static final int START_BONUS = 20;	// user starts with $20 bonus money
	public static final int INCORRECT_FEE = 6, HINT_FEE = 3;	// user loses $6 for each incorrect attempt, $3 for every hint in bonus money
	public static final int [] LOCK_NUMS = {2, 1, 3, 5, 4};		// actual order of locks (#'s) to match each subsequent clue (1-5) in the game
	private static final String[] FIRST_FIVE_LOCK_COMBOS = {"16", "6", "10210", "312", "2610"};		// lock combos for locks 1-5 (in order)
	private static final String[] LOCK_CLUES = {"Passenger decks on Oasis of the Seas", "Ignore the pink and green beach umbrellas",
			"Oasis of the Seas (it's not the rows...)", "'Life is better on a cruise' - I,A,S", "Only formal nights (left -> right; top -> bottom)"
			, "Height of pyramid"};		// lock clues for locks 1-5 (in order)
	private static final String[] LOCK_HINTS = {"Lock 1 - Do a quick Google search!", "Lock 2 - Number of blue beach umbrellas.",
			"Lock 3 - Number of times Oasis OTS appears in each column.", "Lock 4  - Number of times each letter (i/a/s) appears.",
			"Lock 5 - Night NUMBERS of formal nights in increasing order (left to right; top to bottom - each night # increments by 1",
			"Pyramid's number of rows."};	// lock hints for locks 1-5 (in order)
	private static final Lock[] LOCKS = new Lock[6];	// 6 total Locks in order of lock #'s (lock 1-6) in Escape Room mini game
	private static final int[] LOCK_INDICES = {0, 1, 0, 0, 1};	// updated locks' indices in ArrayList after previous locks have been removed
	
	// Instance data fields
	private ArrayList<Lock> allLocks;	// locks left to unlock (only the first five)
	private ArrayList<String> pyramidOutput = new ArrayList<String>();	// last lock's consonant pyramid (each element is a new line of the pyramid)
	
	/**
	 * This default constructor creates an EscapeRoom mini game with an instantiated ArrayList with the first five locks,
	 * $20 bonus money and instantiates all 6 locks in the LOCKS constant array.
	 */
	public EscapeRoom() {
		allLocks = new ArrayList<Lock>();	// instantiate ArrayList of locks left out of the first 5 locks
		bonusMoney = START_BONUS;			// set bonus money to starting amount of $20
		instantiateLocks();					// call private method instantiate all locks (allLocks ArrayList & LOCKS constant array)
	}
	
	/**
	 * Instantiates all locks (in allLocks ArrayList & LOCKS constant array).
	 */
	private void instantiateLocks() {
		// Constant for index of last special lock in LOCKS array (the one that contains all 6 locks)
		final int LAST_LOCK_INDEX = LOCKS.length - 1;
		
		// Instantiate locks #1-5
		for (int i = 0; i < FIRST_FIVE_LOCK_COMBOS.length; i++) {
			Lock newLock = new Lock(LOCK_CLUES[i], LOCK_HINTS[i], FIRST_FIVE_LOCK_COMBOS[i], (i + 1));	// Instantiate lock #[i + 1]
			LOCKS[i] = newLock;		// Add lock #[i + 1] to LOCKS constant array at position i
			allLocks.add(newLock);	// Add lock #[i + 1] to allLocks ArrayList
		}
		
		// Instantiate last lock (lock #6) in LOCKS constant array's last index
		LOCKS[LAST_LOCK_INDEX] = new Lock(LOCK_CLUES[LAST_LOCK_INDEX], LOCK_HINTS[LAST_LOCK_INDEX], LAST_LOCK_INDEX + 1);
	}
	
	/**
	 * This class method returns a string that contains the Escape Room's story & instructions.
	 * 
	 * @return A <code>String</code> containing the Escape Room's story & instructions.
	 */
	public static String showInstructions() {
		return "You are on a ship that is about to sink. You must find your life boat to escape and survive. However, it is locked, and in"
				+ " order to find that lock combination, you first need to unlock 5 other locks. You start with $" + START_BONUS + " bonus"
				+ " money. For each incorrect guess, $" + INCORRECT_FEE + " gets subtracted from the bonus. For each hint, $"
				+ HINT_FEE + " gets subtracted.";
	}
	
	/**
	 * Uses given code to attempt to unlock the lock with the specific lock number. The lock
	 * can only be unlocked if it is the actual lock meant to be open.
	 * 
	 * @param lockNum
	 * 			The number of the lock in which the user wants to unlock.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @param code
	 * 			The combination the user entered for the lock they want to unlock.
	 * 
	 * @return A <code>String</code> representing the passenger's amount of money left.
	 */
	public String attemptUnlock(int lockNum, int actualLockNum, String code) {
		Lock lock = LOCKS[lockNum - 1];	// get the lock the user wants to unlock
		boolean isUnlocked = lock.unlock(code, actualLockNum);	// try to unlock that lock
		
		// Return a different output message depending on if unlock attempt is successful or not
		if (isUnlocked) {	// if user picked correct lock & entered correct combination for that lock
			// Remove the opened lock from the ArrayList of the first five locks
			// (only if it's not the last lock in the ArrayList (because index -1 would result in error) or lock #6)
			if (lockNum != 4 && lockNum != 6) {
				allLocks.remove(LOCK_INDICES[lockNum - 1]);
			}
			return "Correct!";
		} else {	// if user picked incorrect lock and/or entered incorrect combination the lock
			bonusMoney -= INCORRECT_FEE;	// subtract fee for incorrect attempt from bonus money
			return "Incorrect! Please try again. (-$" + INCORRECT_FEE + ")";
		}
	}
	
	/**
	 * Returns lock's hint eligibility (false if its hint has already been given for that lock.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>boolean</code> representing the lock's hint eligibility.
	 */
	public boolean canHaveHint(int actualLockNum) {
		return LOCKS[actualLockNum - 1].isEligibleForHint();
	}
	
	/**
	 * Returns string representation of the remaining locks out of the first five in the allLocks ArrayList.
	 * 
	 * @return A <code>String</code> representing the remaining locks out of the first five.
	 */
	public String[] getStrLocks() {
		// Instantiate a String array with the number of locks left in allLocks
		String[] strLocks = new String[allLocks.size()];
		
		// For each lock at i in allLocks, store its String representation in strLocks at index i
		for (int i = 0; i < strLocks.length; i++) {
			strLocks[i] = allLocks.get(i).toString();
		}
		
		return strLocks;
	}
	
	
	/**
	 * This special getter/accessor method returns the String representation of the specified lock's state.
	 * 
	 * @param lockNum
	 * 			The number of the lock in which the user selected.
	 * 
	 * @return A <code>String</code> representing the specified lock's state.
	 */
	public String getStrLockState(int lockNum) {
		return LOCKS[lockNum - 1].getStrState();
	}
	
	/**
	 * This special getter/accessor method returns the specified lock's state.
	 * 
	 * @param lockNum
	 * 			The number of the lock in which the user selected.
	 * 
	 * @return A <code>boolean</code> containing the specified lock's state.
	 */
	public boolean getLockState(int lockNum) {
		return LOCKS[lockNum - 1].isOpen();
	}
	
	/**
	 * This special getter/accessor method returns the actual lock's clue.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>String</code> containing the actual lock's clue.
	 */
	public String getLockClue(int actualLockNum) {
		return LOCKS[actualLockNum - 1].getClue();
	}
	
	/**
	 * This special getter/accessor method returns the actual lock's hint.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>String</code> containing the actual lock's hint.
	 */
	public String getLockHint(int actualLockNum) {
		Lock lock = LOCKS[actualLockNum - 1];
		
		// Only subtract hint fee & show hint if lock hasn't already has its hint showed yet
		if (lock.isEligibleForHint()) {
			bonusMoney -= HINT_FEE;
			return lock.getHint();
		} else {	// if lock's hint has already been given
			return "Hint already received.";
		}
	}
	
	/**
	 * This special getter/accessor method returns the specified lock's combination.
	 * 
	 * @param lockNum
	 * 			The number of the lock in which the user selected.
	 * 
	 * @return A <code>String</code> containing the specified lock's combination.
	 */
	public String getLockCombo(int lockNum) {
		return LOCKS[lockNum - 1].getCombo();
	}
	
	/**
	 * Returns the consonant pyramid depending on the given word for the last lock's clue.
	 * 
	 * @param word
	 * 			The word entered by the user to use consonant pyramid algorithm with.
	 * 
	 * @return A <code>String</code> containing the consonant pyramid for the last lock's clue.
	 */
	public String getConsonantPyramid(String word) {
		String pyramid = "";
		
		// Apply recursive consonant pyramid algorithm to word entered by user
		consonantPyramid(word.toUpperCase() + "A", word.length());	// add 'A' to end of word to make bottom line of pyramid the original word
		
		// Add each element of the pyramidOutput ArrayList on a new line of pyramid variable
		for (String row : pyramidOutput) {
			pyramid += row + "\n";
		}
		
		// Set the last lock's combination to the height/number of rows of the consonant pyramid output
		LOCKS[5].setCombo(String.valueOf(pyramidOutput.size()));
		
		return pyramid;
	}
	
	/**
	 * Applies a consonant pyramid recursive algorithm.
	 * 
	 * @param word
	 * 			The word to possibly remove its last vowel from.
	 * 
	 * @param vowelIndex
	 * 			The last vowel occurrence in word.
	 */
	private void consonantPyramid(String word, int vowelIndex) {
		// Local variables
		final char[] VOWELS = {'A', 'E', 'I', 'O', 'U', 'Y'};
		boolean containsVowel = false;	// sets containsVowel to false (it will only turn true if a vowel is found in for loop)
		
		// First, check if word contains a vowel in order to create a pyramid by removing vowels from right to left of word
		for (char vowel: VOWELS) {	// loop through each vowel in VOWELS
			if (word.contains(String.valueOf(vowel))) {
				containsVowel = true;
			}
		}
		
		// Only shorten word again (apply recursion) if it contains a vowel and has more than one letter left
		if (containsVowel && word.length() > 1) {
			
			checkVowelFromEnd:	// name of outer loop
			for (int i = word.length() - 1; i >= 0; i--) {	// loop through each letter of word, starting from the right/end
				for (char testVowel : VOWELS) {		// loop through each vowel in VOWELS
					if (word.charAt(i) == testVowel) {	// if letter is a vowel
						vowelIndex = i;		// set that letter's index to vowelIndex
						break checkVowelFromEnd;	// break out of outer loop because vowel closest to end of word has been located
					}
				}
			}
			
			// Get new word (after removing the vowel closest to the right)
			String shortenedWord = word.substring(0, vowelIndex) + word.substring(vowelIndex + 1);;		
			consonantPyramid(shortenedWord, vowelIndex);	// recursive call
			pyramidOutput.add(shortenedWord);	// add the shortened word as a new element to pyramidOutput ArrayList
		}
	}
	
	/**
	 * Validates word entered by user to make sure it is an actual word (no numbers, symbols,..). This is
	 * to generate the last lock's consonant pyramid clue.
	 * 
	 * @param word
	 * 			The word entered by user.
	 * 
	 * @return A <code>boolean</code> containing the validity of the user's word.
	 */
	public boolean validateWord(String word) {
		// Set isValidWord to true (it will only change to false if it has a character that isn't a letter later)
		boolean isValidWord = true;
		
		// Check each character in user's word to make sure they're all alphabetical letters
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isLetter(word.charAt(i))) {
				isValidWord = false;
			}
		}
		
		return isValidWord;
	}
	
	/**
	 * Returns a boolean that represents whether or not the actual lock needs a visual clue.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>boolean</code> representing whether or not the actual lock
	 * needs a visual clue.
	 */
	public boolean needsVisual(int actualLockNum) {
		// Only locks 2, 3 and 5 need a visual clue
		if (actualLockNum == 2 || actualLockNum == 3 || actualLockNum == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the visual output for the actual lock's clue.
	 * 
	 * @param actualLockNum
	 * 			The number of the actual lock meant to be open.
	 * 
	 * @return A <code>String</code> 2D array containing the visual output for actual lock's clue.
	 */
	public String [][] getLockVisual(int actualLockNum) {
		// Constants for visuals in 2D array
		final String BLUE_UMBRELLA = "BLUE_UMBRELLA", PINK_UMBRELLA = "PINK_UMBRELLA", GREEN_UMBRELLA = "GREEN_UMBRELLA",
				OASIS = "OASIS_OF_THE_SEAS", ICON = "ICON_OF_THE_SEAS", ODYSSEY = "ODYSSEY_OF_THE_SEAS", FORMAL = "FORMAL_ATTIRE",
				CASUAL = "CASUAL_ATTIRE", SMART_CASUAL = "SMART_CASUAL_ATTIRE";
		
		// Return a 2D array (2rows x 5cols) with the specified lock's visuals
		if (actualLockNum == 2) {
			String [][] UMBRELLA_GRID = {{GREEN_UMBRELLA, BLUE_UMBRELLA, BLUE_UMBRELLA, PINK_UMBRELLA, GREEN_UMBRELLA},
					{BLUE_UMBRELLA, BLUE_UMBRELLA, PINK_UMBRELLA, BLUE_UMBRELLA, BLUE_UMBRELLA}};
			return UMBRELLA_GRID;
		} else if (actualLockNum == 3) {
			final String [][] CRUISE_SHIP_GRID = {{OASIS, ODYSSEY, OASIS, ICON, ODYSSEY}, {ICON, ICON, OASIS, OASIS, ICON}};
			return CRUISE_SHIP_GRID;
		} else if (actualLockNum == 5) {
			String [][] ATTIRE_GRID = {{CASUAL, FORMAL, SMART_CASUAL, SMART_CASUAL, SMART_CASUAL},
					{FORMAL, CASUAL, CASUAL, SMART_CASUAL, FORMAL}};
			return ATTIRE_GRID;
		} else {	// if actualLockNum isn't 2, 3 or 5, return null because no visual needed
			return null;
		}
	}
}