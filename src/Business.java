
public final class Business {
	private String name;
	private int level;
	private long revenue;
	private int baseCost;
	private int cost;
	private Game game;
	
	// Class variable used to track free business upgrades, or 2 upgrades in 1
	private static boolean deductMoney = true;
	
	public Business(Game game, String name, int baseCost) {
		this.game = game;
		this.name = name;
		this.level = 0;
		this.baseCost = baseCost;
		this.cost = (int) (baseCost * this.game.getBusinessPurchaseMultiplier() * this.game.getPermItemBusinessPurchaseMultiplier()
				* this.game.getUpgradeCostMultiplier());
		this.revenue = (int) (baseCost * 2.5 * this.game.getRevenueMultiplier() * this.game.getPermItemRevenueMultiplier());
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(int baseCost) {
		this.baseCost = baseCost;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public static boolean isDeductMoney() {
		return deductMoney;
	}

	public static void setDeductMoney(boolean deductMoney) {
		Business.deductMoney = deductMoney;
	}

	/*
	 * Upgrade the selected business if the player has enough money, and the business has already been purchased
	 * and is not at its max level.
	 */
	public boolean upgrade() {
		if (level == 0 || level == 10) {
			return false;
		}
		else if (deductMoney && this.game.getMoneyAmount() < this.cost) {
			return false;
		}
		
		this.level++;
		if (deductMoney) {
			long newMoneyValue = this.game.getMoneyAmount() - this.cost;
			this.game.setMoneyAmount(newMoneyValue);
			this.game.getGui().getMoneyAmount().setText(Long.toString(newMoneyValue));
			
			long newTotalSpent = this.game.getSpentMoney() + this.cost;
			this.game.getGui().getTotalMoneySpentTextArea().setText(Long.toString(newTotalSpent) + "$");
			this.game.setSpentMoney(newTotalSpent);
			
			this.game.setBusinessUpgradeMultiplier(1.0);
			this.game.setUpgradeCostMultiplier(1.0);
		}
		
		this.baseCost = (int) (this.baseCost * 1.3);
		this.cost = this.game.calculateBusinessUpgradeCost(this);
		this.revenue = (long) (this.revenue * game.getRevenueMultiplier() * game.getPermItemRevenueMultiplier() * 1.4);
		
		if (this.game.getNbUpgrades() == 2) {
			this.game.setNbUpgrades(1);
			deductMoney = false;
			upgrade();
		}
		long newRevenue = this.game.calculateExpectedRevenue();
		this.game.getGui().getExpectedRevenue().setText(Long.toString(newRevenue));
		deductMoney = true;
		return true;
	}

	@Override
	public String toString() {
		if (this.level >= 1) {
			return this.name + ", level " + this.level + ", upgrade cost = " + this.cost + "$";
		}
		return this.name + ", purchase cost = " + this.cost + "$";
	}

	/*
	 * Two businesses are equal if they have the same name and level
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == null || obj == null) {
			return false;
		}
		return this.getName().equals(((Business) obj).getName()) && this.getLevel() == ((Business) obj).getLevel();
	}
	
	
}
