package rst;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
	/*@Override
	public void stop() throws Exception {
		FileHandler.saveToFile(passenger.getHighScore());
	}*/
	
	/**
	 * Overridden method to handle what happens when the application starts.
	 */
	@Override
	public void start(Stage myStage) throws Exception {
		// Local constants
		final int NUM_OF_TILE_COLS = 4;
		
		// Set layout
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Add title Label
		Label lblTitle = new Label("Welcome to the cruise story game!");
		lblTitle.setFont(Font.font(LARGE_FONT));
		root.getChildren().add(lblTitle);
		
		// Add ImageView holding Royal Caribbean's logo
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo.png").toString()));
		root.getChildren().add(imgLogo);
		
		// TilePane layout
		TilePane tileInstructions = new TilePane();
		tileInstructions.setPrefColumns(NUM_OF_TILE_COLS);
		tileInstructions.setAlignment(Pos.CENTER);
		// ImageViews to hold image of Oasis of the Seas (cruise ship's name) & its aqua theatre
		ImageView imgOasis = new ImageView(new Image(getClass().getResource("/images/oasis.png").toString()));
		ImageView imgAquaTheatre = new ImageView(new Image(getClass().getResource("/images/aquaTheatre.png").toString()));
		// Label to hold the game's intro & overall instructions
		Label lblIntro = new Label(Passenger.showGameIntro());
		lblIntro.setFont(Font.font(MEDIUM_FONT));
		// Label to hold game's goal information
		Label lblGoal = new Label(Passenger.showGameGoal());
		lblGoal.setFont(Font.font(MEDIUM_FONT));
		// ImageViews to hold images of Oasis OTS's Central Park
		ImageView imgCentralPark = new ImageView(new Image(getClass().getResource("/images/centralPark.png").toString()));
		ImageView imgCentralPark2 = new ImageView(new Image(getClass().getResource("/images/centralPark2.png").toString()));
		tileInstructions.getChildren().addAll(imgOasis, imgAquaTheatre, lblIntro, lblGoal, imgCentralPark, imgCentralPark2);
		root.getChildren().add(tileInstructions);
		
		// Create scene with root VBox
		Scene mainScene = new Scene(root);
		
		myStage.setTitle("Cruise Story Game");
		myStage.setScene(mainScene);
		myStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}