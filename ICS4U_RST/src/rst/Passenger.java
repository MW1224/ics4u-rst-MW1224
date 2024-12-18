package rst;

import java.util.ArrayList;

/**
 * The <code>Passenger</code> class holds the passenger's information and handles the logic of the game.
 * It holds the passenger's first and last name, loyalty status, total money and win status. In terms of
 * the game's logic, it holds the high score out of all games and the mini games left to play (once per each game).
 */
public class Passenger {
	// Class data fields
	public static final String[] LOYALTY_STATUSES = {"Gold", "Platinum", "Emerald", "Diamond", "Diamond Plus", "Pinnacle"};		// the 6 Royal Caribbean loyalty statuses 
	public static final int START_MONEY = 1600;		// passenger starts with $1600
	
	// Instance data fields
	private String firstName, lastName, loyaltyStatus;
	private int totalMoney, highScore;
	private boolean winStatus;		// true if passenger has positive amount of money left, false if they have 0 or less
	private ArrayList<String> miniGamesLeft = new ArrayList<String>();		// contains mini games left to play
	
	/**
	 * This default constructor creates a Passenger with a no first or last name,
	 * no loyalty status, $1600 total money, true winStatus (is winner because have
	 * positive total money left), $0 high score and 2 mini games left.
	 */
	public Passenger() {
		firstName = null;
		lastName = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;	// $1600 starting money for total money
		highScore = 0;				// default high score of 0
		winStatus = true;			// positive money left so win status is true
		miniGamesLeft.add(EscapeRoom.NAME);			// add royal escape room to mini games that haven't been played yet
		miniGamesLeft.add(WordUnscramble.NAME);		// add word unscramble to mini games that haven't been played yet
	}
	
	/**
	 * This overloaded constructor creates a Passenger with no first or last name,
	 * no loyalty status, $1600 total money; is winner true winStatus (is winner
	 * because have positive total money left), given high score and 2 mini games left.
	 * 
	 * @param hScore
	 * 			High score out of all games
	 */
	public Passenger(int hScore) {
		firstName = null;
		lastName = null;
		loyaltyStatus = null;
		totalMoney = START_MONEY;	// $1600 starting money for total money
		highScore = hScore;
		winStatus = true;			// positive money left so win status is true
		miniGamesLeft.add(EscapeRoom.NAME);			// add royal escape room to mini games that haven't been played yet
		miniGamesLeft.add(WordUnscramble.NAME);		// add word unscramble to mini games that haven't been played yet
	}
	
	/**
	 * This overloaded constructor creates a Passenger with a their first and last name,
	 * their loyalty status, $1600 total money, true winStatus (is winner because have
	 * positive total money left), given high score; and 2 mini games left.
	 * 
	 * @param fName
	 * 			User's first name
	 * 
	 * @param lName
	 * 			User's last name
	 * 
	 * @param lStatus
	 * 			User's loyalty status
	 * 
	 * @param hScore
	 * 			High score out of all games
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
	
	/**
	 * This class method returns a string that contains the Cruise Story Game's instructions & overview.
	 * 
	 * @return A <code>String</code> containing the Cruise Story Game's instructions & overview.
	 */
	public static String showGameIntro() {
		return "You are on a 2-day cruise vacation aboard Royal Caribbean's Oasis of the Seas. You will"
				+ " play a variety of mini games and choose your own adventure given some scenarios.";
	}
	
	/**
	 * This class method returns a string that contains the Cruise Story Game's goal & winning circumstances.
	 * 
	 * @return A <code>String</code> containing the Cruise Story Game's goal & winning circumstances.
	 */
	public static String showGameGoal() {
		return "Goal: Don't run out of money at the end of the two days! You will win if you still have "
				+ "money left. You will start with $" + START_MONEY;
	}
	
	/**
	 * This class method returns a string that contains the Cruise Story Game's prompt for the passenger's info.
	 * 
	 * @return A <code>String</code> containing the Cruise Story Game's prompt for the passenger's info.
	 */
	public static String showPassengerInfoPrompt() {
		return "Enter passenger's information:";
	}
	
	/**
	 * Returns a string that represents the passenger's amount of money left.
	 * 
	 * @return A <code>String</code> representing the passenger's amount of money left.
	 */
	public String showMoneyLeft() {
		return "Remaining money: $" + totalMoney;
	}
	
	/**
	 * Updates the passenger's money left and adjusts winStatus accordingly.
	 * 
	 * @param bonus
	 * 			Amount to add to or subtract from total money.
	 */
	public void updateTotalMoney(int bonus) {
		totalMoney += bonus;	// adjust total money based on bonus amount (+ or -)
		
		// Adjust win status depending on amount of money left
		if (totalMoney <= 0) {		// if no money or negative money left
			winStatus = false;
		} else if (totalMoney > 0) {	// if still money left
			winStatus = true;
		}
	}
	
	/**
	 * This getter/accessor method returns the mini games left to play.
	 * 
	 * @return A <code>String ArrayList</code> containing the mini games left
	 * to play.
	 */
	public ArrayList<String> getMiniGamesLeft() {
		return miniGamesLeft;
	}
	
	/**
	 * Removes the specified mini game from the ArrayList of mini games left.
	 * 
	 * @param miniGame
	 * 			Mini game to remove from the ArrayList of mini games left.
	 */
	public void removeMiniGame(String miniGame) {
		miniGamesLeft.remove(miniGame);		// use ArrayList's method the mini game String object
	}
	
	/**
	 * This getter/accessor method returns the passenger's total money left.
	 * 
	 * @return An <code>int</code> containing the passenger's total money left.
	 */
	public int getMoneyLeft() {
		return totalMoney;
	}
	
	/**
	 * This getter/accessor method returns the passenger's first name.
	 * 
	 * @return A <code>String</code> containing the passenger's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This setter/mutator method sets the passenger's first name.
	 * 
	 * @param fName
	 * 			The passenger's first name.
	 */
	public void setFirstName(String fName) {
		firstName = fName;
	}
	
	/**
	 * This getter/accessor method returns the passenger's last name.
	 * 
	 * @return A <code>String</code> containing the passenger's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This setter/mutator method sets the passenger's last name.
	 * 
	 * @param lName
	 * 			The passenger's last name.
	 */
	public void setLastName(String lName) {
		lastName = lName;
	}
	
	/**
	 * This setter/mutator method sets the passenger's loyalty status.
	 * 
	 * @param lStatus
	 * 			The passenger's loyalty status.
	 */
	public void setLoyaltyStatus(String lStatus) {
		loyaltyStatus = lStatus;
	}
	
	/**
	 * Returns a string that represents the high score out of all games stored in Passenger object.
	 * 
	 * @return A <code>String</code> representing the high score out of all games stored in Passenger object.
	 */
	public String showHighScore() {
		return "High score (out of all games): $" + highScore;
	}
	
	/**
	 * This getter/accessor method returns the high score out of all games.
	 * 
	 * @return An <code>int</code> containing the high score out of all games.
	 */
	public int getHighScore() {
		return highScore;
	}
	
	/**
	 * Resets the high score out of all games to 0;
	 */
	public void resetHighScore() {
		highScore = 0;
	}
	
	/**
	 * This getter/accessor method returns the passenger's win status (true if positive money left,
	 * false if negative money left).
	 * 
	 * @return A <code>boolean</code> containing the passenger's win status.
	 */
	public boolean getWinStatus() {
		return winStatus;
	}
	
	/**
	 * Returns a string that represents the Cruise Story Game's results. If the passenger lost, their loyalty
	 * status will drop to the level below (unless they're already at the lowest level of GOLD). If the passenger
	 * won, their loyalty status will increase to the level above (unless they're already at the top level of
	 * PINACCLE). Indicates if passenger won or lost, their final remaining money, lets them know if they set
	 * a new high score (if it's higher than the current high score) and outputs high score out of all games.
	 * 
	 * @return A <code>String</code> representing the Cruise Story Game's results.
	 */
	public String showOverallResult() {
		String result = firstName + " " + lastName + " ";	// add passenger's first and last name to result
		int loyaltyStatusIndex = 0;		// index of passenger's current loyalty status in the array of loyalty statuses
		
		// Get loyalty status index
		for (int i = 0; i < LOYALTY_STATUSES.length; i++) {
			if (loyaltyStatus.equals(LOYALTY_STATUSES[i])) {
				loyaltyStatusIndex = i;		// set the index to the passenger's loyalty status's index
			}
		}
		
		// Output different results depending on whether or not the passenger won
		if (winStatus) {
			result += "won!\n";
			
			// Show updated loyalty status
			if (loyaltyStatusIndex != LOYALTY_STATUSES.length - 1) {	// if loyalty status isn't already the highest level
				result += "You've been upgraded from " + loyaltyStatus + " to ";	// show upgraded message
				setLoyaltyStatus(LOYALTY_STATUSES[loyaltyStatusIndex + 1]);		// set their loyalty status to the level above their current one
			} else {	// if loyalty status is the highest level of PINNACLE
				result += "You have maintained your status of ";	// show maintained status message
			}
		} else {
			result += "lost!\n";
			
			// Show update loyalty status
			if (loyaltyStatusIndex != 0) {	// if loyalty status isn't already the lowest level
				result += "You've been degraded from " + loyaltyStatus + " to ";	// show degraded message
				setLoyaltyStatus(LOYALTY_STATUSES[loyaltyStatusIndex - 1]);		// set their loyalty status to the level below their current one
			} else {	// if loyalty status is the lowest level of GOLD
				result += "You still have the lowest status of ";	// show maintained status message
			}
		}
		
		// Add updated/maintained loyalty status & final remaining money to result output
		result += loyaltyStatus + "!\nYou had $" + totalMoney + " left.\n";
		
		// Add a message if the passenger set a new high score
		if (totalMoney > highScore) {	// if this passenger's remaining money is greater than the current high score
			highScore = totalMoney;		// set their remaining money as the current high score
			result += "You set a new high score!\n";	// add message
		}
		
		// Add current high score to result output
		result += showHighScore();
		
		return result;
	}
	
	/**
	 * This overridden method returns a String that represents the Passenger object.
	 * 
	 * @return A <code>String</code> representing the Passenger object.
	 */
	public String toString() {
		String passengerString = firstName + " " + lastName + "\nLoyalty Status: "
				+ loyaltyStatus + "\nTotal Money: " + totalMoney;
		return passengerString;
	}
}