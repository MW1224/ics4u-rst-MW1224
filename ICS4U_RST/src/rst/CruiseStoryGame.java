package rst;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
	private static final int SMALL_FONT = 15, MEDIUM_FONT = 20, LARGE_FONT = 26;
	
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
		
		
		// Set layout
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Load from file
		int highScore = FileHandler.loadFromFile();
		passenger = new Passenger(highScore);
		
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
		root.getChildren().add(tileInstructions);	// Add this TilePane to the Vbox root
		
		// GridPane to obtain input for passenger's information
		GridPane gridPassengerInfo = new GridPane();
		gridPassengerInfo.setHgap(GAP);	// sets gaps between columns
		gridPassengerInfo.setVgap(GAP);	// sets gaps between rows
		
		// Label for instructions for passenger to enter their information
		Label lblPassengerInfo = new Label(Passenger.showPassengerInfoPrompt());
		lblPassengerInfo.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblPassengerInfo, 3, 0, 4, 1); // pos (3,0), colspan = 4, rowspan = 1
		
		// Label for passenger's first name
		Label lblFirstName = new Label("First name:");
		lblFirstName.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblFirstName, 0, 1, 2, 1);	// pos (0,1), colspan = 2, rowspan = 1
		// TextField for passenger's first name
		TextField txtFirstName = new TextField();
		gridPassengerInfo.add(txtFirstName, 2, 1, 2, 1);	// pos (2,1), colspan = 2, rowspan = 1
		// Label for passenger's last name
		Label lblLastName = new Label("Last name:");
		lblLastName.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblLastName, 6, 1, 2, 1);	// pos (6,1), colspan = 2, rowspan = 1
		// TextField for passenger's first name
		TextField txtLastName = new TextField();
		gridPassengerInfo.add(txtLastName, 8, 1, 2, 1);	// pos (8,1), colspan = 2, rowspan = 1
		// Label for passenger's email
		Label lblEmail = new Label("Email:");
		lblEmail.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblEmail, 1, 2);	// pos (1,2), colspan = 1, rowspan = 1
		// TextField for passenger's email
		TextField txtEmail = new TextField();
		gridPassengerInfo.add(txtEmail, 2, 2, 2, 1);	// pos (2,2), colspan = 2, rowspan = 1
		// Label for passenger's phone number
		Label lblPhoneNumber = new Label("Phone number:");
		lblPhoneNumber.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblPhoneNumber, 6, 2, 2, 1);	// pos (6,2), colspan = 2, rowspan = 1
		// TextField for passenger's phone number
		TextField txtPhoneNumber = new TextField();
		gridPassengerInfo.add(txtPhoneNumber, 8, 2, 2, 1);	// pos (8,2), colspan = 2, rowspan = 1
		// Label for passenger's loyalty status
		Label lblLoyaltyStatus = new Label("Crown & Anchor Society status:");
		lblLoyaltyStatus.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblLoyaltyStatus, 1, 3, 2, 1);	// pos (1,3), colspan = 2, rowspan = 1
		// ChoiceBox for loyalty status options
		ChoiceBox<String> chcLoyaltyStatus = new ChoiceBox<String>();
		chcLoyaltyStatus.getItems().addAll(Passenger.LOYALTY_STATUSES);
		gridPassengerInfo.add(chcLoyaltyStatus, 3, 3, 2, 1);	// pos (3,3), colspan = 2, rowspan = 1
		// Label to display overall high score between all games played before
		Label lblHighScore = new Label(passenger.showHighScore());
		lblHighScore.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(lblHighScore, 1, 4, 2, 1);	// pos (1,4), colspan = 2, rowspan = 1
		// Button for event handler to reset high score out of all games played to $0
		Button btnResetHighScore = new Button("Reset high score counter");
		btnResetHighScore.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnResetHighScore, 4, 4, 2, 1);	// pos (4,4), colspan = 2, rowspan = 1
		btnResetHighScore.setOnAction(event -> resetHighScore());
		// Button for event handler to create passenger with their information
		Button btnFinish = new Button("Done entering info");
		btnFinish.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnFinish, 8, 4, 2, 1);	// pos (8,4), colspan = 1, rowspan = 1
		// Add this GridPane to the Vbox root
		gridPassengerInfo.setAlignment(Pos.CENTER);
		root.getChildren().add(gridPassengerInfo);
		
		// Create scene with root VBox
		Scene mainScene = new Scene(root, 1300, 900);
		
		myStage.setTitle("Cruise Story Game");
		myStage.setScene(mainScene);
		myStage.show();
	}
	
	private void resetHighScore() {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}