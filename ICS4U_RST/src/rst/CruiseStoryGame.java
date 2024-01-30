package rst;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
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
	private static final int SCREEN_HEIGHT = 900;
	private static final int SMALL_FONT = 15, MEDIUM_FONT = 17, LARGE_FONT = 20, XL_FONT = 26;
	private static final Image CLOSED_LOCK = new Image(CruiseStoryGame.class.getResource("/images/closedLock.png").toString());
	private static final Image OPEN_LOCK = new Image(CruiseStoryGame.class.getResource("/images/openLock.png").toString());
	private static final int LOCK_NUM_INDEX = 5;	// index of lock number in string representation of each lock ("lock #")
	private static final int NUM_OF_POSSIBLE_WORDS = WordUnscramble.NUM_OF_POSSIBLE_WORDS;
	
	// Global variables & input/output UI controls
	private Passenger passenger;
	private int dayNumber = 1;
	private Label lblTitle, lblInstructions, lblResult, lblRemainingMoney, lblErrorMessage, lblBonusMoney;
	private Button btnEndGame, btnStartCruise;
	private Scene mainScene;
	
	// For Cruise Story Game main screen
	private Label lblHighScore;
	private TextField txtFirstName, txtLastName;
	private ChoiceBox<String> chcLoyaltyStatus;
	// For Cruise Route Random Selection screen
	private RouteCard[][] routeGrid;
	private Label lblRoute;
	// For Packing Scenario screen
	private PackingScenario packingScenario;
	private Label lblWeight;
	private int[] itemQuantities;
	private boolean eventDone;
	// For Royal Escape Room Mini Game screen
	private EscapeRoom royalEscapeRoom;
	private ImageView imgLock;
	private HBox hbxSliders;
	private Label lblClue, lblLockNum, lblLockState, lblHint, lblWordPyramid;
	private Button btnHint, btnNextLock, btnEnterWord;
	private ChoiceBox<String> chcLocks;
	private GridPane gridVisual;
	private TextField txtWord, txtLockCombo;
	private Button btnUnlock;
	private int actualLockNum, lockIndex = 0, chosenLockNum;
	private int[] lockComboNums;
	// For Word Unscramble Mini Game screen
	private WordUnscramble wordUnscrambleGame;
	private Button btnDone;
	private TextField[] txtWords;
	private ImageView[] imgOutputs;
	private Label lblPossibleWords;
	// For Activities Scenario screen
	private TextField[] txtNumsPerActivities;
	private Button btnDoneActivities;
	
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
		final int TILE_HEIGHT = 185, TILE_WIDTH = 200;	// dimensions for tiles in TilePane
		final int IMG_HEIGHT = 175;
		final int SCREEN_WIDTH = 1100;
		
		// Set layout (root node for this JavaFX scene graph)
		VBox root = new VBox(GAP);		// instantiate VBox with GAP in between its child nodes
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);	// make sure VBox is centered in the screen
		
		// Load high score from file
		int highScore = FileHandler.loadFromFile();
		
		// Instantiate Passenger object with the high score loaded from file
		passenger = new Passenger(highScore);
		
		// Add title Label to root
		lblTitle = new Label("Welcome to the Cruise Story Game!");
		lblTitle.setFont(Font.font(XL_FONT));
		root.getChildren().add(lblTitle);
		
		// Add ImageView holding Royal Caribbean's logo to root
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo.png").toString()));
		root.getChildren().add(imgLogo);
		
		// TilePane layout for images and instructions
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
		
		// Label to hold the game's overall intro & instructions
		lblInstructions = new Label(Passenger.showGameIntro());
		lblInstructions.setFont(Font.font(MEDIUM_FONT));
		lblInstructions.setWrapText(true);
		
		// Label to hold game's goal information
		Label lblGoal = new Label(Passenger.showGameGoal());
		lblGoal.setFont(Font.font(MEDIUM_FONT));
		lblGoal.setWrapText(true);
		
		// Add the 10 child nodes to the TilePane parent node (the eight images & two labels)
		tileInstructions.getChildren().addAll(imgOasis, imgAquaTheatre, imgOasis2, lblInstructions, imgBoardwalk, imgOasisBack, lblGoal, imgCentralPark, imgCentralPark2, imgOasisTop);
		tileInstructions.setPrefTileHeight(TILE_HEIGHT);
		tileInstructions.setPrefTileWidth(TILE_WIDTH);
		tileInstructions.setPrefHeight(TILE_HEIGHT * 2);
		tileInstructions.setPrefWidth(TILE_WIDTH * 5);
		root.getChildren().add(tileInstructions);	// add this TilePane to the Vbox root
		
		// GridPane for passenger's information input
		GridPane gridPassengerInfo = new GridPane();
		gridPassengerInfo.setAlignment(Pos.CENTER);
		gridPassengerInfo.setHgap(GAP);	// set gaps between columns
		gridPassengerInfo.setVgap(GAP);	// set gaps between rows
		
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
		
		// Label for passenger's loyalty status
		Label lblLoyaltyStatus = new Label("Crown & Anchor Society status:");
		lblLoyaltyStatus.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblLoyaltyStatus, 1, 2, 2, 1);	// pos (1,2), colspan = 2, rowspan = 1
		
		// ChoiceBox for loyalty status options
		chcLoyaltyStatus = new ChoiceBox<String>();
		chcLoyaltyStatus.getItems().addAll(Passenger.LOYALTY_STATUSES);
		gridPassengerInfo.add(chcLoyaltyStatus, 3, 2, 2, 1);	// pos (3,2), colspan = 2, rowspan = 1
		
		// Label to display overall high score between all games played before
		lblHighScore = new Label(passenger.showHighScore());
		lblHighScore.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblHighScore, 1, 3, 2, 1);	// pos (1,3), colspan = 2, rowspan = 1
		
		// Button for event handler to reset high score out of all games played to $0
		Button btnResetHighScore = new Button("Reset high score counter");
		btnResetHighScore.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnResetHighScore, 4, 3, 2, 1);	// pos (4,3), colspan = 2, rowspan = 1
		btnResetHighScore.setOnAction(event -> resetHighScore());
		
		// Button for event handler to create passenger with their information
		Button btnFinish = new Button("Done entering info");
		btnFinish.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnFinish, 8, 3, 2, 1);	// pos (8,3), colspan = 2, rowspan = 1
		btnFinish.setOnAction(event -> addPassengerInfo());
	
		root.getChildren().add(gridPassengerInfo);	// add this GridPane to the Vbox root
		
		// Add label in case of error message to bottom of VBox root
		lblErrorMessage = new Label("");
		lblErrorMessage.setFont(Font.font(SMALL_FONT));
		root.getChildren().add(lblErrorMessage);
		
		// Create scene with root VBox
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		myStage.setTitle("Cruise Story Game");
		myStage.setScene(mainScene);
		myStage.show();
	}
	
	/**
	 * Method to reset high score out all games - event handler method for
	 * the "Reset high score counter" Button. Updates the high score Label to
	 * show the new high score of $0.
	 */
	private void resetHighScore() {
		passenger.resetHighScore();		// reset high score to $0
		lblHighScore.setText(passenger.showHighScore());	// output new high score of $0
	}
	
	/**
	 * Method to add information to Passenger object - event handler method for
	 * the "Done entering info" Button. Also, validates user input to make sure
	 * TextFields are not empty. If there is no problem with user input, it shows the
	 * next screen (Cruise Route Random Selection).
	 */
	private void addPassengerInfo() {
		// Local variables to hold values from TextFields
		String firstName, lastName, loyaltyStatus;
		
		// Store input from UI controls in the local variables
		firstName = txtFirstName.getText().trim();
		lastName = txtLastName.getText().trim();
		loyaltyStatus = chcLoyaltyStatus.getValue();
		
		// Input validation: Make sure all TextFields are not empty & a loyalty status is chosen from the menu
		if (firstName.isEmpty() || lastName.isEmpty() || loyaltyStatus == null) {
			lblErrorMessage.setText("Please enter information in all fields! (no empty boxes)");	// output error message
			return;
		}
		
		// Set passenger's properties
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setLoyaltyStatus(loyaltyStatus);
		
		// Show next screen: Cruise Route Random Selection
		showCruiseRouteScreen();
	}
	
	/**
	 * Method to add information to Passenger object - event handler method for
	 * the "Done entering info" Button. Also, validates user input to make sure
	 * TextFields are not empty. If there is no problem with user input, it shows the
	 * next screen (Cruise Route Random Selection).
	 */
	private void showCruiseRouteScreen() {
		
		// Local constants
		final int GRID_DIMENSION = 4;	// number of columns for TilePane
		final int TILE_DIMENSION = RouteCard.CARD_DIMENSION + 10;
		final int SCREEN_WIDTH = 790;
		
		// Local variable
		int cardNum = 1;
		
		// Set layout (root node for this JavaFX scene graph)
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// TOP section of the BorderPane layout
		// Parent node: VBox
		VBox vbxTop = new VBox(SMALL_GAP);
		vbxTop.setPadding(new Insets(GAP, GAP, GAP, GAP));
		vbxTop.setAlignment(Pos.CENTER);
		
		// Label for title
		lblTitle.setText("Cruise Route Random Selection");
		
		// Label for instructions
		lblInstructions.setText(RouteCard.showInstructions());
		
		// Add the 2 Labels to VBox parent node
		vbxTop.getChildren().addAll(lblTitle, lblInstructions);
		root.setTop(vbxTop);	// add VBox to root node
		
		// CENTER section of the BorderPane layout
		// Parent node: TilePane
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
				routeGrid[row][col].setOnAction(event -> selectRoute(event));	// add event handler for each RouteCard
				tileCenter.getChildren().add(routeGrid[row][col]);	// add RouteCard to parent node (TilePane)
				
				cardNum++;	// increment card number
			}
		}
		
		root.setCenter(tileCenter);	// add TilePane to root node
		
		// LEFT section of the BorderPane layout
		// Label to show passenger's remaining money
		lblRemainingMoney = new Label(passenger.showMoneyLeft());
		lblRemainingMoney.setFont(Font.font(MEDIUM_FONT));
		lblRemainingMoney.setPrefWidth(100);
		lblRemainingMoney.setWrapText(true);
		
		root.setLeft(lblRemainingMoney);
		BorderPane.setAlignment(lblRemainingMoney, Pos.CENTER);
		
		// RIGHT section of the BorderPane layout
		// Add button to show next screen (Packing Scenario)
		Button btnNext = new Button("Next");
		btnNext.setFont(Font.font(MEDIUM_FONT));
		btnNext.setOnAction(event -> showPackingScenarioScreen());
		
		root.setRight(btnNext);
		BorderPane.setAlignment(btnNext, Pos.CENTER);
		
		// BOTTOM section of the BorderPane layout
		// Label to output the cruise's route & its cost
		lblRoute = new Label("");
		lblRoute.setFont(Font.font(MEDIUM_FONT));
		lblRoute.setWrapText(true);
		
		root.setBottom(lblRoute);
		BorderPane.setAlignment(lblRoute, Pos.CENTER);
		
		// Change the scene graph of the previous stage to this one
		Window mainWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (mainWindow instanceof Stage) {
			Stage myStage = (Stage) mainWindow;
			myStage.setTitle("Cruise Route Random Selection");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	/**
	 * Method to flip over card and select its route - event handler method for
	 * the RouteCards. 
	 */
	private void selectRoute(ActionEvent event) {
		// The getSource() method returns the Object (RouteCard that was clicked) that generated this event
		RouteCard temp = (RouteCard) event.getSource();	// cast to RouteCard object to access getValue() method from RouteCard class
	
		// Select this route card
		temp.selectRouteCard();
		
		// Make sure card isn't disabled (that means route has already been chosen)
		boolean disabledCard = temp.getState();	// true if card is disabled, false if not disabled
		
		// Change remaining money left if card isn't disabled
		if (!disabledCard) {
			passenger.updateTotalMoney(temp.getCost());
			lblRemainingMoney.setText(passenger.showMoneyLeft());
					
			lblRoute.setText(temp.showResult());	// output route and its cost
		}
		
		// Disable all cards so user only selects that one route
		for (int row = 0; row < routeGrid.length; row++) {
			for (int col = 0; col < routeGrid[row].length; col++) {
				routeGrid[row][col].disableCard();
			}
		}
	}
	
	/**
	 * Method to show the Packing Scenario screen - event handler method for
	 * the "Next" Button. Also, validates that user actually selected a route from previous
	 * screen (Cruise Route Random Selection).
	 */
	private void showPackingScenarioScreen() {
		// Local constants
		final int SCREEN_WIDTH = 1200;
		final int FIRST_COLS_ITEMS = (PackingScenario.NUM_OF_ITEMS - 7)/2;	// the number of textfields/packing items in the first two columns
		final String[] ITEMS = PackingScenario.getItems();
				
		// Local variables
		boolean noRouteSelected = true;	
		packingScenario = new PackingScenario();
		
		// Make sure user selected a route from previous scene
		// If a card is disabled, that means a route was selected
		for (int row = 0; row < routeGrid.length; row++) {
			for (int col = 0; col < routeGrid[row].length; col++) {
				if (routeGrid[row][col].getState()) {	// if card is disabled
					noRouteSelected = false;
				}
			}
		}
		
		// User hasn't clicked "Finish Packing" button yet
		eventDone = false;
		
		// Output error message if passenger hasn't selected a route yet
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
		
		// Add Label to show weight limit
		Label lblWeightLimit = new Label(PackingScenario.showWeightLimit());
		lblWeightLimit.setFont(Font.font(MEDIUM_FONT));
		root.getChildren().add(lblWeightLimit);
		
		// Add Label to show instructions
		lblInstructions.setText(PackingScenario.showInstructions());
		root.getChildren().add(lblInstructions);
		
		// Add HBox for 3 columns (VBox) of activities and suitcase image showing its current weight
		HBox hbxItems = new HBox(GAP * 2);	// Parent HBox to hold 3 VBoxes
		hbxItems.setAlignment(Pos.CENTER);
		
		// Three VBoxes to hold individual HBoxes for each item
		VBox vbxCol1 = new VBox(SMALL_GAP), vbxCol2 = new VBox(SMALL_GAP), vbxCol3 = new VBox(SMALL_GAP);
		
		// Instantiate an ArrayList of Integer Spinners to obtain user input for all packing items
		ArrayList<Spinner<Integer>> spnItemQuantities = new ArrayList<Spinner<Integer>>();
		itemQuantities = new int[PackingScenario.NUM_OF_ITEMS];	// this is where the values of the spinners will be stored
		
		// For each packing item, add an HBox with a label and spinner to its VBox column
		for (int i = 0; i < itemQuantities.length; i++) {
			HBox hbxRow = new HBox(GAP);	// HBox one of the three VBoxes
			
			// Instantiate Value Factory for Spinner (0 to 100) and set the default to 0
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
			valueFactory.setValue(0);
			
			// Add Label to show the packing item at index i
			Label lblItem = new Label(ITEMS[i] + "  ");
			lblItem.setFont(Font.font(SMALL_FONT));
			
			// Instantiate a Spinner to obtain input for number of each item user wants to pack
			Spinner<Integer> spnItemQuantity = new Spinner<Integer>();
			spnItemQuantity.setValueFactory(valueFactory);
			itemQuantities[i] = spnItemQuantity.getValue();	// add Spinner's value to the int array of number of items
			spnItemQuantities.add(i, spnItemQuantity);	// add Spinner to array of Spinners
			
			// Add Listener for Spinner to deal with every time its value changes
			spnItemQuantity.valueProperty().addListener(new ChangeListener<Integer>() {
				public void changed(ObservableValue<? extends Integer> ov, Integer old_val, Integer new_val) {
					itemQuantities[spnItemQuantities.indexOf(spnItemQuantity)] = spnItemQuantity.getValue();
				}
			});
			
			hbxRow.getChildren().addAll(lblItem, spnItemQuantity);	// add Label & Spinner to parent node HBox
			
			// Depending on the index (of the packng item), add HBox containing item to either the first, second or third column VBox
			if (i < FIRST_COLS_ITEMS) {
				vbxCol1.getChildren().add(hbxRow);
			} else if (i >= FIRST_COLS_ITEMS && i < FIRST_COLS_ITEMS * 2) {
				vbxCol2.getChildren().add(hbxRow);
			} else {
				vbxCol3.getChildren().add(hbxRow);
			}	
		}
		
		// Suitcase output in the 3rd VBox (3rd column)
		StackPane stackSuitcase = new StackPane();	// to print suitcase's final weight (text) on top of a suitcase image
		stackSuitcase.setAlignment(Pos.CENTER);
		
		// Add Image of suitcase with height 210
		ImageView imgSuitcase = new ImageView(new Image(getClass().getResource("/images/suitcase.png").toString()));
		imgSuitcase.setFitHeight(210);
		imgSuitcase.setPreserveRatio(true);
		
		// Add Label to display final weight later
		lblWeight = new Label();
		lblWeight.setFont(Font.font(LARGE_FONT));
		lblWeight.setPrefWidth(90);
		lblWeight.setTextFill(Color.LIGHTCYAN);
		lblWeight.setWrapText(true);
		
		// Add Image & Label to parent node StackPane
		stackSuitcase.getChildren().addAll(imgSuitcase, lblWeight);
		// Add StackPane to parent node VBox (3rd column)
		vbxCol3.getChildren().add(stackSuitcase);
		
		// Add three VBoxes to parent node Hbox that contains the 3 columns of items
		hbxItems.getChildren().addAll(vbxCol1, vbxCol2, vbxCol3);
		// Add HBox to root node
		root.getChildren().add(hbxItems);
		
		// Button for event handler to check weight (finish packing)
		Button btnFinishPacking = new Button("Finish Packing");
		btnFinishPacking.setFont(Font.font(SMALL_FONT));
		btnFinishPacking.setOnAction(event -> checkWeight());
		
		// Adjust width of remaining money label
		lblRemainingMoney.setPrefWidth(200);
		
		// Label to output result, set the text to empty because don't need to show anything yet
		lblResult = new Label("");
		
		// Label to output any possible error messages later, set it to blank for now
		lblErrorMessage.setText("");
		
		// Button for event handler to show next scene
		btnStartCruise = new Button("Start Cruise Trip!");
		btnStartCruise.setFont(Font.font(SMALL_FONT));
		btnStartCruise.setOnAction(event -> showMiniGamesScreen());
		
		// Add Button ("Finish Packing"), 3 labels and Button ("Start Cruise Trip!") to root node
		root.getChildren().addAll(btnFinishPacking, lblResult, lblRemainingMoney, lblErrorMessage, btnStartCruise);
		
		// Change the scene graph of the previous stage to this one
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("Packing Scenario");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	/**
	 * Method to show results of passenger's suitcase's weight - event handler method for
	 * the "Finish Packing" Button. Also, validates that user isn't clicking the button again
	 * after already finishing packing.
	 */
	private void checkWeight() {
		// If user already finished packing
		if (eventDone) {
			lblErrorMessage.setText("You've already finished packing.");
			return;
		}
		
		// Remove error message that might've appeared (if user tried to move on to next screen before finishing packing)
		lblErrorMessage.setText("");
		
		// Output result (if suitcase is under, over or on the weight limit) & the change in money
		lblResult.setText(packingScenario.checkWeight(itemQuantities) + "\n" + packingScenario.showChangeInMoney());
		
		// Output the suitcase's final weight on the suitcase image
		lblWeight.setText("Final weight: " + packingScenario.getWeight() + " lbs");
		
		// Get remaining money for the passenger's overall game
		passenger.updateTotalMoney(packingScenario.getChangeInMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
		
		// Set eventDone to true to prevent user packing another time
		eventDone = true;
	}
	
	/**
	 * Method to show Mini Game selection screen - event handler method for btnStartCruise.
	 * Also, validates that user has finished packing scenario before moving on to mini
	 * games.
	 */
	private void showMiniGamesScreen() {
		// Local constants
		final int SCREEN_WIDTH = 400, GAMES_SCREEN_HEIGHT = 400;
		
		// If it is after the last day (2), show the end screen because have no mini games left to play
		if (dayNumber == 3) {
			showEndScreen();
		} else {
			// Validates that passenger actually finished the Packing Scenario before moving on to mini games
			if (!eventDone) {
				lblErrorMessage.setText("Please finish activity before moving on.");
				return;
			}
			
			// Get the passenger's mini games to play and store in local variable
			ArrayList<String> miniGamesLeft = passenger.getMiniGamesLeft();
			
			// Root node for this JavaFX scene graph
			VBox root = new VBox(GAP);
			root.setPadding(new Insets(GAP, GAP, GAP, GAP));
			root.setAlignment(Pos.CENTER);
			
			// Label for title (shows which day it is)
			lblTitle.setText("Day " + dayNumber);
			
			// Label for instructions
			lblInstructions.setText("Select a mini game:");
			lblInstructions.setFont(Font.font(LARGE_FONT));
		
			// Add two Labels to root node
			root.getChildren().addAll(lblTitle, lblInstructions);
			
			// Toggle group with RadioButtons for mini game(s) options left to play - user must select one
			ToggleGroup tgMiniGames = new ToggleGroup();
			
			// Add each mini game as a RadioButton
			for (String miniGame : miniGamesLeft) {
				RadioButton radMiniGame = new RadioButton(miniGame);
				radMiniGame.setFont(Font.font(LARGE_FONT));
				radMiniGame.setToggleGroup(tgMiniGames);	// add to same ToggleGroup as other mini game
				radMiniGame.setOnAction(event -> playMiniGame(event));
				
				root.getChildren().add(radMiniGame);	// add each RadioButton to root node
			}
			
			// Instantiate bonus money label to be used in either mini game (depends on which one gets clicked first, so must instantiate here)
			lblBonusMoney = new Label("");
			lblBonusMoney.setFont(Font.font(MEDIUM_FONT));
			
			// Instantiate "End Game" Button be used in either mini game
			btnEndGame = new Button("End Game");
			btnEndGame.setFont(Font.font(MEDIUM_FONT));
			btnEndGame.setOnAction(event -> showActivitiesScreen());
			
			// Change the scene graph of the previous stage to this one
			Window scenarioWindow = mainScene.getWindow();
			mainScene = new Scene(root, SCREEN_WIDTH, GAMES_SCREEN_HEIGHT);
			
			if (scenarioWindow instanceof Stage) {
				Stage myStage = (Stage) scenarioWindow;
				myStage.setTitle("Day " + dayNumber + " - Mini Games");
				myStage.setScene(mainScene);
				myStage.show();
			}
		}
	}
	
	/**
	 * Method to call a mini game's method to play it - event handler method
	 * for either RadioButton.
	 */
	private void playMiniGame(ActionEvent event) {
		// Get the RadioButton that was selected
		RadioButton temp = (RadioButton)event.getSource();
		
		// Launch the screen of the mini game that was clicked on
		if (temp.getText().equals(EscapeRoom.NAME)) {	// if passenger picked Royal Escape Room
			royalEscapeRoom = new EscapeRoom();		// instantiate EscapeRoom here
			passenger.removeMiniGame(temp.getText());	// remove mini game from ArrayList stored in passenger object, so doesn't appear on screen next time
			showEscapeRoomScreen();	// show Escape Room scene
		} else if (temp.getText().equals(WordUnscramble.NAME)) {	// if passenger picked Word Unscramble
			wordUnscrambleGame = new WordUnscramble();		// instantiate EscapeRoom here
			passenger.removeMiniGame(temp.getText());	// remove mini game from ArrayList stored in passenger object, so doesn't appear on screen next time
			showWordUnscrambleScreen();	// show Escape Room scene
		}
	}
	
	/**
	 * Method to show the Royal Escape Room screen.
	 */
	private void showEscapeRoomScreen() {
		// Local constant
		final int SCREEN_WIDTH = 1250; 
		
		// Set actual lock number to the constant at lockIndex (increments by one for every subsequent lock/clue)
		// The constant holds the locks that will be revealed to the Passenger in the GAME's order (2, 1, 3, 5, 4)
		actualLockNum = EscapeRoom.LOCK_NUMS[lockIndex];
		
		// Root node for this JavaFX scene graph
		VBox root = new VBox(GAP);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// Label for title
		lblTitle.setText(EscapeRoom.NAME);
		
		// Label for instructions
		lblInstructions.setText(EscapeRoom.showInstructions());
		lblInstructions.setFont(Font.font(MEDIUM_FONT));
	
		// Add two Labels to root node
		root.getChildren().addAll(lblTitle, lblInstructions);
		
		// Add Label for clue
		lblClue = new Label("Clue #" + (lockIndex + 1) + ": " + royalEscapeRoom.getLockClue(actualLockNum));
		lblClue.setFont(Font.font(MEDIUM_FONT));
		lblClue.setWrapText(true);
		root.getChildren().add(lblClue);
		
		// Add GridPane to show visual clue aid
		gridVisual = new GridPane();
		gridVisual.setHgap(GAP);	// sets gaps between columns
		gridVisual.setVgap(GAP);	// sets gaps between rows
		gridVisual.setAlignment(Pos.CENTER);
		showGridVisual();	// this method will fill the GridPane with images if applicable
		root.getChildren().add(gridVisual);
		
		// Hint section (HBox parent node)
		HBox hbxHint = new HBox(GAP);
		hbxHint.setAlignment(Pos.CENTER);
		
		// Add Button for event handler to show hint
		btnHint = new Button("Get Hint");
		btnHint.setFont(Font.font(SMALL_FONT));
		btnHint.setOnAction(event -> showHint());
		
		// Add Label for error message just in case of invalid user input later, but set it to blank for now
		lblErrorMessage.setText("");
		
		// Add Button and Label to parent node HBox, then add the HBox to root node
		hbxHint.getChildren().addAll(btnHint, lblErrorMessage);
		root.getChildren().add(hbxHint);
		
		// Add Label to output hint
		lblHint = new Label("");
		lblHint.setFont(Font.font(SMALL_FONT));
		lblHint.setWrapText(true);
		root.getChildren().add(lblHint);
		
		// Section for user to pick which lock to open (parent node FlowPane)
		FlowPane flowLockSelection = new FlowPane();
		flowLockSelection.setAlignment(Pos.CENTER);
		flowLockSelection.setHgap(GAP);
		flowLockSelection.setPrefWidth(SCREEN_WIDTH);
	
		// Add ImageView to hold picture of Royal Escape Room's logo on Oasis of the Seas
		ImageView imgEscapeRoom = new ImageView(new Image(getClass().getResource("/images/escapeRoom.png").toString()));
		imgEscapeRoom.setFitHeight(120);
		imgEscapeRoom.setPreserveRatio(true);
		
		// Label for lock selection instructions
		Label lblLockSelection = new Label("Choose a lock to open:");
		lblLockSelection.setFont(Font.font(MEDIUM_FONT));
		
		// ChoiceBox for lock choices
		chcLocks = new ChoiceBox<String>();
		setLockChoiceBox();	// adds locks to the ChoiceBox
		
		// StackPane for Lock Image & Text output
		StackPane stackLock = new StackPane();
		stackLock.setAlignment(Pos.CENTER);
		
		// Add ImageView to hold lock's image to parent StackPane
		imgLock = new ImageView();
		imgLock.setFitHeight(120);
		imgLock.setPreserveRatio(true);
		imgLock.setPreserveRatio(true);
		
		// Add parent VBox that will hold two labels
		VBox vbxLock = new VBox();
		vbxLock.setAlignment(Pos.CENTER);
		
		// Add Label to show chosen lock's number
		lblLockNum = new Label();
		lblLockNum.setFont(Font.font(XL_FONT));
		lblLockNum.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(1), Insets.EMPTY)));
		
		// Add Label to show chosen lock's state (unlocked or locked)
		lblLockState = new Label();
		lblLockState.setFont(Font.font(SMALL_FONT));
		lblLockState.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(1), Insets.EMPTY)));
		
		// Changes the imgLock's Image depending on if the lock is unlocked or locked 
		setLockImage();
		
		// Add two child Labels to parent VBox, then add child VBox to parent StackPane
		vbxLock.getChildren().addAll(lblLockNum, lblLockState);
		stackLock.getChildren().addAll(imgLock, vbxLock);
		
		// Add Image of lifeboat for visuals in escape room game
		ImageView imgLifeBoat = new ImageView(new Image(getClass().getResource("/images/lifeBoat.png").toString()));
		imgLifeBoat.setFitHeight(120);
		imgLifeBoat.setPreserveRatio(true);
		
		// Add image (escape room), label, choicebox, stackpane and image (lifeboat) to parent FlowPane
		flowLockSelection.getChildren().addAll(imgEscapeRoom, lblLockSelection, chcLocks, stackLock, imgLifeBoat);
		// Add FlowPane to root node
		root.getChildren().add(flowLockSelection);
		
		// Section for lock combo input (HBox parent node)
		HBox hbxLockCombo = new HBox(GAP);
		hbxLockCombo.setPadding(new Insets(GAP, GAP, GAP, GAP));
		hbxLockCombo.setAlignment(Pos.CENTER);
		
		// Add Label to prompt for lock combination to parent HBox
		Label lblLockCombo = new Label("Enter lock combination for lock " + chosenLockNum + ":");
		lblLockCombo.setFont(Font.font(MEDIUM_FONT));
		hbxLockCombo.getChildren().add(lblLockCombo);
		
		// Add sliders (changes depending on chosen lock number from ChoiceBox)
		hbxSliders = new HBox(GAP); // Each slider will vertically appear in a column
		setSliders();
		
		// Add Listener for ChoiceBox (which will change the lock's image, the number of sliders, chosen lock number variable and the combination prompt
		chcLocks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_lock, String new_lock) {
				chosenLockNum = Integer.valueOf(String.valueOf(new_lock.charAt(LOCK_NUM_INDEX)));
				lblLockCombo.setText("Enter lock combination for lock " + chosenLockNum + ":");
				setLockImage();
				setSliders();
			}
		});
		
		// Add Button for event handler to attempt to open lock
		btnUnlock = new Button("Unlock");
		btnUnlock.setFont(Font.font(SMALL_FONT));
		btnUnlock.setOnAction(event -> openLock());
		
		// Add all child Sliders & Button to parent HBox
		hbxLockCombo.getChildren().addAll(hbxSliders, btnUnlock);
		root.getChildren().add(hbxLockCombo);	// add HBox to root node
		
		// Bottom section (HBox parent node)
		HBox hbxBottom = new HBox(50);
		hbxBottom.setAlignment(Pos.CENTER);
		
		// Add Label for to show results later, set to blank for now
		lblResult.setText("");
		lblResult.setFont(Font.font(MEDIUM_FONT));
		
		// Button for event handler to move on to the next lock's clue
		btnNextLock = new Button("Next Lock");
		btnNextLock.setFont(Font.font(SMALL_FONT));
		btnNextLock.setVisible(false);
		btnNextLock.setOnAction(event -> showNextLock());
		
		// Add Label for bonus money output
		lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());
		
		// Add 2 Labels and Button to HBox parent
		hbxBottom.getChildren().addAll(lblResult, btnNextLock, lblBonusMoney);
		root.getChildren().add(hbxBottom);	// add HBox to root node
		
		// Change the scene graph of the previous stage to this one
		Window miniGamesWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (miniGamesWindow instanceof Stage) {
			Stage myStage = (Stage) miniGamesWindow;
			myStage.setTitle("Royal Escape Room");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	/**
	 * Method to add Images to the GridPane gridVisual for Royal Escape Room.
	 */
	private void showGridVisual() {
		// Set night number to 1 (for lock 5/clue 4)
		int nightNum = 1;
		gridVisual.getChildren().clear();
		
		// Get string visual
		String [][] visual = royalEscapeRoom.getLockVisual(actualLockNum);
		
		// Add visuals in 2row x 5col GridPane
		for (int row = 0; row < visual.length; row++) {
			for (int col = 0; col < visual[row].length; col++) {
				// Parent StackPane to hold image & label
				StackPane stackGrid = new StackPane();
				
				// ImageView to hold the image clue in the grid
				ImageView imgVisual = new ImageView(new Image(getClass().getResource("/images/" + visual[row][col] + ".png").toString()));
				imgVisual.setFitHeight(95);
				imgVisual.setPreserveRatio(true);
				
				// Label to show night numbers if the lock number is 5 (clue #4)
				Label lblGrid = new Label("");
				lblGrid.setFont(Font.font(SMALL_FONT));
				StackPane.setAlignment(lblGrid, Pos.BOTTOM_CENTER);	// stack the label at the bottom of image
				
				// Add night number on Label if lock 5 (clue #4)
				if (actualLockNum == 5) {
					lblGrid.setText("Night " + (nightNum));
					nightNum++;	// Increment night # by 1 to show each subsequent night #
				}
				
				// Add children (ImageView & Label) to parent StackPane
				stackGrid.getChildren().addAll(imgVisual, lblGrid);
				// Add StackPane to root node at the 2d array's indices (but (col,row) instead of (row,col))
				gridVisual.add(stackGrid, col, row);
			}
		}
	}
	
	/**
	 * Method to add lock options to the chcLocks ChoiceBox.
	 */
	private void setLockChoiceBox() {
		// Get locks (their String representations) left to choose from
		String [] strLocks = royalEscapeRoom.getStrLocks();
		
		// Clears ChoiceBox and sets it to the new array of locks left
		chcLocks.getItems().setAll(strLocks);
		
		// Set ChoiceBox's default value to first lock left (and chosenLockNum to that lock's number)
		chcLocks.setValue(strLocks[0]);
		chosenLockNum = Integer.valueOf(String.valueOf(strLocks[0].charAt(LOCK_NUM_INDEX)));
		chcLocks.setDisable(false);	// enable ChoiceBox again to allow passenger to choose an option
	}
	
	/**
	 * Method to set the image of the lock (opened or closed).
	 */
	private void setLockImage() {
		// Set the lock's number to the chosen lock's number
		lblLockNum.setText(String.valueOf(chosenLockNum));
		
		// Set the lock's state to the chosen lock's state ("UNLOCKED" or "LOCKED")
		lblLockState.setText(royalEscapeRoom.getStrLockState(chosenLockNum));
		
		// Set image of lock depending on the chosen lock's state
		if (royalEscapeRoom.getLockState(chosenLockNum)) {
			imgLock.setImage(OPEN_LOCK);
		} else {
			imgLock.setImage(CLOSED_LOCK);
		}
	}
	
	/**
	 * Adds sliders to HBox of sliders, depending on the chosen lock (since number
	 * of sliders in HBox varies depending on lock).
	 */
	private void setSliders() {
		// Instantiate ArrayLists of UI controls for each digit for the chosen lock's combination
		ArrayList<Slider> sldComboNums = new ArrayList<Slider>();
		ArrayList<Label> lblComboNums = new ArrayList<Label>();
		
		// Stores the number of digits in the chosen lock's combination
		lockComboNums = new int[royalEscapeRoom.getLockCombo(chosenLockNum).length()];
		
		// Remove all children from HBox of sliders to add the new ones without adding to what already exists
		hbxSliders.getChildren().clear();
		
		// For each digit in the chosen lock's combination, add a VBox to the HBox hbxSliders
		for (int i = 0; i < lockComboNums.length; i++) {
			// New VBox to hold slider & label
			VBox vbxComboNum = new VBox();
			
			// Slider for each digit in the combo (ranges from 0-9, default value is 0)
			Slider sldComboNum = new Slider(0, 9, 0);
			sldComboNum.setOrientation(Orientation.VERTICAL);	// vertical slider instead of default horizontal
			sldComboNum.setShowTickMarks(true);		// set tick marks visible
			sldComboNum.setShowTickLabels(true);	// set the label the value of each tick mark visible
			sldComboNum.setMajorTickUnit(1);		// set the scale between each major tick mark
			sldComboNum.setBlockIncrement(1);		// number by which slider moves if using arrow key is clicked
			sldComboNum.setPrefHeight(120);
			
			// Get value of slider, set it to the lock combination's digit at index i
			int sliderValue = (int)sldComboNum.getValue();
			lockComboNums[i] = sliderValue;
			
			// Label to output value of each slider
			Label lblComboNum = new Label(String.valueOf(sliderValue));
			lblComboNum.setFont(Font.font(SMALL_FONT));
	
			// Add Slider & Label to their corresponding ArrayLists at index i
			sldComboNums.add(i, sldComboNum);
			lblComboNums.add(i, lblComboNum);
			
			// Add Slider & Label to parent VBox
			vbxComboNum.getChildren().addAll(sldComboNum, lblComboNum);
			
			// Add Listener to each Slider to change its value every time the user moves the knob
			sldComboNum.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					int newValue = (int)sldComboNum.getValue();		// store new value of Slider
					int sliderIndex = sldComboNums.indexOf(sldComboNum);	// get the index of the slider that was changed out of all sliders
					
					// Set the lock combination's digit at index i to the new value
					lockComboNums[sliderIndex] = newValue;
					
					// Output the new value of the slider in Label
					lblComboNums.get(sliderIndex).setText(String.valueOf(newValue));
				}
			});
			
			// Add VBox of slider with its value stored in a Label to parent HBox that holds all Sliders
			hbxSliders.getChildren().add(vbxComboNum);
		}
	}
	
	/**
	 * Outputs a hint for the actual lock and updated bonus money, only if eligible
	 * (if passenger hasn't received a hint yet for that lock). If not eligible, outputs
	 * an error message and doesn't subtract hint fee from bonus money.
	 */
	private void showHint() {
		// Only give hint if passenger hasn't received hint for the actual lock yet
		if (royalEscapeRoom.canHaveHint(actualLockNum)) {
			lblHint.setText(royalEscapeRoom.getLockHint(actualLockNum));	// output hint in RESULT label
			lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());		// show updated bonus money
		} else {	// if passenger already received hint for that lock
			lblErrorMessage.setText(royalEscapeRoom.getLockHint(actualLockNum));	// output error message in ERROR MESSAGE label
		}
	}
	
	/**
	 * Attempts to open lock with passenger's code. Makes sure they don't try to
	 * open a lock that is already open.
	 */
	private void openLock() {
		String code = "";	// set code to blank for now in order to add to it
		
		// Input validation: Make sure passenger doesn't try to unlock the same lock again
		if (royalEscapeRoom.getLockState(chosenLockNum) ) {
			lblResult.setText("You already unlocked lock " + chosenLockNum);	// error message
			return;
		}
		
		// For the last/6th special lock, its input comes from a TextField not from int array from Sliders
		if (chosenLockNum != 6) {	// if lock is not lock 6
			
			// Convert combination in int array to a String
			for (int comboNum : lockComboNums) {
				code += String.valueOf(comboNum);
			}
			
		} else {
			code = txtLockCombo.getText().trim();	// set the code to the String value of input from TextField
		}
		
		// Output result
		lblResult.setText(royalEscapeRoom.attemptUnlock(chosenLockNum, actualLockNum, code));
		
		// Process/adjust output controls depending on chosen lock's state (unlocked/locked)
		if (royalEscapeRoom.getLockState(chosenLockNum)) {	// if unlocked
			btnHint.setDisable(true);	// disable hint Button to prevent invalid input
			
			// Output/processing is different for last/6th special lock
			if (chosenLockNum != 6) {	// if not 6th lock
				btnNextLock.setVisible(true);	// allows user to move on to next lock
				setLockImage();		// changes image of lock depending on its state
				chcLocks.setDisable(true);	// disable ChoiceBox to prevent user from choosing another lock when already opened this one
			} else {	// if 6th lock
				imgLock.setImage(OPEN_LOCK);	// change image of lock to open lock
				btnEndGame.setVisible(true);	// allow user to finish Royal Escape Room mini game
				lblBonusMoney.setText("Your final bonus money is $" + royalEscapeRoom.getBonusMoney() + ".");	// output final bonus money
				
				// Update remaining money & output to passenger
				passenger.updateTotalMoney(royalEscapeRoom.getBonusMoney());
				lblRemainingMoney.setText(passenger.showMoneyLeft());
				lblRemainingMoney.setVisible(true);
			}
		} else {
			lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());	// show bonus money after fee
		}
	}
	
	/**
	 * Shows the next lock's clue.
	 */
	private void showNextLock() {
		// Adjust UI elements from previous opened lock
		lblErrorMessage.setText("");	// set error message to blank
		lblHint.setText("");			// set hint output to blank
		lblResult.setText("");			// set result output to blank
		
		// If the actual lock is lock 4 (last clue #5), then don't show next lock. Instead, show last lock.
		if (actualLockNum != 4) {
			lockIndex++;	// increment lock index
			actualLockNum = EscapeRoom.LOCK_NUMS[lockIndex];	// Set the actual lock number to the lock in the GAME's order of locks
			
			// Show the next lock's clue
			lblClue.setText("Clue #" + (lockIndex + 1) + ": " + royalEscapeRoom.getLockClue(actualLockNum));
		
			// Show the next lock's visual aid (only if it needs one)
			if (royalEscapeRoom.needsVisual(actualLockNum)) {
				gridVisual.setVisible(true);
				showGridVisual();
			} else {
				gridVisual.setVisible(false);	// don't show visual aid
			}
			
			// Adjust Buttons from previous opened lock
			btnHint.setDisable(false);		// enable hint button again
			btnNextLock.setVisible(false);	// don't show next lock button yet

			// Show the updated lock choices (after removing the previous lock from the list)
			setLockChoiceBox();
		} else {	// if last lock (#4, but clue 5) before lock 6
			actualLockNum = 6;	// set lock number in GAME to last lock's
			showLastLock();		// show last lock's scene
		}
	}
	
	/**
	 * Shows the last lock in the Royal Escape Room's screen.
	 */
	private void showLastLock() {
		// Local constant
		final int SCREEN_WIDTH = 700;
		
		// Root node for this JavaFX scene graph
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Set the chosen lock number to 6 too
		chosenLockNum = 6;
		
		// Add Label for title
		lblTitle.setText("Final lock! This is the lock on the lifeboat.");
		root.getChildren().add(lblTitle);
		
		// Section for word input (BorderPane parent node)
		BorderPane borderWordInput = new BorderPane();
		
		// TOP of BorderPane
		// HBox as parent node
		HBox hbxTop = new HBox(GAP);
		hbxTop.setAlignment(Pos.CENTER);
		
		// Add Label to prompt user for a word
		Label lblWordPrompt = new Label("Enter a word:");
		lblWordPrompt.setFont(Font.font(MEDIUM_FONT));
		
		// Add TextField for user's word input
		txtWord = new TextField();
		
		// Add Button for event handler to output consonant pyramid & validate user input (word in TextField)
		btnEnterWord = new Button("Enter");
		btnEnterWord.setFont(Font.font(MEDIUM_FONT));
		btnEnterWord.setOnAction(event -> enterWord());
		
		// Add children nodes (Label, TextField and Button) to parent HBox
		hbxTop.getChildren().addAll(lblWordPrompt, txtWord, btnEnterWord);
		BorderPane.setAlignment(hbxTop, Pos.TOP_CENTER);
		borderWordInput.setTop(hbxTop);
		
		// RIGHT side of BorderPane
		// Add Label for consonant word pyramid output
		lblWordPyramid = new Label("");
		lblWordPyramid.setAlignment(Pos.CENTER_LEFT);
		lblWordPyramid.setFont(Font.font(MEDIUM_FONT));
		borderWordInput.setRight(lblWordPyramid);
		
		// CENTER of BorderPane
		// StackPane as parent node (to hold lock's image on top of lifeboat's image)
		StackPane stackCenter = new StackPane();
		stackCenter.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// ImageView for image of lifeboat
		ImageView imgLifeBoat = new ImageView(new Image(getClass().getResource("/images/lifeBoat2.png").toString()));
		imgLifeBoat.setFitHeight(150);
		imgLifeBoat.setPreserveRatio(true);
		
		// ImageView to show lifeboat's lock's state
		imgLock.setImage(CLOSED_LOCK);
		imgLock.setFitHeight(90);
		
		// Add two ImageViews to StackPane
		stackCenter.getChildren().addAll(imgLifeBoat, imgLock);
		borderWordInput.setCenter(stackCenter);
		BorderPane.setAlignment(stackCenter, Pos.CENTER);
		
		// BOTTOM of BorderPane
		// VBox as parent node
		VBox vbxBottom = new VBox(GAP);
		vbxBottom.setAlignment(Pos.CENTER);
		
		// Label for last lock's clue
		lblClue.setText("Clue: " + royalEscapeRoom.getLockClue(chosenLockNum));
		
		// Button for event handler to show hint
		Button btnHint = new Button("Get Hint");
		btnHint.setFont(Font.font(SMALL_FONT));
		btnHint.setOnAction(event -> showHint());
		
		// Add children nodes (3 Labels and Button) to VBox parent
		vbxBottom.getChildren().addAll(lblClue, btnHint, lblHint, lblErrorMessage);
		borderWordInput.setBottom(vbxBottom);
		root.getChildren().add(borderWordInput);
		
		// Section for lock combo (HBox parent node)
		HBox hbxLockCombo = new HBox(GAP);
		hbxLockCombo.setAlignment(Pos.CENTER);
		
		// Label for lock combo prompt
		Label lblComboPrompt = new Label("Enter lock combination:");
		lblComboPrompt.setFont(Font.font(MEDIUM_FONT));
		
		// TextField for lock combo input
		txtLockCombo = new TextField();
		txtLockCombo.setDisable(true);	// disable lock combo input TextField for now because need word input first
		
		// Add children (Label & TextField for lock combo) to HBox parent node
		hbxLockCombo.getChildren().addAll(lblComboPrompt, txtLockCombo);
		root.getChildren().add(hbxLockCombo);	// add HBox to root node
		
		// Section for unlocking/output (Hbox parent node)
		HBox hbxUnlocking = new HBox(GAP);
		hbxUnlocking.setAlignment(Pos.CENTER);
		
		// Button for event handler to try to open lock (don't show yet because need word input for pyramid first)
		btnUnlock.setVisible(false);
		
		// Label for result output
		lblResult.setText("");
		
		// Add children (Button & 2 Labels) to HBox parent node
		hbxUnlocking.getChildren().addAll(btnUnlock, lblResult, lblBonusMoney);
		root.getChildren().add(hbxUnlocking);	// add HBox to root node
		
		// UI Controls (Label & Button for event handler to end game) after passenger unlocks last lock
		lblRemainingMoney.setVisible(false);
		btnEndGame.setVisible(false);	// don't show yet because haven't received word or lock combination input yet
		root.getChildren().addAll(lblRemainingMoney, btnEndGame);	// also add remaining money label to root
		
		// Change the scene graph of the previous stage to this one
		Window previousLocksWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (previousLocksWindow instanceof Stage) {
			Stage myStage = (Stage) previousLocksWindow;
			myStage.setTitle("Royal Escape Room: Final Lock");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	/**
	 * Shows word pyramid if word entered by user is valid, otherwise, shows error
	 * message and doesn't output pyramid - event handler method for "Enter" Button.
	 */
	private void enterWord() {
		String word = txtWord.getText().trim();
		
		// Input validation
		if (word.isEmpty()) {	// validate that word isn't blank
			lblErrorMessage.setText("Invalid input (cannot be blank).");
			return;
		} else if (!royalEscapeRoom.validateWord(word)) {	// validate that word only contains letters
			lblErrorMessage.setText("Invalid input (must be a word).");
			return;
		}
		
		// Set error message Label to blank just in case previously received error message
		lblErrorMessage.setText("");
		
		// Output consonant pyramid
		lblWordPyramid.setText(royalEscapeRoom.getConsonantPyramid(word));
		
		// Disable word TextField & "Enter" Button to make sure user doesn't change their word & consonant pyramid
		txtWord.setDisable(true);
		btnEnterWord.setDisable(true);
		
		// Allow user to enter their combination attempt now that they entered their word
		txtLockCombo.setDisable(false);
		btnUnlock.setVisible(true);
	}
	
	/**
	 * Method to show the Word Unscramble Game screen.
	 */
	private void showWordUnscrambleScreen() {
		// Local constants
		final int SCREEN_WIDTH = 750, THIS_SCREEN_HEIGHT = 800;
		final Image imgBLANK = new Image(CruiseStoryGame.class.getResource("/images/white.png").toString());
		
		// Root node for this JavaFX scene graph
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// TOP section of BorderPane (VBox parent node)
		VBox vbxTop = new VBox(GAP);
		vbxTop.setAlignment(Pos.CENTER);
		
		// Label for title
		lblTitle.setText(WordUnscramble.NAME);
		
		// Label for instructions
		lblInstructions.setText(WordUnscramble.showInstructions());
		
		// Label for letters to use to create words
		Label lblLetters = new Label("Letters: " + WordUnscramble.WORD);
		lblLetters.setFont(Font.font(MEDIUM_FONT));
		lblLetters.setTextFill(Color.MEDIUMORCHID);
		
		// Add children (3 Labels) to parent VBox
		vbxTop.getChildren().addAll(lblTitle, lblInstructions, lblLetters);
		BorderPane.setAlignment(vbxTop, Pos.TOP_CENTER);
		root.setTop(vbxTop);
		
		// LEFT section of BorderPane (VBox parent node)
		VBox vbxLeft = new VBox(GAP);
		
		// Label to show the bonus points system for this game
		Label lblBonusPtsSystem = new Label(WordUnscramble.showPointsSystem());
		lblBonusPtsSystem.setFont(Font.font(MEDIUM_FONT));
		
		// Label to show the number of possible words
		Label lblNumOfPossibleWords = new Label("Number of possible words: " + NUM_OF_POSSIBLE_WORDS);
		lblNumOfPossibleWords.setFont(Font.font(MEDIUM_FONT));
		
		// Reset bonus money label to blank (only updates when user finishes entering words)
		lblBonusMoney.setText("");
		
		// Add children (4 Labels) to VBox parent
		vbxLeft.getChildren().addAll(lblBonusPtsSystem, lblNumOfPossibleWords, lblBonusMoney, lblRemainingMoney);
		BorderPane.setAlignment(vbxLeft, Pos.CENTER);
		root.setLeft(vbxLeft);
		
		// RIGHT section of BorderPane
		VBox vbxRight = new VBox(GAP);
		
		// Add Label to output all possible words after user finishes entering their words
		lblPossibleWords = new Label("");
		lblPossibleWords.setFont(Font.font(MEDIUM_FONT));
		
		// Add Button for event handler to check words entered by user
		btnDone = new Button("Done");
		btnDone.setFont(Font.font(SMALL_FONT));
		btnDone.setOnAction(event -> checkWords());
		
		// Add children (Label & Button) to VBox root
		vbxRight.getChildren().addAll(lblPossibleWords, btnDone);
		BorderPane.setAlignment(vbxRight, Pos.CENTER_LEFT);
		root.setRight(vbxRight);
		
		// CENTER section of BorderPane (GridPane parent node)
		GridPane gridCenter = new GridPane();
		gridCenter.setHgap(GAP);	// sets gaps between columns
		gridCenter.setVgap(GAP);	// sets gaps between rows
		gridCenter.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// Add an array of TextFields and ImageViews as UI controls for each word entered by user
		txtWords = new TextField[NUM_OF_POSSIBLE_WORDS];
		imgOutputs = new ImageView[NUM_OF_POSSIBLE_WORDS];
		
		// For each word entered by user, add a TextField and a blank image (that will later hold result)
		for (int j = 0; j < NUM_OF_POSSIBLE_WORDS; j++) {
			txtWords[j] = new TextField();
			
			imgOutputs[j] = new ImageView(imgBLANK);
			imgOutputs[j].setFitHeight(35);
			imgOutputs[j].setPreserveRatio(true);
			
			// Split TextField/ImageView sets into two columns
			if (j < NUM_OF_POSSIBLE_WORDS / 2) {	// TextFields/ImageViews 1-11
				gridCenter.add(txtWords[j], 0, j);
				gridCenter.add(imgOutputs[j], 1, j);
			} else {	// TextFields/ImageViews 12-22
				gridCenter.add(txtWords[j], 2, j - (NUM_OF_POSSIBLE_WORDS / 2));
				gridCenter.add(imgOutputs[j], 3, j - (NUM_OF_POSSIBLE_WORDS / 2));
			}
		}

		BorderPane.setAlignment(gridCenter, Pos.CENTER);
		root.setCenter(gridCenter);
		
		// BOTTOM section of BorderPane (Button for event handler to end the game)
		btnEndGame.setDisable(true);
		root.setBottom(btnEndGame);
		BorderPane.setAlignment(btnEndGame, Pos.CENTER);
		
		// Change the scene graph of the previous stage to this one
		Window miniGamesWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, THIS_SCREEN_HEIGHT);
		
		if (miniGamesWindow instanceof Stage) {
			Stage myStage = (Stage) miniGamesWindow;
			myStage.setTitle("Word Unscramble");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	/**
	 * Method to check the 22 TextFields that contains user's words for the Word
	 * Unscramble mini game - event handler for the "Done" Button.
	 */
	private void checkWords() {
		// Local constants for Images
		final Image BLANK = new Image(getClass().getResource("/images/blankAnswer.png").toString());
		final Image INCORRECT = new Image(getClass().getResource("/images/incorrect.png").toString());
		final Image CORRECT = new Image(getClass().getResource("/images/correct.png").toString());
		
		String[] wordsToCheck = new String[NUM_OF_POSSIBLE_WORDS];
		
		// Populate wordsToCheck array with corresponding input from TextFields
		for (int i = 0; i < NUM_OF_POSSIBLE_WORDS; i++) {
			wordsToCheck[i] = txtWords[i].getText().trim();
		}
		
		// Get array of corresponding results for each word
		String[] results = wordUnscrambleGame.checkWords(wordsToCheck);
		
		// Add resulting image for each entry by user
		for (int j = 0; j < results.length; j++) {
			if (results[j].equals(WordUnscramble.BLANK)) {
				imgOutputs[j].setImage(BLANK);
			} else if (results[j].equals(WordUnscramble.CORRECT)) {
				imgOutputs[j].setImage(CORRECT);
			} else {
				imgOutputs[j].setImage(INCORRECT);
			}
		}
		
		// Output all possible words that could've been created from the given word
		String strPossibleWords = "Possible Words:\n";
		String[] possibleWords = WordUnscramble.POSSIBLE_WORDS;
		
		for (int i = 0; i < possibleWords.length; i++) {
			strPossibleWords += possibleWords[i];
			if (i % 2 == 0) {	// even
				strPossibleWords += "\t";
			} else if (i != possibleWords.length - 1) {	// odd
				strPossibleWords += "\n";
			}
		}
		
		lblPossibleWords.setText(strPossibleWords);
		
		// Make sure user doesn't re-enter words (so disable "Done" Button)
		btnDone.setDisable(true);
		
		// Enable "End Game" now that user finished entering words
		btnEndGame.setDisable(false);
		
		// Output bonus money resulting from this game
		lblBonusMoney.setText(wordUnscrambleGame.showBonusAmount());
		
		// Output remaining money for the overall game
		passenger.updateTotalMoney(wordUnscrambleGame.getBonusMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
	}
	
	/**
	 * Method to show the Activities Scenario screen - event handler for
	 * the "End Game" Button.
	 */
	private void showActivitiesScreen() {
		// Local constants
		final int SCREEN_WIDTH = 1250;
		final int NUM_OF_ACTIVITIES = ActivitiesScenario.ACTIVITIES.length;
		final int HALF = NUM_OF_ACTIVITIES / 2;	// helps to separate the controls for each activity into 2 columns
		
		// Local variables
		Label lblActivity;
		int rowIndex;
		ImageView[] imgActivities = new ImageView[NUM_OF_ACTIVITIES];
		
		// Root node for this JavaFX scene graph (VBox)
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Labels for title & instructions, add to root node
		lblTitle.setText("Day " + dayNumber + ": Activities");
		lblInstructions.setText(ActivitiesScenario.showInstructions());
		root.getChildren().addAll(lblTitle, lblInstructions);
		
		// Section for user input (GridPane parent node)
		GridPane gridInput = new GridPane();
		gridInput.setHgap(GAP);	// sets gaps between columns
		gridInput.setVgap(GAP);	// sets gaps between rows
		gridInput.setPadding(new Insets(GAP, GAP, GAP, GAP));
		gridInput.setAlignment(Pos.CENTER);
		
		// Array TextFields for input for number of times to perform each activity
		txtNumsPerActivities = new TextField[NUM_OF_ACTIVITIES];
		
		// For each activity, add an image of it, a label to show which activity and TextField for input of number of times to perform it
		for (int i = 0; i < NUM_OF_ACTIVITIES; i++) {
			// Access image using index i in its file name
			imgActivities[i] = new ImageView(new Image(getClass().getResource("/images/activity" + i + ".png").toString()));
			imgActivities[i].setFitHeight(70);
			imgActivities[i].setPreserveRatio(true);
			
			// Use index i as cost of each activity ($0-$13)
			lblActivity = new Label(ActivitiesScenario.ACTIVITIES[i] + " ($" + i + "):");
			lblActivity.setFont(Font.font(MEDIUM_FONT));
			
			txtNumsPerActivities[i] = new TextField();
			
			if (i < HALF) {	// TextFields/ImageViews 1-7
				rowIndex = i * 2;
				gridInput.add(imgActivities[i], 0, rowIndex, 2, 2);
				gridInput.add(lblActivity, 2, rowIndex, 2, 1);
				gridInput.add(txtNumsPerActivities[i], 4, rowIndex);
			} else {	// TextFields/ImageViews 8-13
				rowIndex = (i - HALF) * 2;
				gridInput.add(imgActivities[i], 6, rowIndex, 2, 2);
				gridInput.add(lblActivity, 8, rowIndex, 2, 1);
				gridInput.add(txtNumsPerActivities[i], 10, rowIndex);
			}
		}
		
		root.getChildren().add(gridInput);
		
		// Section for results (HBox parent node)
		HBox hbxResults = new HBox(GAP);
		hbxResults.setAlignment(Pos.CENTER);
		
		// Button for event handler to calculate the cost of all activities 
		btnDoneActivities = new Button("Done");
		btnDoneActivities.setFont(Font.font(SMALL_FONT));
		btnDoneActivities.setOnAction(event -> calculateActivitiesCost());
		
		// Label to show cost of activities, set to blank for now
		lblResult.setText("");
		lblResult.setFont(Font.font(MEDIUM_FONT));
		
		// Add children (Button & Label) to HBox parent node
		hbxResults.getChildren().addAll(btnDoneActivities, lblResult);
		root.getChildren().add(hbxResults);
		
		// Add Label in case of error, set to blank for now
		lblErrorMessage.setText("");
		root.getChildren().add(lblErrorMessage);
		
		// HBox parent node to hold Button & Label (remaining money)
		HBox hbxBottom = new HBox(GAP);
		hbxBottom.setAlignment(Pos.CENTER);
		
		// Button for event handler to end Activities Scenario & show Mini Games Screen or End Screen (if last day)
		btnStartCruise.setText("Next");
		btnStartCruise.setDisable(true);
		
		// Add children (Button & Label) to HBox parent
		hbxBottom.getChildren().addAll(btnStartCruise, lblRemainingMoney);
		root.getChildren().addAll(hbxBottom);	// add HBox to root node
		
		// Change the scene graph of the previous stage to this one
		Window miniGameWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (miniGameWindow instanceof Stage) {
			Stage myStage = (Stage) miniGameWindow;
			myStage.setTitle("Activities Scenario");
			myStage.setScene(mainScene);
			myStage.show();
			dayNumber++;	// increment day number by one since the Activities Scenario is the last event of each day
		}
	}
	
	/**
	 * Method to show the calculate the cost of all activities - event handler for
	 * the "Done" Button in the Activities Scenario screen.
	 */
	private void calculateActivitiesCost() {
		int[] numOfEachActivity = new int[ActivitiesScenario.ACTIVITIES.length];
		
		// Loop through each number per activity entered by user
		for (int i = 0; i < numOfEachActivity.length; i++) {
			String textInput = txtNumsPerActivities[i].getText().trim();	// convert TextField's input to String
			
			// Input validation: Make sure input is not empty
			if (textInput.isEmpty()) {
				lblErrorMessage.setText("Invalid entry (empty box).");
				return;
			}
			
			// Input validation: Make sure TextField only contained numbers
			try {
				numOfEachActivity[i] = Integer.parseInt(textInput);
			} catch (NumberFormatException e) {
				lblErrorMessage.setText("Invalid entry (Please enter a number)");	// error message
				return;
			}
		}
		
		// Instantiate ActivitiesScenario with the number of each activity input
		ActivitiesScenario activitiesScenario = new ActivitiesScenario(numOfEachActivity);
		
		// Calculate the total cost of the activities
		activitiesScenario.calculateCost();
		
		// Set error message Label to blank in case an error message was shown before for invalid input
		lblErrorMessage.setText("");
		
		// Show the cost of all activities
		lblResult.setText(activitiesScenario.showChangeInMoney());
		
		// Update the passenger's overall remaining money in the Cruise Story Game
		passenger.updateTotalMoney(activitiesScenario.getChangeInMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
		
		// Disable the "Done" button so user doesn't enter their activities again and subtract additional money from their total money
		btnDoneActivities.setDisable(true);
		
		// Enable the "Next" button since Activities Scenario is done
		btnStartCruise.setDisable(false);
	}
	
	/**
	 * Shows the last screen (scene) of the game; this screen shows the results of the
	 * overall game.
	 */
	private void showEndScreen() {
		// Local constants
		final int SCREEN_WIDTH = 1160, END_SCREEN_HEIGHT = 750;
		final int IMG_HEIGHT = 200;
		
		// Root node for this JavaFX scene graph (VBox)
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// HBox to hold 3 ImageViews
		HBox hbxHeader = new HBox(GAP);
		hbxHeader.setAlignment(Pos.CENTER);
		
		// ImageView holding Image of the ship 
		ImageView imgOasis = new ImageView(new Image(getClass().getResource("/images/oasisOTS.png").toString()));
		imgOasis.setFitHeight(IMG_HEIGHT);
		imgOasis.setPreserveRatio(true);
		
		// ImageView holding Image of Royal Caribbean's logo 
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo2.png").toString()));
		imgLogo.setFitHeight(IMG_HEIGHT);
		imgLogo.setPreserveRatio(true);
		
		// ImageView holding result (if the passenger won or lost) 
		ImageView imgResult = new ImageView();
		imgResult.setFitHeight(IMG_HEIGHT);
		imgResult.setPreserveRatio(true);
		
		// Set the result Image (depends on whether or not the passenger won)
		if (passenger.getWinStatus()) {	// if passenger won ($+ left)
			imgResult.setImage(new Image(getClass().getResource("/images/winner.png").toString()));
		} else {	// if passenger lost ($0 or $- left)
			imgResult.setImage(new Image(getClass().getResource("/images/loser.png").toString()));
		}
		
		// Add children (3 Images) to HBox parent node
		hbxHeader.getChildren().addAll(imgOasis, imgLogo, imgResult);
		root.getChildren().add(hbxHeader);	// add HBox to root node
		
		// Add Label to output the end of game stats
		lblResult.setText(passenger.showOverallResult());
		lblResult.setFont(Font.font(XL_FONT));
		root.getChildren().addAll(lblResult);
		
		// Section for images at the bottom (HBox parent node)
		HBox hbxImages = new HBox(GAP);
		hbxImages.setAlignment(Pos.CENTER);
		
		// ImageView holding Image of Oasis OTS' pool deck
		ImageView imgPoolDeck = new ImageView(new Image(getClass().getResource("/images/poolDeck.png").toString()));
		imgPoolDeck.setFitHeight(IMG_HEIGHT);
		imgPoolDeck.setPreserveRatio(true);
		
		// ImageView holding Image of Oasis OTS' boardwalk
		ImageView imgBoardwalk = new ImageView(new Image(getClass().getResource("/images/boardwalkView.png").toString()));
		imgBoardwalk.setFitHeight(IMG_HEIGHT);
		imgBoardwalk.setPreserveRatio(true);
		
		// ImageView holding Image of Oasis OTS' mini golf
		ImageView imgMiniGolfCourse = new ImageView(new Image(getClass().getResource("/images/golf.png").toString()));
		imgMiniGolfCourse.setFitHeight(IMG_HEIGHT);
		imgMiniGolfCourse.setPreserveRatio(true);
		
		// ImageView holding Image of sailaway on Oasis OTS in NYC
		ImageView imgSailaway = new ImageView(new Image(getClass().getResource("/images/nyc.png").toString()));
		imgSailaway.setFitHeight(IMG_HEIGHT);
		imgSailaway.setPreserveRatio(true);
		
		// ImageView holding Image of Oasis OTS' ultimate abyss 10-story dry slide
		ImageView imgUltimateAbyss = new ImageView(new Image(getClass().getResource("/images/ultimateAbyss.png").toString()));
		imgUltimateAbyss.setFitHeight(IMG_HEIGHT);
		imgUltimateAbyss.setPreserveRatio(true);
		
		// ImageView holding Image of Oasis & Enchantment OTS at Royal Caribbean's private Island, Perfect Day at CocoCay
		ImageView imgShips = new ImageView(new Image(getClass().getResource("/images/oasisAndEnchantment.png").toString()));
		imgShips.setFitHeight(IMG_HEIGHT);
		imgShips.setPreserveRatio(true);
		
		// Add children (6 ImageViews) to HBox parent node
		hbxImages.getChildren().addAll(imgPoolDeck, imgBoardwalk, imgMiniGolfCourse, imgSailaway, imgUltimateAbyss, imgShips);
		root.getChildren().add(hbxImages);	// add HBox to root node
		
		// Change the scene graph of the previous stage to this one
		Window previousWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, END_SCREEN_HEIGHT);
		
		if (previousWindow instanceof Stage) {
			Stage myStage = (Stage) previousWindow;
			myStage.setTitle("End Screen");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}