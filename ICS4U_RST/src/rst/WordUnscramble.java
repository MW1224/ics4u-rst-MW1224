package rst;

import java.util.ArrayList;

public class WordUnscramble extends MiniGame {
	public static final int START_BONUS = 0, CORRECT_BONUS = 4, INCORRECT_FEE = -6, BLANK_FEE = -10;
	public static final int NUM_OF_POSSIBLE_WORDS = 22;
	public static final String WORD = "ocean";
	public static final String CORRECT = "correct", INCORRECT = "incorrect", BLANK = "blank"; 
	public static final String[] POSSIBLE_WORDS = {"ocean", "canoe", "acne", "cane", "cone", "once",
			"aeon", "can", "con", "ace", "eco", "oca", "ane", "eon", "nae", "one", "an", "en", "na",
			"no", "on", "ae"};
	
	private ArrayList<String> possibleWordsLeft;
	
	public WordUnscramble() {
		bonusMoney = START_BONUS;
		possibleWordsLeft = new ArrayList<String>();
		populatePossibleWordsLeft();
	}
	
	private void populatePossibleWordsLeft() {
		for (String possibleWord : POSSIBLE_WORDS) {
			possibleWordsLeft.add(possibleWord);
		}
	}
	
	public static String showInstructions() {
		return "Using the letters below, you must create as many words as possible:\n\t- The words don't"
				+ "need to use all 5 letters\n\t- No duplicate words\n\t- Words must be actual words\n\t"
				+ "- Only click 'done' when you're done entering all your words";
	}
	
	public static String showPointsSystem() {
		return "Bonus money (starting at $" + START_BONUS + "):\n\t- Each incorrect word: -$" + (-1 * INCORRECT_FEE)
				+ "\n\t- Each correct word: +$" + CORRECT_BONUS + "\n\t- Each blank word: -$" + BLANK_FEE;
	}
}