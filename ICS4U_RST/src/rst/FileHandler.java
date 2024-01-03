package rst;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The <code>FileHandler</code> class gets information from and saves information to the text file.
 */
public class FileHandler {
	
	/**
	 * Loads information saved in the text file into an int that contains
	 * the high score out of all games and returns it.
	 * 
	 * @return An <code>int</code> containing the high score out of all
	 * games from the text file.
	 */
	public static int loadFromFile() {
		int highScore = 0;
		
		try {	// handle exceptions that may occur when reading from file
			// File handling objects
			FileReader cruiseStoryGameFile = new FileReader("data/cruiseStoryGame.txt");
			BufferedReader cruiseStoryGameStream = new BufferedReader(cruiseStoryGameFile);
			
			String line = cruiseStoryGameStream.readLine();
			highScore = Integer.valueOf(line);
			
			cruiseStoryGameStream.close();	// close file
		} catch (FileNotFoundException e) {
			System.err.println("No file was found: " + e.getMessage());	// error message
		} catch (IOException e) {
			System.err.println("Problems reading the file: " + e.getMessage());	// error message
		} catch (NumberFormatException e) {
			System.err.println("File not formatted properly: " + e.getMessage());	// error message
		}
		
		return highScore;
	}
	
	/**
	 * Saves high score to the text file.
	 *  
	 * @param highScore
	 * 			The high score of all games to be saved to text file.
	 */
	public static void saveToFile(int highScore) {
		System.out.println("shutting down...");	// output message
		
		try {	// handle exceptions that may occur when writing to file
			FileWriter cruiseStoryGameFile = new FileWriter("data/cruiseStoryGame.txt");	// creates new file on hard drive and writes data to it
			PrintWriter cruiseStoryGamePrinter = new PrintWriter(cruiseStoryGameFile);		// simplifies process of writing different data types to file
			
			cruiseStoryGamePrinter.println(highScore);
			cruiseStoryGamePrinter.close();	// close file
			
		} catch (IOException e) {	// input/output exception
			System.err.println("Error writing to file: " + e.getMessage());	// error message
		}
	}
}