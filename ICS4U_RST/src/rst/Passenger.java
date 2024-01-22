package rst;

import java.util.ArrayList;

/**
 * The <code>Passenger</code> class
 */
public class Passenger {
	// Class data fields
	public static final String[] LOYALTY_STATUSES = {"Gold", "Platinum", "Emerald", "Diamond", "Diamond Plus", "Pinnacle"};
	public static final int START_MONEY = 1600;
	
	// Instance data fields
	private String firstName, lastName, email, phoneNumber, loyaltyStatus;
	private int totalMoney, highScore;
	private boolean winStatus;
	private ArrayList<String> miniGamesLeft = new ArrayList<String>();
	
	/**
	 * This default constructor creates a Passenger with a no
	 * first name, last name, email and phone number; gold loyalty status;
	 * $2000 total money; is winner (because have total points left);
	 * $0 high score; and 2 mini games left.
	 */
	public Passenger() {
		firstName = null;
		lastName = null;
		email = null;
		phoneNumber = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;
		highScore = 0;
		winStatus = true;
		miniGamesLeft.add(EscapeRoom.NAME);
		miniGamesLeft.add(WordUnscramble.NAME);
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a no
	 * first name, last name, email, phone number and loyalty status;
	 * $2000 total money; is winner (because have total points left); 
	 * given high score; and 2 mini games left.
	 */
	public Passenger(int hScore) {
		firstName = null;
		lastName = null;
		email = null;
		phoneNumber = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;
		highScore = hScore;
		winStatus = true;
		miniGamesLeft.add(EscapeRoom.NAME);
		miniGamesLeft.add(WordUnscramble.NAME);
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a their
	 * first name, last name, email, phone number and loyalty status;
	 * $2000 total money; is winner (because have total points left);
	 * given high score; and 2 mini games left.
	 */
	public Passenger(String fName, String lName, String email, String phoneNum, String lStatus, int hScore) {
		firstName = fName;
		lastName = lName;
		this.email = email;
		phoneNumber = phoneNum;
		loyaltyStatus = lStatus;
		totalMoney = START_MONEY;
		highScore = hScore;
		winStatus = true;
		miniGamesLeft.add(EscapeRoom.NAME);
		miniGamesLeft.add(WordUnscramble.NAME);
	}
	
	public static String showGameIntro() {
		return "You are on a 2-day cruise vacation aboard Royal Caribbean's Oasis of the Seas. You will"
				+ " play a variety of mini games and choose your own adventure given some scenarios.";
	}
	
	public static String showGameGoal() {
		return "Goal: Don't run out of money at the end of the two days! You will win if you still have "
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
		
		// Adjust win status depending on amount of money left
		if (totalMoney <= 0) {	// if no money or negative money left
			winStatus = false;
		} else if (totalMoney > 0) {	// if still money left
			winStatus = true;
		}
	}
	
	public ArrayList<String> getMiniGamesLeft() {
		return miniGamesLeft;
	}
	
	public void removeMiniGame(String miniGame) {
		miniGamesLeft.remove(miniGame);
	}
	
	public int getMoneyLeft() {
		return totalMoney;
	}
	
	public String toString() {
		String passengerString = firstName + " " + lastName + "\n" + email + "\n" + phoneNumber + "\nLoyalty Status: " + loyaltyStatus
				+ "\nTotal Money: " + totalMoney + "\nHigh Score Out of All Games: " + highScore;
		return passengerString;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String fName) {
		firstName = fName;
	}
	
	public void setLastName(String lName) {
		lastName = lName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNum) {
		phoneNumber = phoneNum;
	}
	
	public void setLoyaltyStatus(String lStatus) {
		loyaltyStatus = lStatus;
	}
	
	public String showHighScore() {
		return "High score (out of all games): $" + highScore;
	}
	
	public void setNewHighScore(int newHighScore) {
		highScore = newHighScore;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public void resetHighScore() {
		highScore = 0;
	}
	
	public boolean getWinStatus() {
		return winStatus;
	}
	
	public String showOverallResult() {
		String result = firstName + " " + lastName + " ";
		
		if (winStatus) {
			result += "won!";
		} else {
			result += "lost!";
		}
		
		result += "\nYou had $" + totalMoney + "left.\n";
		
		if (totalMoney > highScore) {
			result += "You set a new high score!\n";
		}
		
		result += showHighScore();
		
		return result;
	}
}