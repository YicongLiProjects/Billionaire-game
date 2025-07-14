import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public final class PermanentItem extends Item {
	
	private int level;
	// This icon will be displayed on the JTable cell representing the item
	private ImageIcon icon;
	private int cost;
	
	// Used for free upgrades or two upgrades in one
	private static boolean deductMoney = true;
	
	public PermanentItem(Game game, String name, int baseCost, ImageIcon icon) {
		super(game, name, baseCost);
		// TODO Auto-generated constructor stub
		this.level = 0;
		this.icon = icon;
		this.cost = (int) (baseCost * this.getGame().getPermItemUpgradeMultiplier() * this.getGame().getItemUpgradePriceMultiplier()
				* this.getGame().getUpgradeCostMultiplier());
	}

	public ImageIcon getIcon() {
		return icon;
	}
	
	// Helper for the buy method used on items
	/*
	 * Applies a buff based on the item name and recalculate item costs
	 */
	public void applyPermanentBuff(String name) {
		switch(name) {
			case "Book of business":
				this.getGame().setPermItemRevenueMultiplier(1.2);
				this.getGame().calculateExpectedRevenue();
				break;
			case "Construction license":
				this.getGame().setPermItemBusinessUpgradeMultiplier(0.9);
				this.getGame().recalculateBusinessUpgradeCost();
				break;
			case "Multi-use voucher":
				this.getGame().setPermItemBusinessPurchaseMultiplier(0.9);
				this.getGame().recalculateBusinessPurchaseCost();
				break;
			case "Tool box":
				this.getGame().setPermItemUpgradeMultiplier(0.9);
				this.getGame().recalculateItemUpgradeCost();
				break;
			default:
				JOptionPane.showMessageDialog(null, "This item is not valid!", "Invalid permanent item", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*
	 * Upgrades this item by updating its level, cost and buff
	 * Call this method in the GUI, with the parameter being true
	 * If the item's level is 0 (not bought yet) or 10 (maxed out), or if the player doesn't have
	 * enough money to upgrade, warn the player.
	 */
	public boolean upgrade() {
		if (this.level == 0 || this.level == 10) {
			return false;
		}
		else if (this.getGame().getMoneyAmount() < this.getCost() && deductMoney) {
			return false;
		}
		this.level++;
		// If the player has a TWO_UPGRADES buff, only deduct the money once
		if (deductMoney) {
			int newMoneyValue = this.getGame().getMoneyAmount() - this.getCost();
			this.getGame().setMoneyAmount(newMoneyValue);
			this.getGame().getGui().getMoneyAmount().setText(Integer.toString(newMoneyValue));
			int newTotalSpent = this.getGame().getSpentMoney() + this.getCost();
			this.getGame().setSpentMoney(newTotalSpent);
			this.getGame().getGui().getTotalMoneySpentTextArea().setText(Integer.toString(newTotalSpent) + "$");
			
			this.getGame().setItemUpgradePriceMultiplier(1.0);
			this.getGame().setUpgradeCostMultiplier(1.0);
		}
		
		int cost = (int) (this.getGame().calculateItemUpgradeCost(this) * 2.0);
		this.setCost(cost);
		
		// Increase the buff when the item is upgraded
		switch(this.getName()) {
			case "Book of business":
				if (this.level == 4) {
					this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() + 0.04);
				}
				else {
					this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() + 0.02);
				}
				this.getGame().getGui().getBookUpgradeProgressBar().setValue(level);
				this.getGame().getGui().getBookUpgradeProgressBar().setString("Level " + level);
				
				int newRevenue = this.getGame().calculateExpectedRevenue();
				this.getGame().getGui().getExpectedRevenue().setText(Integer.toString(newRevenue));
				break;
			case "Construction license":
				if (this.level == 4) {
					this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() - 0.04);
				}
				else {
					this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() - 0.02);
				}
				this.getGame().getGui().getLicenseUpgradeProgressBar().setValue(level);
				this.getGame().getGui().getLicenseUpgradeProgressBar().setString("Level " + level);
				this.getGame().recalculateBusinessUpgradeCost();
				break;
			case "Multi-use voucher":
				if (this.level == 4) {
					this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() - 0.04);
				}
				else {
					this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() - 0.02);
				}
				this.getGame().getGui().getVoucherUpgradeProgressBar().setValue(level);
				this.getGame().getGui().getVoucherUpgradeProgressBar().setString("Level " + level);
				this.getGame().recalculateBusinessPurchaseCost();
				break;
			case "Tool box":
				if (this.level == 4) {
					this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() - 0.02);
				}
				else {
					this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() - 0.01);
				}
				this.getGame().getGui().getToolboxUpgradeProgressBar().setValue(level);
				this.getGame().getGui().getToolboxUpgradeProgressBar().setString("Level " + level);
				this.getGame().recalculateItemUpgradeCost();
				break;
		}
		
		if (this.getGame().getNbUpgrades() == 2) {
			this.getGame().setNbUpgrades(1);
			deductMoney = false;
			upgrade();
		}
		this.getGame().getGui().getMoneyAmountUpgradePanel().setText(Integer.toString(this.getGame().getMoneyAmount()));
		deductMoney = true;
		return true;
	}

	public int getLevel() {
		return level;
	}

	// Used only to set level to 1 after purchasing the item
	// For testing purposes, can set the level to any integer.
	public void setLevel(int level) {
		this.level = level;
	}
	
	// Set to static, as the player can choose to upgrade any permanent item for free with the buff
	public static boolean isDeductMoney() {
		return deductMoney;
	}

	public static void setDeductMoney(boolean deductMoney) {
		PermanentItem.deductMoney = deductMoney;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		if (this.level >= 1) {
			return this.getName() + ", level " + this.getLevel() + ", " + this.getCost() + "$";
		}
		return this.getName() + ", " + this.getBaseCost() + "$";
	}

	/*
	 * Two permanent items are equal if they have the same name and level
	 * For testing purposes only.
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == null || obj == null || !(obj instanceof PermanentItem)) {
			return false;
		}
		return this.getName().equals(((PermanentItem) obj).getName()) && this.getLevel() == ((PermanentItem) obj).getLevel();
	}
	
}
