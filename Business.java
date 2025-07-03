import javax.swing.JOptionPane;

public final class Business {
	private String name;
	private int level;
	private int revenue;
	private int cost;
	private Game game;
	
	// Class variable used to track free business upgrades, or 2 upgrades in 1
	private static boolean deductMoney = true;
	
	public Business(Game game, String name, int cost) {
		this.game = game;
		this.name = name;
		this.level = 0;
		this.cost = cost;
		this.revenue = (int) (this.cost * 2.5);
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
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
		if (level == 0) {
			JOptionPane.showMessageDialog(null, "You cannot upgrade this business! Please buy it first", 
					"Business not purchased yet", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else if (level == 10) {
			JOptionPane.showMessageDialog(null, "This business is already at the maximum level!", "Maximum level reached", 
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else if (deductMoney && this.game.getMoneyAmount() < this.cost) {
			JOptionPane.showMessageDialog(null, "You can't afford to upgrade this business!", "Insufficient funds", 
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		this.level++;
		if (deductMoney) {
			int newMoneyValue = this.game.getMoneyAmount() - this.getCost();
			int newTotalSpent = this.game.getSpentMoney() + this.getCost();
			this.game.getGui().getTotalMoneySpentTextArea().setText(Integer.toString(newTotalSpent) + "$");
			this.game.setMoneyAmount(newMoneyValue);
			this.game.setSpentMoney(newTotalSpent);
			this.game.getGui().getMoneyAmount().setText(Integer.toString(newMoneyValue));
		}
		else {
			JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
		}
		this.game.setBusinessUpgradeMultiplier(1.0);
		this.game.setUpgradeCostMultiplier(1.0);
		
		this.cost = (int) (this.cost * game.getBusinessUpgradeMultiplier() * game.getPermItemBusinessUpgradeMultiplier() * 
				game.getUpgradeCostMultiplier() * 1.3);
		this.revenue = (int) (this.revenue * game.getRevenueMultiplier() * game.getPermItemRevenueMultiplier() * 
				game.getUpgradeCostMultiplier() * 1.4);
		
		if (this.game.getNbUpgrades() == 2) {
			this.game.setNbUpgrades(1);
			deductMoney = false;
			upgrade();
		}
		int newRevenue = this.game.calculateRevenue();
		this.game.getGui().getExpectedRevenue().setText(Integer.toString(newRevenue));
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
