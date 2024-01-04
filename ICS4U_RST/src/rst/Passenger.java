package rst;

import java.util.ArrayList;

/**
 * The <code>Passenger</code> class
 */
public class Passenger {
	// Class data fields
	public static final String[] LOYALTY_STATUSES = {"Gold", "Platinum", "Emerald", "Diamond", "Diamond Plus", "Pinnacle"};
	public static final int START_MONEY = 2000;
	
	// Instance data fields
	private String firstName, lastName, email, phoneNumber, loyaltyStatus;
	private int totalMoney, highScore;
	private ArrayList<String> miniGamesLeft = new ArrayList<String>();
	
	/**
	 * This default constructor creates a Passenger with a no
	 * first name, last name, email and phone number; gold loyalty status;
	 * $2000 total money; $0 high score; and 2 mini games left.
	 */
	public Passenger() {
		firstName = null;
		lastName = null;
		email = null;
		phoneNumber = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;
		highScore = 0;
		miniGamesLeft.add("Royal Escape Room");
		miniGamesLeft.add("Word Unscramble");
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a no
	 * first name, last name, email, phone number and loyalty status;
	 * $2000 total money; given high score; and 2 mini games left.
	 */
	public Passenger(int hScore) {
		firstName = null;
		lastName = null;
		email = null;
		phoneNumber = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;
		highScore = hScore;
		miniGamesLeft.add("Royal Escape Room");
		miniGamesLeft.add("Word Unscramble");
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a their
	 * first name, last name, email, phone number and loyalty status;
	 * $2000 total money; given high score; and 2 mini games left.
	 */
	public Passenger(String fName, String lName, String email, String phoneNum, String lStatus, int hScore) {
		firstName = fName;
		lastName = lName;
		this.email = email;
		phoneNumber = phoneNum;
		loyaltyStatus = lStatus;
		totalMoney = START_MONEY;
		highScore = hScore;
		miniGamesLeft.add("Royal Escape Room");
		miniGamesLeft.add("Word Unscramble");
	}
	
	public static String showGameIntro() {
		return "You are on a 2-day cruise vacation aboard\nRoyal Caribbean's Oasis of the Seas. You will"
				+ "\nplay a variety of mini games and choose your\nown adventure given some scenarios.";
	}
	
	public static String showGameGoal() {
		return "Goal: Don't run out of money at the end\nof the two days! You will win if you still have\n"
				+ "money left. You will start with $" + START_MONEY;
	}
	
	public static String showPassengerInfoPrompt() {
		return "Enter passenger's information:";
	}
	
	public String showMoneyLeft() {
		return "Remaining money: $" + totalMoney;
	}
	
	public void updateTotalMoney(int bonus) {
		totalMoney += bonus;
	}
	
	public ArrayList<String> getMiniGamesLeft() {
		return miniGamesLeft;
	}

}