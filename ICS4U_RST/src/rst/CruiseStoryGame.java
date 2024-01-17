package rst;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author Mayra Wang
 * Date: January 2024
 * Course: ICS4U
 * CruiseStoryGame.java
 * RST
 */
public class CruiseStoryGame extends Application {
	
	// Global constants
	private static final int GAP = 10, SMALL_GAP = 3;
	private static final int SMALL_FONT = 15, MEDIUM_FONT = 17, LARGE_FONT = 20, XL_FONT = 26;
	
	// References for global objects
	private Passenger passenger;
	private RouteCard[][] routeGrid;
	
	// Global variables
	private boolean eventDone;
	
	// Input/output UI controls
	private Label lblTitle, lblInstructions, lblResult, lblRemainingMoney, lblErrorMessage;
	// For Cruise Story Game main screen
	private Label lblHighScore;
	private TextField txtFirstName, txtLastName, txtEmail, txtPhoneNumber;
	private ChoiceBox<String> chcLoyaltyStatus;
	private Scene mainScene;
	// For Cruise Route Random Selection screen
	private Label lblRoute;
	// For Packing Scenario screen
	private PackingScenario packingScenario;
	private int[] itemQuantities = new int[PackingScenario.NUM_OF_ITEMS];
	private int itemIndex;
	private Label lblWeight;
	
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
		final int TILE_HEIGHT = 185, TILE_WIDTH = 200;
		final int IMG_HEIGHT = 175;
		final int SCREEN_WIDTH = 1100, SCREEN_HEIGHT = 900;
		
		// Set layout
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Load from file
		int highScore = FileHandler.loadFromFile();
		passenger = new Passenger(highScore);
		
		// Add title Label
		lblTitle = new Label("Welcome to the Cruise Story Game!");
		lblTitle.setFont(Font.font(XL_FONT));
		root.getChildren().add(lblTitle);
		
		// Add ImageView holding Royal Caribbean's logo
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo.png").toString()));
		root.getChildren().add(imgLogo);
		
		// TilePane layout
		TilePane tileInstructions = new TilePane(GAP, 0);
		tileInstructions.setAlignment(Pos.CENTER);
		
		// ImageViews to hold images of Oasis of the Seas
		ImageView imgOasis = new ImageView(new Image(getClass().getResource("/images/oasis.png").toString()));
		imgOasis.setFitHeight(IMG_HEIGHT);
		imgOasis.setPreserveRatio(true);
		
		ImageView imgAquaTheatre = new ImageView(new Image(getClass().getResource("/images/aquaTheatre.png").toString()));
		imgAquaTheatre.setFitHeight(IMG_HEIGHT);
		imgAquaTheatre.setPreserveRatio(true);
		
		ImageView imgOasis2 = new ImageView(new Image(getClass().getResource("/images/oasis2.png").toString()));
		imgOasis2.setFitHeight(IMG_HEIGHT);
		imgOasis2.setPreserveRatio(true);
		
		ImageView imgBoardwalk = new ImageView(new Image(getClass().getResource("/images/boardwalk.png").toString()));
		imgBoardwalk.setFitHeight(IMG_HEIGHT);
		imgBoardwalk.setPreserveRatio(true);
		
		ImageView imgOasisBack = new ImageView(new Image(getClass().getResource("/images/oasisBack.png").toString()));
		imgOasisBack.setFitHeight(IMG_HEIGHT);
		imgOasisBack.setPreserveRatio(true);
		
		ImageView imgCentralPark = new ImageView(new Image(getClass().getResource("/images/centralPark.png").toString()));
		imgCentralPark.setFitHeight(IMG_HEIGHT);
		imgCentralPark.setPreserveRatio(true);
		
		ImageView imgCentralPark2 = new ImageView(new Image(getClass().getResource("/images/centralPark2.png").toString()));
		imgCentralPark2.setFitHeight(IMG_HEIGHT);
		imgCentralPark2.setPreserveRatio(true);
		
		ImageView imgOasisTop = new ImageView(new Image(getClass().getResource("/images/oasisTop.png").toString()));
		imgOasisTop.setFitHeight(IMG_HEIGHT);
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
		tileInstructions.setPrefTileHeight(TILE_HEIGHT);
		tileInstructions.setPrefTileWidth(TILE_WIDTH);
		tileInstructions.setPrefHeight(TILE_HEIGHT * 2);
		tileInstructions.setPrefWidth(TILE_WIDTH * 5);
		root.getChildren().add(tileInstructions);	// Add this TilePane to the Vbox root
		
		// GridPane to obtain input for passenger's information
		GridPane gridPassengerInfo = new GridPane();
		gridPassengerInfo.setHgap(GAP);	// sets gaps between columns
		gridPassengerInfo.setVgap(GAP);	// sets gaps between rows
		
		// Label for instructions for passenger to enter their information
		Label lblPassengerInfo = new Label(Passenger.showPassengerInfoPrompt());
		lblPassengerInfo.setFont(Font.font(LARGE_FONT));
		gridPassengerInfo.add(lblPassengerInfo, 3, 0, 4, 1); // pos (3,0), colspan = 4, rowspan = 1
		
		// Label for passenger's first name
		Label lblFirstName = new Label("First name:");
		lblFirstName.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblFirstName, 0, 1, 2, 1);	// pos (0,1), colspan = 2, rowspan = 1
		// TextField for passenger's first name
		txtFirstName = new TextField();
		gridPassengerInfo.add(txtFirstName, 2, 1, 2, 1);	// pos (2,1), colspan = 2, rowspan = 1
		// Label for passenger's last name
		Label lblLastName = new Label("Last name:");
		lblLastName.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblLastName, 6, 1, 2, 1);	// pos (6,1), colspan = 2, rowspan = 1
		// TextField for passenger's first name
		txtLastName = new TextField();
		gridPassengerInfo.add(txtLastName, 8, 1, 2, 1);	// pos (8,1), colspan = 2, rowspan = 1
		// Label for passenger's email
		Label lblEmail = new Label("Email:");
		lblEmail.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblEmail, 1, 2);	// pos (1,2), colspan = 1, rowspan = 1
		// TextField for passenger's email
		txtEmail = new TextField();
		gridPassengerInfo.add(txtEmail, 2, 2, 2, 1);	// pos (2,2), colspan = 2, rowspan = 1
		// Label for passenger's phone number
		Label lblPhoneNumber = new Label("Phone number:");
		lblPhoneNumber.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblPhoneNumber, 6, 2, 2, 1);	// pos (6,2), colspan = 2, rowspan = 1
		// TextField for passenger's phone number
		txtPhoneNumber = new TextField();
		gridPassengerInfo.add(txtPhoneNumber, 8, 2, 2, 1);	// pos (8,2), colspan = 2, rowspan = 1
		// Label for passenger's loyalty status
		Label lblLoyaltyStatus = new Label("Crown & Anchor Society status:");
		lblLoyaltyStatus.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblLoyaltyStatus, 1, 3, 2, 1);	// pos (1,3), colspan = 2, rowspan = 1
		// ChoiceBox for loyalty status options
		chcLoyaltyStatus = new ChoiceBox<String>();
		chcLoyaltyStatus.getItems().addAll(Passenger.LOYALTY_STATUSES);
		gridPassengerInfo.add(chcLoyaltyStatus, 3, 3, 2, 1);	// pos (3,3), colspan = 2, rowspan = 1
		// Label to display overall high score between all games played before
		lblHighScore = new Label(passenger.showHighScore());
		lblHighScore.setFont(Font.font(MEDIUM_FONT));
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
		btnFinish.setOnAction(event -> addPassengerInfo());
		// Add this GridPane to the VBox root
		gridPassengerInfo.setAlignment(Pos.CENTER);
		root.getChildren().add(gridPassengerInfo);
		
		// Add label in case of error message to bottom of VBox root
		lblResult = new Label("");
		lblResult.setFont(Font.font(SMALL_FONT));
		root.getChildren().add(lblResult);
		
		// Create scene with root VBox
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		myStage.setTitle("Cruise Story Game");
		myStage.setScene(mainScene);
		myStage.show();
	}
	
	private void resetHighScore() {
		passenger.resetHighScore();
		lblHighScore.setText(passenger.showHighScore());
	}
	
	private void addPassengerInfo() {
		String firstName, lastName, email, phoneNumber, loyaltyStatus;
		firstName = txtFirstName.getText().trim();
		lastName = txtLastName.getText().trim();
		email = txtEmail.getText().trim();
		phoneNumber = txtLastName.getText().trim();
		loyaltyStatus = chcLoyaltyStatus.getValue();
		
		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || loyaltyStatus == null) {
			lblResult.setText("Please enter information in all fields! (no empty boxes)");
			return;
		}
		
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setEmail(email);
		passenger.setPhoneNumber(phoneNumber);
		passenger.setLoyaltyStatus(loyaltyStatus);
		
		showCruiseRouteScreen();
	}
	
	private void showCruiseRouteScreen() {
		// Local constants
		final int GRID_DIMENSION = 4;
		final int TILE_DIMENSION = RouteCard.CARD_DIMENSION + 10;
		final int SCREEN_WIDTH = 790, SCREEN_HEIGHT = 900;
		
		// Local variable
		int cardNum = 1;
		
		// Root node for this JavaFX scene graph
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// TOP section of the BorderPane layout
		VBox vbxTop = new VBox();
		vbxTop.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxTop.setAlignment(Pos.CENTER);
		// Label for title
		lblTitle.setText("Cruise Route Random Selection");
		// Label for instructions
		lblInstructions = new Label(RouteCard.showInstructions());
		lblInstructions.setFont(Font.font(MEDIUM_FONT));
		lblInstructions.setWrapText(true);
		// Add to parent node VBox
		vbxTop.getChildren().addAll(lblTitle, lblInstructions);
		root.setTop(vbxTop);
		
		// CENTER section of the BorderPane layout
		TilePane tileCenter = new TilePane(GAP, GAP);
		tileCenter.setPrefColumns(GRID_DIMENSION);
		tileCenter.setPrefTileHeight(TILE_DIMENSION);
		tileCenter.setPrefTileWidth(TILE_DIMENSION);
		tileCenter.setPrefSize(TILE_DIMENSION * GRID_DIMENSION, TILE_DIMENSION * GRID_DIMENSION);
		
		// Create 4x4 grid for RouteCards
		routeGrid = new RouteCard[GRID_DIMENSION][GRID_DIMENSION];
		
		for (int row = 0; row < routeGrid.length; row++) {
			for (int col = 0; col < routeGrid[row].length; col++) {
				routeGrid[row][col] = new RouteCard(cardNum);	// instantiate each RouteCard in the 2D array
				routeGrid[row][col].setOnAction(event -> selectRoute(event));
				tileCenter.getChildren().add(routeGrid[row][col]);
				
				cardNum++;
			}
		}
		root.setCenter(tileCenter);
		
		// LEFT section of the BorderPane layout
		lblRemainingMoney = new Label(passenger.showMoneyLeft());
		lblRemainingMoney.setFont(Font.font(MEDIUM_FONT));
		lblRemainingMoney.setPrefWidth(100);
		lblRemainingMoney.setWrapText(true);
		root.setLeft(lblRemainingMoney);
		BorderPane.setAlignment(lblRemainingMoney, Pos.CENTER);
		
		// RIGHT section of the BorderPane layout
		Button btnNext = new Button("Next");
		btnNext.setFont(Font.font(MEDIUM_FONT));
		root.setRight(btnNext);
		BorderPane.setAlignment(btnNext, Pos.CENTER);
		btnNext.setOnAction(event -> showPackingScenario());
		
		// Bottom section of the BorderPane layout
		lblRoute = new Label("");
		lblRoute.setFont(Font.font(MEDIUM_FONT));
		lblRoute.setWrapText(true);
		root.setBottom(lblRoute);
		BorderPane.setAlignment(lblRoute, Pos.CENTER);
		
		Window mainWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (mainWindow instanceof Stage) {
			Stage myStage = (Stage) mainWindow;
			myStage.setTitle("Cruise Route Random Selection");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	private void selectRoute(ActionEvent event) {
		// The getSource() method returns the Object (RouteCard that was clicked) that generated this event
		RouteCard temp = (RouteCard) event.getSource();	// cast to RouteCard object to access getValue() method from RouteCard class
	
		// Select this route card
		temp.selectRouteCard();
		
		// Change remaining money left
		boolean disabledCard = temp.getState();
		if (!disabledCard) {
			passenger.updateTotalMoney(temp.getChangeInMoney());
			lblRemainingMoney.setText(passenger.showMoneyLeft());
					
			lblRoute.setText(temp.showResult());
		}
		
		// Disable all cards so user can only select one route
		for (int row = 0; row < routeGrid.length; row++) {
			for (int col = 0; col < routeGrid[row].length; col++) {
				routeGrid[row][col].disableCard();
			}
		}
	}
	
	private void showPackingScenario() {
		// Local constants
		final int SCREEN_WIDTH = 1200, SCREEN_HEIGHT = 900;
		final int FIRST_ROWS_ITEMS = (PackingScenario.NUM_OF_ITEMS - 7)/2;
		final String[] ITEMS = PackingScenario.getItems();
				
		// Local variables
		boolean noRouteSelected = true;
		packingScenario = new PackingScenario();
		
		// Make sure user selected a route from previous scene
		for (int row = 0; row < routeGrid.length; row++) {
			for (int col = 0; col < routeGrid[row].length; col++) {
				if (routeGrid[row][col].getState()) {	// if card is disabled
					noRouteSelected = false;
				}
			}
		}
		
		// User hasn't clicked "Finish Packing" button yet
		eventDone = false;
		
		if (noRouteSelected) {
			lblRoute.setText("Error: You must select a route before moving on");
			return;
		}
		
		// Root node for this JavaFX scene graph
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Add title Label
		lblTitle.setText(packingScenario.toString());
		root.getChildren().add(lblTitle);
		
		// Label to show weight limit
		Label lblWeightLimit = new Label(PackingScenario.showWeightLimit());
		lblWeightLimit.setFont(Font.font(MEDIUM_FONT));
		
		// Add Label to show instructions
		lblInstructions.setText(PackingScenario.showInstructions());
		lblInstructions.setFont(Font.font(MEDIUM_FONT));
		root.getChildren().addAll(lblWeightLimit, lblInstructions);
		
		// Add HBox for 3 columns of activities and suitcase image showing its current weight
		HBox hbxItems = new HBox(GAP * 2);
		VBox vbxCol1 = new VBox(SMALL_GAP), vbxCol2 = new VBox(SMALL_GAP), vbxCol3 = new VBox(SMALL_GAP);
		
		ArrayList<Spinner<Integer>> spnItemQuantities = new ArrayList<Spinner<Integer>>();
		
		itemIndex = 0;
		while (itemIndex < PackingScenario.NUM_OF_ITEMS) {
			HBox hbxRow = new HBox(GAP);
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
			valueFactory.setValue(0);
			
			Label lblActivity = new Label(ITEMS[itemIndex] + "  ");
			lblActivity.setFont(Font.font(SMALL_FONT));
			
			spnItemQuantities.add(itemIndex, new Spinner<Integer>());
			Spinner<Integer> spnItemQuantity = spnItemQuantities.get(itemIndex);
			
			spnItemQuantity.setValueFactory(valueFactory);
			itemQuantities[itemIndex] = spnItemQuantity.getValue();
			
			spnItemQuantity.valueProperty().addListener(new ChangeListener<Integer>() {
				public void changed(ObservableValue<? extends Integer> ov, Integer old_val, Integer new_val) {
					itemQuantities[spnItemQuantities.indexOf(spnItemQuantity)] = spnItemQuantity.getValue();
				}
			});
			
			hbxRow.getChildren().addAll(lblActivity, spnItemQuantity);
			
			if (itemIndex < FIRST_ROWS_ITEMS) {
				vbxCol1.getChildren().add(hbxRow);
			} else if (itemIndex >= FIRST_ROWS_ITEMS && itemIndex < FIRST_ROWS_ITEMS * 2) {
				vbxCol2.getChildren().add(hbxRow);
			} else {
				vbxCol3.getChildren().add(hbxRow);
			}
			
			itemIndex++;	// increment index
		}
		
		// Suitcase output in the third VBox (3rd column)
		StackPane stackSuitcase = new StackPane();
		ImageView imgSuitcase = new ImageView(new Image(getClass().getResource("/images/suitcase.png").toString()));
		imgSuitcase.setFitHeight(210);
		imgSuitcase.setPreserveRatio(true);
		lblWeight = new Label();
		lblWeight.setFont(Font.font(LARGE_FONT));
		lblWeight.setPrefWidth(90);
		lblWeight.setTextFill(Color.LIGHTCYAN);
		lblWeight.setWrapText(true);
		
		stackSuitcase.getChildren().addAll(imgSuitcase, lblWeight);
		stackSuitcase.setAlignment(Pos.CENTER);
		vbxCol3.getChildren().add(stackSuitcase);
		
		hbxItems.getChildren().addAll(vbxCol1, vbxCol2, vbxCol3);
		hbxItems.setAlignment(Pos.CENTER);
		root.getChildren().add(hbxItems);
		
		// Button to check weight
		Button btnFinishPacking = new Button("Finish Packing");
		btnFinishPacking.setFont(Font.font(SMALL_FONT));
		btnFinishPacking.setOnAction(event -> checkWeight());
		
		// Adjust width of remaining money label
		lblRemainingMoney.setPrefWidth(200);
		
		// Label to output result
		lblResult.setText("");
		
		// Label to output any possible error messages later
		lblErrorMessage = new Label("");
		lblErrorMessage.setFont(Font.font(SMALL_FONT));
		
		// Button to move to next scene
		Button btnStartCruise = new Button("Start Cruise Trip!");
		btnStartCruise.setFont(Font.font(SMALL_FONT));
		btnStartCruise.setOnAction(event -> showMiniGames());
		
		root.getChildren().addAll(btnFinishPacking, lblResult, lblRemainingMoney, lblErrorMessage, btnStartCruise);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("Packing Scenario");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	private void checkWeight() {
		// If user already finished packing
		if (eventDone) {
			lblErrorMessage.setText("You've already finished packing.");
			return;
		}
		
		lblErrorMessage.setText("");
		
		lblResult.setText(packingScenario.checkWeight(itemQuantities) + "\n" + packingScenario.showChangeInMoney());
		lblWeight.setText("Final weight: " + packingScenario.getWeight() + " lbs");
		
		passenger.updateTotalMoney(packingScenario.getChangeInMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
		
		eventDone = true;
	}
	
	private void showMiniGames() {
		if (!eventDone) {
			lblErrorMessage.setText("Please finish packing before moving on.");
			return;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}