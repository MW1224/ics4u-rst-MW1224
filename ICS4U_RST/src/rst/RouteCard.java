package rst;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RouteCard extends Button {

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
			"Perfect Day at CocoCay", "Arabian Gulf", "Mediterranean"};
	public static final int[] ROUTE_COSTS = {1470, 1430, 1360, 1450, 1410, 1490, 1370, 1400, 1440, 1390, 1350,
			1460, 1480, 1380, 1420, 1500};
	
	private boolean state;
	private int cost, cardNum;
	private String routeName;
	
	public RouteCard(int cardNum) {
		super();
		this.cardNum = cardNum;
		state = false;
		routeName = ROUTE_NAMES[cardNum - 1];
		cost = ROUTE_COSTS[cardNum - 1];
		setGraphic(new ImageView(BLANK));
		setText(String.valueOf(cardNum));
	}
	
	public static String showInstructions() {
		return "There are " + ROUTE_NAMES.length + " possible cruise routes, and each route costs a different amount of money."
					+ " Click on one card to flip it over and reveal your cruise's route.";
	}
	
	public int getChangeInMoney() {
		return -1 * cost;
	}
	
	public void disableCard() {
		state = true;
	}
	
	public void selectRouteCard() {
		if (!state) {
			setGraphic(new ImageView(ROUTE_IMAGES[cardNum]));
			setText(routeName + " ($" + cost + ")");
		}
	}
	
	public String showResult() {
		return "Cruise route: " + routeName + "\nCost: $" + cost;
	}
	
	public String toString() {
		return "Cruise Route Random Selection";
	}
}