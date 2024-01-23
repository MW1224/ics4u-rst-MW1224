package rst;

import java.util.ArrayList;

/**
 * The <code>Passenger</code> class holds the passenger's information and handles the logic of the game.
 * It holds the 
 */
public class Passenger {
	// Class data fields
	public static final String[] LOYALTY_STATUSES = {"Gold", "Platinum", "Emerald", "Diamond", "Diamond Plus", "Pinnacle"};
	public static final int START_MONEY = 1650;		// Passenger starts with $1650
	
	// Instance data fields
	private String firstName, lastName, loyaltyStatus;
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
		loyaltyStatus = null;
		totalMoney = START_MONEY;
		highScore = 0;
		winStatus = true;
		miniGamesLeft.add(EscapeRoom.NAME);
		miniGamesLeft.add(WordUnscramble.NAME);
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a no
	 * first name, last name and loyalty status;
	 * $2000 total money; is winner (because have total points left); 
	 * given high score; and 2 mini games left.
	 */
	public Passenger(int hScore) {
		firstName = null;
		lastName = null;
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
	public Passenger(String fName, String lName, String lStatus, int hScore) {
		firstName = fName;
		lastName = lName;
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
		String passengerString = firstName + " " + lastName + "\nLoyalty Status: "
				+ loyaltyStatus + "\nTotal Money: " + totalMoney;
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
	
	public void setLoyaltyStatus(String lStatus) {
		loyaltyStatus = lStatus;
	}
	
	public String showHighScore() {
		return "High score (out of all games): $" + highScore;
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
		int loyaltyStatusIndex = 0;
		
		// Get loyalty status index
		for (int i = 0; i < LOYALTY_STATUSES.length; i++) {
			if (loyaltyStatus.equals(LOYALTY_STATUSES[i])) {
				loyaltyStatusIndex = i;
			}
		}
		
		if (winStatus) {
			result += "won!\n";
			if (loyaltyStatusIndex != LOYALTY_STATUSES.length - 1) {
				result += "You've been upgraded from " + loyaltyStatus + " to ";
				setLoyaltyStatus(LOYALTY_STATUSES[loyaltyStatusIndex + 1]);
			} else {
				result += "You have maintained your status of ";
			}
		} else {
			result += "lost!\nYou've been degraded from " + loyaltyStatus + " to ";
			setLoyaltyStatus(LOYALTY_STATUSES[loyaltyStatusIndex - 1]);
		}
		
		result += loyaltyStatus + "!\nYou had $" + totalMoney + " left.\n";
		
		if (totalMoney > highScore) {
			highScore = totalMoney;
			result += "You set a new high score!\n";
		}
		
		result += showHighScore();
		
		return result;
	}
}