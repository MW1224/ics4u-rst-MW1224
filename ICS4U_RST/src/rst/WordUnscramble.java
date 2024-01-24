package rst;

import java.util.ArrayList;

/**
 * The <code>WordUnscramble</code> class, a subclass of the <code>MiniGame</code>
 * class, manages the logic of the Word Unscramble mini game.
 */
public class WordUnscramble extends MiniGame {
	// Constant data fields
	public static final String NAME = "Word Unscramble";
	public static final int START_BONUS = 0, CORRECT_BONUS = 4, INCORRECT_FEE = -6, BLANK_FEE = -10;
	public static final int NUM_OF_POSSIBLE_WORDS = 22;
	public static final String WORD = "OCEAN";
	public static final String CORRECT = "correct", INCORRECT = "incorrect", BLANK = "blank"; 
	public static final String[] POSSIBLE_WORDS = {"OCEAN", "CANOE", "ACNE", "CANE", "CONE", "ONCE",
			"AEON", "CAN", "CON", "ACE", "ECO", "OCA", "ANE", "EON", "NAE", "ONE", "AN", "EN", "NA",
			"NO", "ON", "AE"};
	
	/**
	 * This default constructor creates a WordUnscramble mini game with $0 bonus money.
	 */
	public WordUnscramble() {
		bonusMoney = START_BONUS;
	}
	
	/**
	 * This class method returns a string that contains the Word Unscramble mini game's instructions.
	 * 
	 * @return A <code>String</code> containing the Word Unscramble mini game's instructions.
	 */
	public static String showInstructions() {
		return "Using the letters below, you must create as many words as possible:\n\t- The words don't"
				+ " need to use all " + WORD.length() + " letters\n\t- No duplicate words\n\t- Words must"
				+ " be actual words\n\t- Only click 'done' when you're done entering all your words";
	}
	
	/**
	 * This class method returns a string that contains the Word Unscramble mini game's bonus points system.
	 * 
	 * @return A <code>String</code> containing the Word Unscramble mini game's bonus points system.
	 */
	public static String showPointsSystem() {
		return "Bonus money (starting at $" + START_BONUS + "):\n\t- Each incorrect word: -$" + (-1 * INCORRECT_FEE)
				+ "\n\t- Each correct word: +$" + CORRECT_BONUS + "\n\t- Each blank word: -$" + (-1 * BLANK_FEE);
	}
	
	/**
	 * Checks all words entered by user to see if they're blank,
	 * incorrect (including duplication) or correct.
	 * 
	 * @param wordsToCheck
	 * 			Words entered by user.
	 * 
	 * @return A <code>String</code> array containing the results for each word.
	 */
	public String[] checkWords(String[] wordsToCheck) {
		// Local variables
		boolean foundMatch;		// keeps track of whether or not each word is correct
		ArrayList<String> possibleWordsLeft = new ArrayList<String>();	// contains words left that user hasn't gotten right yet (prevents duplication in their answers)
		String[] correctWords = new String[22];	// contains results for each word (blank, correct or incorrect)
		
		// Add all possible words to ArrayList
		for (String possibleWord : POSSIBLE_WORDS) {
			possibleWordsLeft.add(possibleWord);
		}
		
		// Check words entered by player
		for (int i = 0; i < wordsToCheck.length; i++) {
			foundMatch = false;		// for each word, set foundMatch to initially false (only changes to true if correct word)
			String testWord = wordsToCheck[i].toUpperCase();	// convert all letters to upper case to accurately compare
			
			// Check if word is blank, incorrect or correct
			if (testWord.isEmpty()) {	// if word is blank
				correctWords[i] = BLANK;	// set result for that word to blank
				bonusMoney += BLANK_FEE;	// add fee for blank word ($-10) to bonusMoney
			} else {
				// Check if word entered by user equals one of the possible words left (not duplicated though because of ArrayList)
				for (String possibleWord : possibleWordsLeft) {
					if (testWord.equals(possibleWord)) {
						foundMatch = true;	// set foundMatch to true if word is correct (matches one of the possible words left)
					}
				}
				
				if (foundMatch) {	// if word is correct
					correctWords[i] = CORRECT;	// set result for that word to correct
					bonusMoney += CORRECT_BONUS;	// add bonus for correct word ($4) to bonusMoney
					possibleWordsLeft.remove(testWord);
				} else {	// if word is incorrect
					correctWords[i] = INCORRECT;	// set result for that word to incorrect
					bonusMoney += INCORRECT_FEE;	// add fee for incorrect word ($-6) to bonusMoney
				}
			}
		}
		return correctWords;
	}
}