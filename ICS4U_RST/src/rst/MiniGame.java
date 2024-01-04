package rst;

public class MiniGame {

	protected int bonusMoney;
	
	public int getBonusMoney() {
		return bonusMoney;
	}
	
	public String showBonusAmount() {
		return "Bonus Money: $" + bonusMoney;
	}
}