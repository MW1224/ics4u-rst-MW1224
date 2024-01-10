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
	private static final int SMALL_FONT = 14, MEDIUM_FONT = 19, LARGE_FONT = 26;
	
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
		TilePane tileInstructions = new TilePane(10, 0);
		tileInstructions.setAlignment(Pos.CENTER);
		// ImageViews to hold images of Oasis of the Seas
		ImageView imgOasis = new ImageView(new Image(getClass().getResource("/images/oasis.png").toString()));
		imgOasis.setFitHeight(200);
		imgOasis.setPreserveRatio(true);
		ImageView imgAquaTheatre = new ImageView(new Image(getClass().getResource("/images/aquaTheatre.png").toString()));
		imgAquaTheatre.setFitHeight(200);
		imgAquaTheatre.setPreserveRatio(true);
		ImageView imgOasis2 = new ImageView(new Image(getClass().getResource("/images/oasis2.png").toString()));
		imgOasis2.setFitHeight(200);
		imgOasis2.setPreserveRatio(true);
		ImageView imgBoardwalk = new ImageView(new Image(getClass().getResource("/images/boardwalk.png").toString()));
		imgBoardwalk.setFitHeight(200);
		imgBoardwalk.setPreserveRatio(true);
		ImageView imgOasisBack = new ImageView(new Image(getClass().getResource("/images/oasisBack.png").toString()));
		imgOasisBack.setFitHeight(200);
		imgOasisBack.setPreserveRatio(true);
		ImageView imgCentralPark = new ImageView(new Image(getClass().getResource("/images/centralPark.png").toString()));
		imgCentralPark.setFitHeight(200);
		imgCentralPark.setPreserveRatio(true);
		ImageView imgCentralPark2 = new ImageView(new Image(getClass().getResource("/images/centralPark2.png").toString()));
		imgCentralPark2.setFitHeight(200);
		imgCentralPark2.setPreserveRatio(true);
		ImageView imgOasisTop = new ImageView(new Image(getClass().getResource("/images/oasisTop.png").toString()));
		imgOasisTop.setFitHeight(200);
		imgOasisTop.setPreserveRatio(true);
		// Label to hold the game's intro & overall instructions
		Label lblIntro = new Label(Passenger.showGameIntro());
		lblIntro.setFont(Font.font(MEDIUM_FONT));
		lblIntro.setWrapText(true);
		// Label to hold game's goal information
		Label lblGoal = new Label(Passenger.showGameGoal());
		lblGoal.setFont(Font.font(MEDIUM_FONT));
		lblGoal.setWrapText(true);
		tileInstructions.getChildren().addAll(imgOasis, imgAquaTheatre, imgOasis2, lblIntro, imgBoardwalk, imgOasisBack, lblGoal, imgCentralPark, imgCentralPark2, imgOasisTop);
		tileInstructions.setPrefTileHeight(210);
		tileInstructions.setPrefTileWidth(240);
		tileInstructions.setPrefHeight(420);
		tileInstructions.setPrefWidth(1200);
		root.getChildren().add(tileInstructions);
		
		// Create scene with root VBox
		Scene mainScene = new Scene(root, 1400, 900);
		
		myStage.setTitle("Cruise Story Game");
		myStage.setScene(mainScene);
		myStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}