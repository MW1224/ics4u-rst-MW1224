package rst;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
	private static final int GAP = 10, SMALL_GAP = 3;	// gaps for layout
	private static final int SCREEN_HEIGHT = 900;
	private static final int SMALL_FONT = 15, MEDIUM_FONT = 17, LARGE_FONT = 20, XL_FONT = 26;
	private static final Image CLOSED_LOCK = new Image(CruiseStoryGame.class.getResource("/images/closedLock.png").toString());
	private static final Image OPEN_LOCK = new Image(CruiseStoryGame.class.getResource("/images/openLock.png").toString());
	private static final int LOCK_NUM_INDEX = 5;
	private static final Image imgBLANK = new Image(CruiseStoryGame.class.getResource("/images/white.png").toString());
	private static final int NUM_OF_POSSIBLE_WORDS = WordUnscramble.NUM_OF_POSSIBLE_WORDS;
	
	// Input/output UI controls & global variables
	private Passenger passenger;
	private Label lblTitle, lblInstructions, lblResult, lblRemainingMoney, lblErrorMessage, lblBonusMoney;
	private boolean eventDone;
	private int dayNumber = 1;
	private Button btnEndGame, btnStartCruise;
	// For Cruise Story Game main screen
	private Label lblHighScore;
	private TextField txtFirstName, txtLastName;
	private ChoiceBox<String> chcLoyaltyStatus;
	private Scene mainScene;
	// For Cruise Route Random Selection screen
	private RouteCard[][] routeGrid;
	private Label lblRoute;
	// For Packing Scenario screen
	private PackingScenario packingScenario;
	private int[] itemQuantities;
	private Label lblWeight;
	// For Royal Escape Room Mini Game screen
	private EscapeRoom royalEscapeRoom;
	private int actualLockNum, lockIndex = 0, chosenLockNum;
	private ImageView imgLock;
	private Label lblClue, lblLockNum, lblLockState, lblHint;
	private int[] lockComboNums;
	private HBox hbxSliders;
	private Button btnHint, btnNextLock;
	private ChoiceBox<String> chcLocks;
	private GridPane gridVisual;
	private Label lblWordPyramid;
	private TextField txtWord, txtLockCombo;
	private Button btnUnlock;
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
		final int TILE_HEIGHT = 185, TILE_WIDTH = 200;
		final int IMG_HEIGHT = 175;
		final int SCREEN_WIDTH = 1100;
		
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
		// Label for passenger's loyalty status
		Label lblLoyaltyStatus = new Label("Crown & Anchor Society status:");
		lblLoyaltyStatus.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblLoyaltyStatus, 1, 2, 2, 1);	// pos (1,3), colspan = 2, rowspan = 1
		// ChoiceBox for loyalty status options
		chcLoyaltyStatus = new ChoiceBox<String>();
		chcLoyaltyStatus.getItems().addAll(Passenger.LOYALTY_STATUSES);
		gridPassengerInfo.add(chcLoyaltyStatus, 3, 2, 2, 1);	// pos (3,3), colspan = 2, rowspan = 1
		// Label to display overall high score between all games played before
		lblHighScore = new Label(passenger.showHighScore());
		lblHighScore.setFont(Font.font(MEDIUM_FONT));
		gridPassengerInfo.add(lblHighScore, 1, 3, 2, 1);	// pos (1,4), colspan = 2, rowspan = 1
		// Button for event handler to reset high score out of all games played to $0
		Button btnResetHighScore = new Button("Reset high score counter");
		btnResetHighScore.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnResetHighScore, 4, 3, 2, 1);	// pos (4,4), colspan = 2, rowspan = 1
		btnResetHighScore.setOnAction(event -> resetHighScore());
		// Button for event handler to create passenger with their information
		Button btnFinish = new Button("Done entering info");
		btnFinish.setFont(Font.font(SMALL_FONT));
		gridPassengerInfo.add(btnFinish, 8, 3, 2, 1);	// pos (8,4), colspan = 1, rowspan = 1
		btnFinish.setOnAction(event -> addPassengerInfo());
		// Add this GridPane to the VBox root
		gridPassengerInfo.setAlignment(Pos.CENTER);
		root.getChildren().add(gridPassengerInfo);
		
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
	
	private void resetHighScore() {
		passenger.resetHighScore();
		lblHighScore.setText(passenger.showHighScore());
	}
	
	private void addPassengerInfo() {
		String firstName, lastName, loyaltyStatus;
		firstName = txtFirstName.getText().trim();
		lastName = txtLastName.getText().trim();
		loyaltyStatus = chcLoyaltyStatus.getValue();
		
		if (firstName.isEmpty() || lastName.isEmpty() || loyaltyStatus == null) {
			lblErrorMessage.setText("Please enter information in all fields! (no empty boxes)");
			return;
		}
		
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setLoyaltyStatus(loyaltyStatus);
		
		showCruiseRouteScreen();
	}
	
	private void showCruiseRouteScreen() {
		// Local constants
		final int GRID_DIMENSION = 4;
		final int TILE_DIMENSION = RouteCard.CARD_DIMENSION + 10;
		final int SCREEN_WIDTH = 790;
		
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
		btnNext.setOnAction(event -> showPackingScenarioScreen());
		
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
			passenger.updateTotalMoney(temp.getCost());
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
	
	private void showPackingScenarioScreen() {
		// Local constants
		final int SCREEN_WIDTH = 1200;
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
		itemQuantities = new int[PackingScenario.NUM_OF_ITEMS];
		
		for (int i = 0; i < itemQuantities.length; i++) {
			HBox hbxRow = new HBox(GAP);
			SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
			valueFactory.setValue(0);
			
			Label lblActivity = new Label(ITEMS[i] + "  ");
			lblActivity.setFont(Font.font(SMALL_FONT));
			
			Spinner<Integer> spnItemQuantity = new Spinner<Integer>();
			spnItemQuantity.setValueFactory(valueFactory);
			itemQuantities[i] = spnItemQuantity.getValue();
			spnItemQuantities.add(i, spnItemQuantity);
			
			spnItemQuantity.valueProperty().addListener(new ChangeListener<Integer>() {
				public void changed(ObservableValue<? extends Integer> ov, Integer old_val, Integer new_val) {
					itemQuantities[spnItemQuantities.indexOf(spnItemQuantity)] = spnItemQuantity.getValue();
				}
			});
			
			hbxRow.getChildren().addAll(lblActivity, spnItemQuantity);
			
			if (i < FIRST_ROWS_ITEMS) {
				vbxCol1.getChildren().add(hbxRow);
			} else if (i >= FIRST_ROWS_ITEMS && i < FIRST_ROWS_ITEMS * 2) {
				vbxCol2.getChildren().add(hbxRow);
			} else {
				vbxCol3.getChildren().add(hbxRow);
			}	
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
		lblResult = new Label("");
		
		// Label to output any possible error messages later
		lblErrorMessage.setText("");
		
		// Button to move to next scene
		btnStartCruise = new Button("Start Cruise Trip!");
		btnStartCruise.setFont(Font.font(SMALL_FONT));
		btnStartCruise.setOnAction(event -> showMiniGamesScreen());
		
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
	
	private void showMiniGamesScreen() {
		// Local constants
		final int SCREEN_WIDTH = 400, GAMES_SCREEN_HEIGHT = 400;
		
		if (dayNumber == 3) {
			showEndScreen();
		} else {
			if (!eventDone) {
				lblErrorMessage.setText("Please finish activity before moving on.");
				return;
			}
			
			ArrayList<String> miniGamesLeft = passenger.getMiniGamesLeft();
			
			// Root node for this JavaFX scene graph
			VBox root = new VBox(GAP);
			root.setPadding(new Insets(GAP, GAP, GAP, GAP));
			root.setAlignment(Pos.CENTER);
			
			// Label for title
			lblTitle.setText("Day " + dayNumber);
			// Label for instructions
			lblInstructions.setText("Select a mini game:");
			lblInstructions.setFont(Font.font(LARGE_FONT));
			
			root.getChildren().addAll(lblTitle, lblInstructions);
			
			// Toggle group with RadioButtons for mini game(s) options left to play - user must select one
			ToggleGroup tgMiniGames = new ToggleGroup();
			
			for (String miniGame : miniGamesLeft) {
				RadioButton radMiniGame = new RadioButton(miniGame);
				radMiniGame.setFont(Font.font(LARGE_FONT));
				radMiniGame.setToggleGroup(tgMiniGames);
				root.getChildren().add(radMiniGame);
				lblBonusMoney = new Label("");
				lblBonusMoney.setFont(Font.font(MEDIUM_FONT));
				
				btnEndGame = new Button("End Game");
				btnEndGame.setFont(Font.font(MEDIUM_FONT));
				btnEndGame.setOnAction(event -> showActivitiesScreen());
				radMiniGame.setOnAction(event -> playMiniGame(event));
			}
			
			Window routeWindow = mainScene.getWindow();
			mainScene = new Scene(root, SCREEN_WIDTH, GAMES_SCREEN_HEIGHT);
			
			if (routeWindow instanceof Stage) {
				Stage myStage = (Stage) routeWindow;
				myStage.setTitle("Day " + dayNumber + " - Mini Games");
				myStage.setScene(mainScene);
				myStage.show();
			}
		}
	}
	
	private void playMiniGame(ActionEvent event) {
		RadioButton temp = (RadioButton) event.getSource();
		
		if (temp.getText().equals(EscapeRoom.NAME)) {
			royalEscapeRoom = new EscapeRoom();
			passenger.removeMiniGame(temp.getText());
			showEscapeRoomScreen();
		} else if (temp.getText().equals(WordUnscramble.NAME)) {
			wordUnscrambleGame = new WordUnscramble();
			passenger.removeMiniGame(temp.getText());
			showWordUnscrambleScreen();
		}
	}
	
	private void showEscapeRoomScreen() {
		
		// Local constant
		final int SCREEN_WIDTH = 1250; 
		
		// Local object and variables
		actualLockNum = EscapeRoom.LOCK_NUMS[lockIndex];
		
		VBox root = new VBox(GAP);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// Label for title
		lblTitle.setText(EscapeRoom.NAME);
		
		// Label for instructions
		lblInstructions.setText(EscapeRoom.showInstructions());
		lblInstructions.setFont(Font.font(MEDIUM_FONT));
	
		root.getChildren().addAll(lblTitle, lblInstructions);
		
		// Label for clue
		lblClue = new Label("Clue #" + (lockIndex + 1) + ": " + royalEscapeRoom.getLockClue(actualLockNum));
		lblClue.setFont(Font.font(MEDIUM_FONT));
		lblClue.setWrapText(true);
		
		// Show visual
		gridVisual = new GridPane();
		gridVisual.setHgap(GAP);	// sets gaps between columns
		gridVisual.setVgap(GAP);	// sets gaps between rows
		gridVisual.setAlignment(Pos.CENTER);
		showGridVisual();
		
		root.getChildren().addAll(lblClue, gridVisual);
		
		// Hint section
		// Button
		HBox hbxHint = new HBox(GAP);
		hbxHint.setAlignment(Pos.CENTER);
		btnHint = new Button("Get Hint");
		btnHint.setFont(Font.font(SMALL_FONT));
		btnHint.setOnAction(event -> showHint());
		lblErrorMessage.setText("");
		hbxHint.getChildren().addAll(btnHint, lblErrorMessage);
		// Label
		lblHint = new Label("");
		lblHint.setFont(Font.font(SMALL_FONT));
		lblHint.setWrapText(true);
		root.getChildren().addAll(hbxHint, lblHint);
		
		// Section for user to pick which lock to openImageView imgEscapeRoom = new ImageView(new Image(getClass().getResource("/images/escapeRoom.png").toString()));
		ImageView imgEscapeRoom = new ImageView(new Image(getClass().getResource("/images/escapeRoom.png").toString()));
		imgEscapeRoom.setFitHeight(120);
		imgEscapeRoom.setPreserveRatio(true);
		
		FlowPane flowLockSelection = new FlowPane();
		flowLockSelection.setAlignment(Pos.CENTER);
		flowLockSelection.setRowValignment(VPos.TOP);
		flowLockSelection.setHgap(GAP);
		flowLockSelection.setPrefWidth(SCREEN_WIDTH);
		// Label for instructions
		Label lblLockSelection = new Label("Choose a lock to open:");
		lblLockSelection.setFont(Font.font(MEDIUM_FONT));
		// ChoiceBox for lock choices
		chcLocks = new ChoiceBox<String>();
		setLockChoiceBox();
		
		// StackPane for Lock Image & Text output
		StackPane stackLock = new StackPane();
		stackLock.setAlignment(Pos.CENTER);
		imgLock = new ImageView();
		imgLock.setFitHeight(120);
		imgLock.setPreserveRatio(true);
		imgLock.setPreserveRatio(true);
		VBox vbxLock = new VBox();
		vbxLock.setAlignment(Pos.CENTER);
		lblLockNum = new Label();
		lblLockNum.setFont(Font.font(XL_FONT));
		lblLockNum.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(1), Insets.EMPTY)));
		lblLockState = new Label();
		lblLockState.setFont(Font.font(SMALL_FONT));
		lblLockState.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(1), Insets.EMPTY)));
		setLockImage();
		vbxLock.getChildren().addAll(lblLockNum, lblLockState);
		stackLock.getChildren().addAll(imgLock, vbxLock);
		
		ImageView imgLifeBoat = new ImageView(new Image(getClass().getResource("/images/lifeBoat.png").toString()));
		imgLifeBoat.setFitHeight(120);
		imgLifeBoat.setPreserveRatio(true);
		
		flowLockSelection.getChildren().addAll(imgEscapeRoom, lblLockSelection, chcLocks, stackLock, imgLifeBoat);
		root.getChildren().add(flowLockSelection);
		
		// Section for lock combo input
		HBox hbxLockCombo = new HBox(GAP);
		hbxLockCombo.setPadding(new Insets(GAP, GAP, GAP, GAP));
		hbxLockCombo.setAlignment(Pos.CENTER);
		// Label
		Label lblLockCombo = new Label("Enter lock combination for lock " + chosenLockNum + ":");
		lblLockCombo.setFont(Font.font(MEDIUM_FONT));
		hbxLockCombo.getChildren().add(lblLockCombo);
		// Sliders
		hbxSliders = new HBox(GAP);
		setSliders();
		
		chcLocks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_lock, String new_lock) {
				chosenLockNum = Integer.valueOf(String.valueOf(new_lock.charAt(LOCK_NUM_INDEX)));
				lblLockCombo.setText("Enter lock combination for lock " + chosenLockNum + ":");
				setLockImage();
				setSliders();
			}
		});
		
		hbxLockCombo.getChildren().add(hbxSliders);
		
		// Button for event handler
		btnUnlock = new Button("Unlock");
		btnUnlock.setFont(Font.font(SMALL_FONT));
		btnUnlock.setOnAction(event -> openLock());
		hbxLockCombo.getChildren().addAll(btnUnlock);
		
		root.getChildren().add(hbxLockCombo);
		
		// Section for bonus money & "Next Lock" button
		HBox hbxBottom = new HBox(50);
		hbxBottom.setAlignment(Pos.CENTER);
		
		// Label for results
		lblResult.setText("");
		lblResult.setFont(Font.font(MEDIUM_FONT));
		
		// Button for event handler
		btnNextLock = new Button("Next Lock");
		btnNextLock.setFont(Font.font(SMALL_FONT));
		btnNextLock.setVisible(false);
		btnNextLock.setOnAction(event -> showNextLock());
		
		// Label for bonus money output
		lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());
		
		hbxBottom.getChildren().addAll(lblResult, btnNextLock, lblBonusMoney);
		root.getChildren().add(hbxBottom);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("Royal Escape Room");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	private void showGridVisual() {
		int nightNum = 1;
		gridVisual.getChildren().clear();
		String [][] visual = royalEscapeRoom.getLockVisual(actualLockNum);
		for (int row = 0; row < visual.length; row++) {
			for (int col = 0; col < visual[row].length; col++) {
				StackPane stackGrid = new StackPane();
				ImageView imgVisual = new ImageView(new Image(getClass().getResource("/images/" + visual[row][col] + ".png").toString()));
				imgVisual.setFitHeight(95);
				imgVisual.setPreserveRatio(true);
				Label lblGrid = new Label("");
				lblGrid.setFont(Font.font(SMALL_FONT));
				StackPane.setAlignment(lblGrid, Pos.BOTTOM_CENTER);
				if (actualLockNum == 5) {
					lblGrid.setText("Night " + (nightNum));
					nightNum++;
				}
				stackGrid.getChildren().addAll(imgVisual, lblGrid);
				gridVisual.add(stackGrid, col, row);
			}
		}
	}
	
	private void setLockChoiceBox() {
		/*chcLocks.setDisable(true);*/
		String [] strLocks = royalEscapeRoom.getStrLocks();
		chcLocks.getItems().setAll(strLocks);
		chcLocks.setValue(strLocks[0]);
		chosenLockNum = Integer.valueOf(String.valueOf(strLocks[0].charAt(LOCK_NUM_INDEX)));
		chcLocks.setDisable(false);
	}
	
	private void setLockImage() {
		lblLockNum.setText(String.valueOf(chosenLockNum));
		lblLockState.setText(royalEscapeRoom.getStrLockState(chosenLockNum));
		if (royalEscapeRoom.getLockState(chosenLockNum)) {
			imgLock.setImage(OPEN_LOCK);
		} else {
			imgLock.setImage(CLOSED_LOCK);
		}
	}
	
	private void setSliders() {
		hbxSliders.getChildren().clear();
		
		ArrayList<Slider> sldComboNums = new ArrayList<Slider>();
		ArrayList<Label> lblComboNums = new ArrayList<Label>();
		lockComboNums = new int[royalEscapeRoom.getLockCombo(chosenLockNum).length()];
		
		for (int i = 0; i < lockComboNums.length; i++) {
			// New VBox to hold slider & label
			VBox vbxComboNum = new VBox();
			// Slider for each slot in the combo
			Slider sldComboNum = new Slider(0, 9, 0);
			sldComboNum.setOrientation(Orientation.VERTICAL);
			sldComboNum.setShowTickMarks(true);		// set tick marks visible
			sldComboNum.setShowTickLabels(true);	// set the label the value of each tick mark visible
			sldComboNum.setMajorTickUnit(1);		// set the scale between each major tick mark
			sldComboNum.setBlockIncrement(1);		// number by which slider moves if using arrow key is clicked
			sldComboNum.setPrefHeight(120);
			int sliderValue = (int)sldComboNum.getValue();	// get value of slider
			// Label to output value of each slider
			Label lblComboNum = new Label(String.valueOf(sliderValue));
			lblComboNum.setFont(Font.font(SMALL_FONT));
			
			lockComboNums[i] = sliderValue;
			sldComboNums.add(i, sldComboNum);
			lblComboNums.add(i, lblComboNum);
			vbxComboNum.getChildren().addAll(sldComboNum, lblComboNum);
			
			sldComboNum.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					int newValue = (int)sldComboNum.getValue();
					int sliderIndex = sldComboNums.indexOf(sldComboNum);
					
					lockComboNums[sliderIndex] = newValue;
					lblComboNums.get(sliderIndex).setText(String.valueOf(newValue));
				}
			});
			
			hbxSliders.getChildren().add(vbxComboNum);
		}
	}
	
	private void showHint() {
		if (royalEscapeRoom.canHaveHint(actualLockNum)) {
			lblHint.setText(royalEscapeRoom.getLockHint(actualLockNum));
		} else {
			lblErrorMessage.setText(royalEscapeRoom.getLockHint(actualLockNum));
		}
		lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());
	}
	
	private void openLock() {
		String code = "";
		
		if (royalEscapeRoom.getLockState(chosenLockNum) ) {
			lblResult.setText("You already unlocked lock " + chosenLockNum);
			return;
		}
		
		if (chosenLockNum != 6) {
			for (int comboNum : lockComboNums) {
				code += String.valueOf(comboNum);
			}
		} else {
			code = txtLockCombo.getText().trim();
		}
		
		lblResult.setText(royalEscapeRoom.attemptUnlock(chosenLockNum, actualLockNum, code));
		
		if (royalEscapeRoom.getLockState(chosenLockNum)) {
			btnHint.setDisable(true);
			if (chosenLockNum != 6) {
				btnNextLock.setVisible(true);
				setLockImage();
				chcLocks.setDisable(true);
			} else {
				imgLock.setImage(OPEN_LOCK);
				btnEndGame.setVisible(true);
				lblBonusMoney.setText("Your final bonus money is $" + royalEscapeRoom.getBonusMoney());
				passenger.updateTotalMoney(royalEscapeRoom.getBonusMoney());
				lblRemainingMoney.setText(passenger.showMoneyLeft());
			}
		}
		
		if (chosenLockNum != 6 || !royalEscapeRoom.getLockState(chosenLockNum)) {
			lblBonusMoney.setText(royalEscapeRoom.showBonusAmount());
		}
	}
	
	private void showNextLock() {
		if (actualLockNum != 4) {
			lockIndex++;
			
			actualLockNum = EscapeRoom.LOCK_NUMS[lockIndex];
			
			lblClue.setText("Clue #" + (lockIndex + 1) + ": " + royalEscapeRoom.getLockClue(actualLockNum));
		
			if (royalEscapeRoom.needsVisual(actualLockNum)) {
				gridVisual.setVisible(true);
				showGridVisual();
			} else {
				gridVisual.setVisible(false);
			}
			
			btnHint.setDisable(false);
			lblErrorMessage.setText("");
			lblHint.setText("");
			lblResult.setText("");
			btnNextLock.setVisible(false);

			setLockChoiceBox();
		} else {
			actualLockNum = 6;
			showLastLock();
		}
	}
	
	private void showLastLock() {
		final int SCREEN_WIDTH = 700;
		
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		chosenLockNum = 6;
		
		lblTitle.setText("Final lock! This is the lock on the lifeboat.");
		root.getChildren().add(lblTitle);
		
		// Section for word input
		BorderPane borderWordInput = new BorderPane();
		// Top of BorderPane
		HBox hbxTop = new HBox(GAP);
		hbxTop.setAlignment(Pos.CENTER);
		Label lblWordPrompt = new Label("Enter a word:");
		lblWordPrompt.setFont(Font.font(MEDIUM_FONT));
		txtWord = new TextField();
		Button btnEnterWord = new Button("Enter");
		btnEnterWord.setFont(Font.font(MEDIUM_FONT));
		btnEnterWord.setOnAction(event -> enterWord());
		hbxTop.getChildren().addAll(lblWordPrompt, txtWord, btnEnterWord);
		BorderPane.setAlignment(hbxTop, Pos.TOP_CENTER);
		borderWordInput.setTop(hbxTop);
		// Right side of BorderPane
		lblWordPyramid = new Label("");
		lblWordPyramid.setAlignment(Pos.CENTER_LEFT);
		lblWordPyramid.setFont(Font.font(MEDIUM_FONT));
		borderWordInput.setRight(lblWordPyramid);
		// Center of BorderPane
		StackPane stackCenter = new StackPane();
		stackCenter.setPadding(new Insets(GAP, GAP, GAP, GAP));
		ImageView imgLifeBoat = new ImageView(new Image(getClass().getResource("/images/lifeBoat2.png").toString()));
		imgLifeBoat.setFitHeight(150);
		imgLifeBoat.setPreserveRatio(true);
		imgLock.setImage(CLOSED_LOCK);
		imgLock.setFitHeight(90);
		stackCenter.getChildren().addAll(imgLifeBoat, imgLock);
		borderWordInput.setCenter(stackCenter);
		BorderPane.setAlignment(stackCenter, Pos.CENTER);
		// Bottom of BorderPane
		VBox vbxBottom = new VBox(GAP);
		vbxBottom.setAlignment(Pos.CENTER);
		lblClue.setText("Clue: " + royalEscapeRoom.getLockClue(chosenLockNum));
		Button btnHint = new Button("Get Hint");
		btnHint.setFont(Font.font(SMALL_FONT));
		btnHint.setOnAction(event -> showHint());
		lblHint.setText("");
		lblErrorMessage.setText("");
		vbxBottom.getChildren().addAll(lblClue, btnHint, lblHint, lblErrorMessage);
		borderWordInput.setBottom(vbxBottom);
		root.getChildren().add(borderWordInput);
		
		// Section for lock combo
		HBox hbxLockCombo = new HBox(GAP);
		hbxLockCombo.setAlignment(Pos.CENTER);
		// Label for lock combo prompt
		Label lblComboPrompt = new Label("Enter lock combination:");
		lblComboPrompt.setFont(Font.font(MEDIUM_FONT));
		// TextField for lock combo input
		txtLockCombo = new TextField();
		txtLockCombo.setDisable(true);
		hbxLockCombo.getChildren().addAll(lblComboPrompt, txtLockCombo);
		root.getChildren().add(hbxLockCombo);
		
		// Section for unlocking/output
		HBox hbxUnlocking = new HBox(GAP);
		hbxUnlocking.setAlignment(Pos.CENTER);
		// Button for event handler
		btnUnlock.setVisible(false);
		// Label for result output
		lblResult.setText("");
		hbxUnlocking.getChildren().addAll(btnUnlock, lblResult, lblBonusMoney);
		root.getChildren().add(hbxUnlocking);
		
		// Button to end game
		btnEndGame.setVisible(false);
		root.getChildren().addAll(lblRemainingMoney, btnEndGame);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("Royal Escape Room: Final Lock");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	private void enterWord() {
		String word = txtWord.getText().trim();
		
		// Input validation
		if (word.isEmpty()) {
			lblErrorMessage.setText("Invalid input (cannot be blank).");
			return;
		} else if (!royalEscapeRoom.validateWord(word)) {
			lblErrorMessage.setText("Invalid input (must be a word).");
			return;
		}
		
		lblErrorMessage.setText("");
		lblWordPyramid.setText(royalEscapeRoom.getConsonantPyramid(word));
		txtLockCombo.setDisable(false);
		btnUnlock.setVisible(true);
		txtWord.setDisable(true);
	}
	
	private void showWordUnscrambleScreen() {
		final int SCREEN_WIDTH = 750, THIS_SCREEN_HEIGHT = 800;
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		// Top section
		VBox vbxTop = new VBox(GAP);
		vbxTop.setAlignment(Pos.CENTER);
		lblTitle.setText(WordUnscramble.NAME);
		lblInstructions.setText(WordUnscramble.showInstructions());
		Label lblLetters = new Label("Letters: " + WordUnscramble.WORD);
		lblLetters.setFont(Font.font(MEDIUM_FONT));
		lblLetters.setTextFill(Color.MEDIUMORCHID);
		vbxTop.getChildren().addAll(lblTitle, lblInstructions, lblLetters);
		BorderPane.setAlignment(vbxTop, Pos.TOP_CENTER);
		root.setTop(vbxTop);
		
		// Left section
		VBox vbxLeft = new VBox(GAP);
		Label lblBonusPtsSystem = new Label(WordUnscramble.showPointsSystem());
		lblBonusPtsSystem.setFont(Font.font(MEDIUM_FONT));
		Label lblNumOfPossibleWords = new Label("Number of possible words: " + NUM_OF_POSSIBLE_WORDS);
		lblNumOfPossibleWords.setFont(Font.font(MEDIUM_FONT));
		lblBonusMoney.setText("");
		vbxLeft.getChildren().addAll(lblBonusPtsSystem, lblNumOfPossibleWords, lblBonusMoney, lblRemainingMoney);
		BorderPane.setAlignment(vbxLeft, Pos.CENTER);
		root.setLeft(vbxLeft);
		
		// Right section
		VBox vbxRight = new VBox(GAP);
		lblPossibleWords = new Label("");
		lblPossibleWords.setFont(Font.font(MEDIUM_FONT));
		
		btnDone = new Button("Done");
		btnDone.setFont(Font.font(SMALL_FONT));
		btnDone.setOnAction(event -> checkWords());
		vbxRight.getChildren().addAll(lblPossibleWords, btnDone);
		BorderPane.setAlignment(vbxRight, Pos.CENTER_LEFT);
		root.setRight(vbxRight);
		
		// Center section
		GridPane gridCenter = new GridPane();
		gridCenter.setHgap(GAP);	// sets gaps between columns
		gridCenter.setVgap(GAP);	// sets gaps between rows
		gridCenter.setPadding(new Insets(GAP, GAP, GAP, GAP));
		
		txtWords = new TextField[NUM_OF_POSSIBLE_WORDS];
		imgOutputs = new ImageView[NUM_OF_POSSIBLE_WORDS];
		
		for (int j = 0; j < NUM_OF_POSSIBLE_WORDS; j++) {
			txtWords[j] = new TextField();
			imgOutputs[j] = new ImageView(imgBLANK);
			imgOutputs[j].setFitHeight(35);
			imgOutputs[j].setPreserveRatio(true);
			
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
		
		// Bottom section
		btnEndGame.setDisable(true);
		root.setBottom(btnEndGame);
		BorderPane.setAlignment(btnEndGame, Pos.CENTER);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, THIS_SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("Word Unscramble");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	private void checkWords() {
		String[] wordsToCheck = new String[NUM_OF_POSSIBLE_WORDS];
		final Image BLANK = new Image(getClass().getResource("/images/blankAnswer.png").toString());
		final Image INCORRECT = new Image(getClass().getResource("/images/incorrect.png").toString());
		final Image CORRECT = new Image(getClass().getResource("/images/correct.png").toString());
		
		for (int i = 0; i < NUM_OF_POSSIBLE_WORDS; i++) {
			wordsToCheck[i] = txtWords[i].getText().trim();
		}
		
		String[] results = wordUnscrambleGame.checkWords(wordsToCheck);
		
		for (int j = 0; j < results.length; j++) {
			if (results[j].equals(WordUnscramble.BLANK)) {
				imgOutputs[j].setImage(BLANK);
			} else if (results[j].equals(WordUnscramble.CORRECT)) {
				imgOutputs[j].setImage(CORRECT);
			} else {
				imgOutputs[j].setImage(INCORRECT);
			}
		}
		
		String strPossibleWords = "Possible Words:\n";
		String[] possibleWords = WordUnscramble.POSSIBLE_WORDS;
		for (int i = 0; i < possibleWords.length; i++) {
			strPossibleWords += possibleWords[i];
			if (i % 2 == 0) {	// even
				strPossibleWords += "\t\t";
			} else if (i != possibleWords.length - 1) {	// odd
				strPossibleWords += "\n";
			}
		}
		lblPossibleWords.setText(strPossibleWords);
		
		btnDone.setDisable(true);
		btnEndGame.setDisable(false);
		lblBonusMoney.setText(wordUnscrambleGame.showBonusAmount());
		
		passenger.updateTotalMoney(wordUnscrambleGame.getBonusMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
		
	}
	
	private void showActivitiesScreen() {
		final int SCREEN_WIDTH = 1250;
		final int NUM_OF_ACTIVITIES = ActivitiesScenario.ACTIVITIES.length;
		final int HALF = NUM_OF_ACTIVITIES / 2;
		
		ImageView[] imgActivities = new ImageView[NUM_OF_ACTIVITIES];
		Label lblActivity;
		int rowIndex;
		
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// Title
		lblTitle.setText("Day " + dayNumber + ": Activities");
		lblInstructions.setText(ActivitiesScenario.showInstructions());
		root.getChildren().addAll(lblTitle, lblInstructions);
		
		// GridPane section for user input
		GridPane gridInput = new GridPane();
		gridInput.setHgap(GAP);	// sets gaps between columns
		gridInput.setVgap(GAP);	// sets gaps between rows
		gridInput.setPadding(new Insets(GAP, GAP, GAP, GAP));
		gridInput.setAlignment(Pos.CENTER);
		
		txtNumsPerActivities = new TextField[NUM_OF_ACTIVITIES];
		
		for (int i = 0; i < NUM_OF_ACTIVITIES; i++) {
			imgActivities[i] = new ImageView(new Image(getClass().getResource("/images/activity" + i + ".png").toString()));
			imgActivities[i].setFitHeight(70);
			imgActivities[i].setPreserveRatio(true);
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
		
		HBox hbxResult = new HBox(GAP);
		hbxResult.setAlignment(Pos.CENTER);
		btnDoneActivities = new Button("Done");
		btnDoneActivities.setFont(Font.font(SMALL_FONT));
		btnDoneActivities.setOnAction(event -> calculateActivitiesCost());
		lblResult.setText("");
		lblResult.setFont(Font.font(MEDIUM_FONT));
		hbxResult.getChildren().addAll(btnDoneActivities, lblResult);
		
		lblErrorMessage.setText("");
		
		HBox hbxBottom = new HBox(GAP);
		hbxBottom.setAlignment(Pos.CENTER);
		btnStartCruise.setText("Next");
		btnStartCruise.setDisable(true);
		hbxBottom.getChildren().addAll(btnStartCruise, lblRemainingMoney);
		root.getChildren().addAll(hbxResult, lblErrorMessage, hbxBottom);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("End Screen");
			myStage.setScene(mainScene);
			myStage.show();
			dayNumber++;
		}
	}
	
	private void calculateActivitiesCost() {
		int[] numOfEachActivity = new int[ActivitiesScenario.ACTIVITIES.length];
		
		for (int i = 0; i < numOfEachActivity.length; i++) {
			String textInput = txtNumsPerActivities[i].getText().trim();
			
			if (textInput.isEmpty()) {
				lblErrorMessage.setText("Invalid entry (empty box).");
				return;
			}
			
			try {
				numOfEachActivity[i] = Integer.parseInt(textInput);
			} catch (NumberFormatException e) {
				lblErrorMessage.setText("Invalid entry (Please enter a number)");
				return;
			}
		}
		
		ActivitiesScenario activitiesScenario = new ActivitiesScenario(numOfEachActivity);
		
		activitiesScenario.calculateCost();
		
		lblErrorMessage.setText("");
		lblResult.setText(activitiesScenario.showChangeInMoney());
		
		passenger.updateTotalMoney(activitiesScenario.getChangeInMoney());
		lblRemainingMoney.setText(passenger.showMoneyLeft());
		
		btnStartCruise.setDisable(false);
		btnDoneActivities.setDisable(true);
	}
	
	private void showEndScreen() {
		final int SCREEN_WIDTH = 1160, END_SCREEN_HEIGHT = 750;
		final int IMG_HEIGHT = 200;
		
		VBox root = new VBox(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setAlignment(Pos.CENTER);
		
		// 3 Images, including result (depending on whether the player lost or won)
		HBox hbxHeader = new HBox(GAP);
		ImageView imgOasis = new ImageView(new Image(getClass().getResource("/images/oasisOTS.png").toString()));
		imgOasis.setFitHeight(IMG_HEIGHT);
		imgOasis.setPreserveRatio(true);
		ImageView imgLogo = new ImageView(new Image(getClass().getResource("/images/logo2.png").toString()));
		imgLogo.setFitHeight(IMG_HEIGHT);
		imgLogo.setPreserveRatio(true);
		ImageView imgResult = new ImageView();
		imgResult.setFitHeight(IMG_HEIGHT);
		imgResult.setPreserveRatio(true);
		if (passenger.getWinStatus()) {
			imgResult.setImage(new Image(getClass().getResource("/images/winner.png").toString()));
		} else {
			imgResult.setImage(new Image(getClass().getResource("/images/loser.png").toString()));
		}
		hbxHeader.getChildren().addAll(imgOasis, imgLogo, imgResult);
		hbxHeader.setAlignment(Pos.CENTER);
		root.getChildren().add(hbxHeader);
		
		// Info
		lblResult.setText(passenger.showOverallResult());
		lblResult.setFont(Font.font(XL_FONT));
		root.getChildren().addAll(lblResult);
		
		// Images section
		HBox hbxImages = new HBox(GAP);
		ImageView imgPoolDeck = new ImageView(new Image(getClass().getResource("/images/poolDeck.png").toString()));
		imgPoolDeck.setFitHeight(IMG_HEIGHT);
		imgPoolDeck.setPreserveRatio(true);
		ImageView imgBoardwalk = new ImageView(new Image(getClass().getResource("/images/boardwalkView.png").toString()));
		imgBoardwalk.setFitHeight(IMG_HEIGHT);
		imgBoardwalk.setPreserveRatio(true);
		ImageView imgMiniGolfCourse = new ImageView(new Image(getClass().getResource("/images/golf.png").toString()));
		imgMiniGolfCourse.setFitHeight(IMG_HEIGHT);
		imgMiniGolfCourse.setPreserveRatio(true);
		ImageView imgSailaway = new ImageView(new Image(getClass().getResource("/images/nyc.png").toString()));
		imgSailaway.setFitHeight(IMG_HEIGHT);
		imgSailaway.setPreserveRatio(true);
		ImageView imgUltimateAbyss = new ImageView(new Image(getClass().getResource("/images/ultimateAbyss.png").toString()));
		imgUltimateAbyss.setFitHeight(IMG_HEIGHT);
		imgUltimateAbyss.setPreserveRatio(true);
		ImageView imgShips = new ImageView(new Image(getClass().getResource("/images/oasisAndEnchantment.png").toString()));
		imgShips.setFitHeight(IMG_HEIGHT);
		imgShips.setPreserveRatio(true);
		hbxImages.getChildren().addAll(imgPoolDeck, imgBoardwalk, imgMiniGolfCourse, imgSailaway, imgUltimateAbyss, imgShips);
		hbxImages.setAlignment(Pos.CENTER);
		root.getChildren().add(hbxImages);
		
		Window routeWindow = mainScene.getWindow();
		mainScene = new Scene(root, SCREEN_WIDTH, END_SCREEN_HEIGHT);
		
		if (routeWindow instanceof Stage) {
			Stage myStage = (Stage) routeWindow;
			myStage.setTitle("End Screen");
			myStage.setScene(mainScene);
			myStage.show();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}