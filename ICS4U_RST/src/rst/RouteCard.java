package rst;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The <code>RouteCard</code> class, a subclass of the <code>Button</code> class, models a card that holds a
 * cruise route's image, name and cost. It can be flipped on its back side (blank image) or flipped over on its
 * front side (shows the route's name, image and cost.
 */
public class RouteCard extends Button {
	// Class data fields
	public static final int CARD_DIMENSION = 120;	// card's dimensions (width & height)
	private static final Image SCANDINAVIA = new Image(RouteCard.class.getResource("/images/SCANDINAVIA.png").toString());
	private static final Image SOUTH_AMERICA = new Image(RouteCard.class.getResource("/images/SOUTH_AMERICA.png").toString());
	private static final Image WEST_COAST = new Image(RouteCard.class.getResource("/images/WEST_COAST.png").toString());
	private static final Image ASIA = new Image(RouteCard.class.getResource("/images/ASIA.png").toString());
	private static final Image BRITISH_ISLES = new Image(RouteCard.class.getResource("/images/BRITISH_ISLES.png").toString());
	private static final Image HAWAII = new Image(RouteCard.class.getResource("/images/HAWAII.png").toString());
	private static final Image MEXICO = new Image(RouteCard.class.getResource("/images/MEXICO.png").toString());
	private static final Image PANAMA_CANAL = new Image(RouteCard.class.getResource("/images/PANAMA_CANAL.png").toString());
	private static final Image ALASKA = new Image(RouteCard.class.getResource("/images/ALASKA.png").toString());
	private static final Image CARIBBEAN = new Image(RouteCard.class.getResource("/images/CARIBBEAN.png").toString());
	private static final Image EAST_COAST = new Image(RouteCard.class.getResource("/images/WEST_COAST.png").toString());
	private static final Image SOUTH_PACIFIC = new Image(RouteCard.class.getResource("/images/SOUTH_PACIFIC.png").toString());
	private static final Image GREEK_ISLES = new Image(RouteCard.class.getResource("/images/GREEK_ISLES.png").toString());
	private static final Image COCOCAY = new Image(RouteCard.class.getResource("/images/COCOCAY.png").toString());
	private static final Image ARABIAN_GULF = new Image(RouteCard.class.getResource("/images/ARABIAN_GULF.png").toString());
	private static final Image MEDITERRANEAN = new Image(RouteCard.class.getResource("/images/MEDITERRANEAN.png").toString());
	private static final Image BLANK = new Image(RouteCard.class.getResource("/images/BLANK.png").toString());
	private static final Image[] ROUTE_IMAGES = {SCANDINAVIA, SOUTH_AMERICA, WEST_COAST, ASIA, BRITISH_ISLES, HAWAII,
			MEXICO, PANAMA_CANAL, ALASKA, CARIBBEAN, EAST_COAST, SOUTH_PACIFIC, GREEK_ISLES, COCOCAY,
			ARABIAN_GULF, MEDITERRANEAN};
	public static final String[] ROUTE_NAMES = {"Scandinavia", "South America", "West Coast", "Asia", "British Isles",
			"Hawaii", "Mexico", "Panama Canal", "Alaska", "Caribbean", "East Coast", "South Pacific", "Greek Isles",
			"P.D. at CocoCay", "Arabian Gulf", "Mediterranean"};
	public static final int[] ROUTE_COSTS = {1470, 1430, 1360, 1450, 1410, 1490, 1370, 1400, 1440, 1390, 1350,
			1460, 1480, 1380, 1420, 1500};
	
	// Instance data fields
	private boolean state;		// card's state (true if disabled, false otherwise)
	private ImageView imgRoute;	// card's route image
	private int cost, cardNum;	// card number refers to this specific RouteCard's number in terms of the other 15 routes
	private String routeName;
	
	/**
	 * This default constructor creates a RouteCard with Button's constructor, false state (not disabled), the
	 * route's corresponding cost, displaying its card number, the card's standard size and a blank image.
	 * 
	 * @param cardNum
	 * 			This specific RouteCard's number relating to the other 15 routes
	 */
	public RouteCard(int cardNum) {
		super();		// call Button's constructor
		this.cardNum = cardNum;
		state = false;	// false state because not disabled
		routeName = ROUTE_NAMES[cardNum - 1];	// subtract one for index because array's indices start at 0, not 1
		cost = ROUTE_COSTS[cardNum - 1];
		
		// Set its Button properties
		setText(String.valueOf(cardNum));				// set its text to its card number
		setContentDisplay(ContentDisplay.BOTTOM);		// set its text on the bottom
		setPrefSize(CARD_DIMENSION, CARD_DIMENSION);	// set its size to 120 x 120
		
		// Sets its image to blank image
		imgRoute = new ImageView(BLANK);
		imgRoute.setFitHeight(CARD_DIMENSION - 20);		// set its height smaller than the actual card to allow room for text on bottom
		imgRoute.setPreserveRatio(true);
		setGraphic(imgRoute);
	}
	
	/**
	 * This class method returns a string that contains the Cruise Route Selection's overview & instructions.
	 * 
	 * @return A <code>String</code> containing the Cruise Route Selection's overview & instructions.
	 */
	public static String showInstructions() {
		return "There are " + ROUTE_NAMES.length + " possible cruise routes, and each route costs a different amount of money."
					+ " Click on one card to flip it over and reveal your cruise's route.";
	}
	
	/**
	 * This getter/accessor method returns the route's cost.
	 * 
	 * @return An <code>int</code> containing the route's cost.
	 */
	public int getCost() {
		return -1 * cost;	// multiply by -1 because cost is negative money
	}
	
	/**
	 * Disables the card by setting its disabled state to true.
	 */
	public void disableCard() {
		state = true;
	}
	
	/**
	 * This getter/accessor method returns the card's disabled state (true if disabled, false if not).
	 * 
	 * @return A <code>boolean</code> containing the card's disabled state.
	 */
	public boolean getState() {
		return state;
	}
	
	/**
	 * Flips over the card by setting its image to its route image instead of blank. Also, sets text
	 * to its route name instead of its card number.
	 */
	public void selectRouteCard() {
		if (!state) {	// if card isn't disabled
			// Set its ImageView to an image of the route
			imgRoute.setImage(ROUTE_IMAGES[cardNum - 1]);
			
			// Sets its text to the route's name instead of the card #
			setText(routeName);
		}
	}
	
	/**
	 * Returns the chosen cruise route (its name & cost) for output.
	 * 
	 * @return A <code>String</code> containing the chosen cruise route (its name & cost) for output/
	 */
	public String showResult() {
		return "Cruise route: " + routeName + "\nCost: $" + cost;
	}
}