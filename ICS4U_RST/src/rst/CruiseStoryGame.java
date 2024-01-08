package rst;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Mayra Wang
 * Date: January 2024
 * Course: ICS4U
 * CruiseStoryGame.java
 * RST
 */
public class CruiseStoryGame extends Application {
	
	// Global constants
	private static final int GAP = 10;
	private static final int SMALL_FONT = 14, MEDIUM_FONT = 20, LARGE_FONT = 26;
	
	// References for objects
	Passenger passenger;
	
	/**
	 * Overridden method to handle what happens when application stops.
	 * Saves high score to text file.
	 */
	@Override
	public void stop() throws Exception {
		FileHandler.saveToFile(passenger.getHighScore());
	}
	
	/**
	 * Overridden method to handle what happens when the application starts.
	 */
	@Override
	public void start(Stage myStage) throws Exception {
		// Local constants
		final int NUM_OF_TILE_COLS = 2;
		
		// Set layout
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// Add title label
		Label lblTitle = new Label("Welcome to the cruise story game!");
		lblTitle.setFont(Font.font(LARGE_FONT));
		root.getChildren().add(lblTitle);
		
		// Add TilePane layout
		TilePane tileInstructions = new TilePane();
		tileInstructions.setPrefColumns(NUM_OF_TILE_COLS);
		// Add ImageView to hold image of Royal Caribbean's logo
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo.png").toString()));
		tileInstructions.getChildren().add(imgLogo);
		// Add Label to hold game instructions
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}