import javax.swing.JOptionPane;

public final class TemporaryItem extends Item implements Comparable<TemporaryItem>{
	private int level;
	private boolean used = false;
	
	public TemporaryItem(Game game, Buff buff, String name, int baseCost) {
		super(game, buff, name, baseCost);
		this.level = 0;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Some temporary items have different levels for the buff they apply
	 */
	public TemporaryItem(Game game, Buff buff, String name, int baseCost, int level) {
		super(game, buff, name, baseCost);
		this.level = level;
	}
	
	/*
	 *  Applies buff to the player
	 *  Purchase and upgrade cost reduction and revenue increase: 4 levels, permanent item buff: 3 levels, the rest = 1 level
	 *  If there are two items with the same buff, apply the buff with highest level
	 *  Recalculate item and business costs to render to the UI
	 *  
	 */
	public void applyBuff() {
		switch (this.getBuff()) {
			default:
				JOptionPane.showMessageDialog(null, "This buff doesn't exist in the game! Try something else", 
						"Buff does not exist", JOptionPane.INFORMATION_MESSAGE);
				break;
			// Perfectly fresh pizza
			case BUSINESS_PURCHASE_COST_DECREASE:
				switch (this.level) {
					case 1:
						this.getGame().setBusinessPurchaseMultiplier(0.85);
						break;
					case 2:
						this.getGame().setBusinessPurchaseMultiplier(0.8);
						break;
					case 3:
						this.getGame().setBusinessPurchaseMultiplier(0.75);
						break;
					case 4:
						this.getGame().setBusinessPurchaseMultiplier(0.7);
						break;
				}
				this.getGame().recalculateBusinessPurchaseCost();
				break;
			// Mysterious chocolate
			case BUSINESS_UPGRADE_COST_DECREASE:
				switch (this.level) {
					case 1:
						this.getGame().setBusinessUpgradeMultiplier(0.9);
						break;
					case 2:
						this.getGame().setBusinessUpgradeMultiplier(0.8);
						break;
					case 3:
						this.getGame().setBusinessUpgradeMultiplier(0.7);
						break;
					case 4:
						this.getGame().setBusinessUpgradeMultiplier(0.6);
						break;
				}
				this.getGame().recalculateBusinessUpgradeCost();
				break;
			// Investment plan
			case REVENUE_INCREASE:
				switch (this.level) {
					case 1:
						this.getGame().setRevenueMultiplier(1.25);
						break;
					case 2:
						this.getGame().setRevenueMultiplier(1.5);
						break;
					case 3:
						this.getGame().setRevenueMultiplier(1.75);
						break;
					case 4:
						this.getGame().setRevenueMultiplier(2.0);
						break;
				}
				this.getGame().calculateExpectedRevenue();
				break;
			// Efficient tools renting coupon
			case PERMANENT_ITEM_UPGRADE_COST_DECREASE:
				switch (this.level) {
					case 1:
						this.getGame().setItemUpgradePriceMultiplier(0.8);
						break;
					case 2:
						this.getGame().setItemUpgradePriceMultiplier(0.7);
						break;
					case 3:
						this.getGame().setItemUpgradePriceMultiplier(0.6);
						break;
					case 4:
						this.getGame().setItemUpgradePriceMultiplier(0.5);
						break;
				}
				this.getGame().recalculateItemUpgradeCost();
				break;
			// Hardware store coupon
			case GENERAL_UPGRADE_COST_DECREASE:
				switch (this.level) {
					case 1:
						this.getGame().setUpgradeCostMultiplier(0.9);
						break;
					case 2:
						this.getGame().setUpgradeCostMultiplier(0.85);
						break;
					case 3:
						this.getGame().setUpgradeCostMultiplier(0.8);
						break;
					case 4:
						this.getGame().setUpgradeCostMultiplier(0.75);
						break;
				}
				this.getGame().recalculateBusinessUpgradeCost();
				this.getGame().recalculateItemUpgradeCost();
				break;
			// Energy drink slush
			case PERMANENT_ITEM_BUFF_ONE_DAY:
				if (this.getGame().getPermanentItems()[0].getLevel() == 0 
				&& this.getGame().getPermanentItems()[1].getLevel() == 0 && this.getGame().getPermanentItems()[2].getLevel() == 0
				&& this.getGame().getPermanentItems()[3].getLevel() == 0) {
					return;
				}
				switch (this.level) {
					case 1:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() - 0.05);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() - 0.05);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() + 0.05);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() - 0.05);
						break;
					case 2:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() - 0.1);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() - 0.1);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() + 0.1);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() - 0.1);
						break;
					case 3:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() - 0.15);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() - 0.15);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() + 0.15);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() - 0.15);
						break;
				}
				this.getGame().recalculateBusinessUpgradeCost();
				this.getGame().recalculateItemUpgradeCost();
				this.getGame().calculateExpectedRevenue();
				break;
			// Hammer
			case ONE_FREE_PERMANENT_ITEM_UPGRADE:
				PermanentItem.setDeductMoney(false);
				break;
			// Temporary construction contract
			case ONE_FREE_BUSINESS_UPGRADE:
				Business.setDeductMoney(false);
				break;
			// Lucky golden coin
			case TWO_UPGRADES:
				this.getGame().setNbUpgrades(2);
				break;	
		}
	}
	
	/*
	 * Removes the buff from the player by resetting the multiplier, if
	 * the buff has not been used yet at the end of the day.
	 */
	public void removeBuff() {
		switch(this.getBuff()) {
			default:
				JOptionPane.showMessageDialog(null, "This buff cannot be removed!", "Buff not removed", JOptionPane.INFORMATION_MESSAGE);
				break;
			case BUSINESS_PURCHASE_COST_DECREASE:
				this.getGame().setBusinessPurchaseMultiplier(1.0);
				this.getGame().recalculateBusinessPurchaseCost();
				break;
			case BUSINESS_UPGRADE_COST_DECREASE:
				this.getGame().setBusinessUpgradeMultiplier(1.0);
				this.getGame().recalculateBusinessUpgradeCost();
				break;
			case REVENUE_INCREASE:
				this.getGame().setRevenueMultiplier(1.0);
				this.getGame().calculateExpectedRevenue();
				break;
			case PERMANENT_ITEM_UPGRADE_COST_DECREASE:
				this.getGame().setItemUpgradePriceMultiplier(1.0);
				this.getGame().recalculateItemUpgradeCost();
				break;
			case GENERAL_UPGRADE_COST_DECREASE:
				this.getGame().setUpgradeCostMultiplier(1.0);
				this.getGame().recalculateBusinessUpgradeCost();
				this.getGame().recalculateItemUpgradeCost();
				break;
			case PERMANENT_ITEM_BUFF_ONE_DAY:
				if (this.getGame().getPermanentItems()[0].getLevel() == 0 
				&& this.getGame().getPermanentItems()[1].getLevel() == 0 && this.getGame().getPermanentItems()[2].getLevel() == 0
				&& this.getGame().getPermanentItems()[3].getLevel() == 0) {
					return;
				}
				switch (this.level) {
					case 1:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() + 0.05);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() + 0.05);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() - 0.05);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() + 0.05);
						break;
					case 2:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() + 0.1);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() + 0.1);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() - 0.1);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() + 0.1);
						break;
					case 3:
						this.getGame().setPermItemBusinessPurchaseMultiplier(this.getGame().getPermItemBusinessPurchaseMultiplier() + 0.15);
						this.getGame().setPermItemBusinessUpgradeMultiplier(this.getGame().getPermItemBusinessUpgradeMultiplier() + 0.15);
						this.getGame().setPermItemRevenueMultiplier(this.getGame().getPermItemRevenueMultiplier() - 0.15);
						this.getGame().setPermItemUpgradeMultiplier(this.getGame().getPermItemUpgradeMultiplier() + 0.15);
						break;
				}
				this.getGame().calculateExpectedRevenue();
				this.getGame().recalculateBusinessUpgradeCost();
				this.getGame().recalculateItemUpgradeCost();
				break;
			case ONE_FREE_PERMANENT_ITEM_UPGRADE:
				PermanentItem.setDeductMoney(true);
				break;
			case ONE_FREE_BUSINESS_UPGRADE:
				Business.setDeductMoney(true);
				break;
			case TWO_UPGRADES:
				this.getGame().setNbUpgrades(1);
				break;
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean getUsed() {
		return used;
	}
	
	// Call when item is used.
	public void use() {
		this.used = true;
	}

	@Override
	public String toString() {
		if (this.level >= 1) {
			return this.getName() + ", level " + this.getLevel() + ", " + this.getBaseCost() + "$";
		}
		return this.getName() + ", " + this.getBaseCost() + "$";
	}

	/*
	 * Two temporary items are equal if they have the same name, level and buff
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == null || obj == null || !(obj instanceof TemporaryItem)) {
			return false;
		}
		return this.getName().equals(((TemporaryItem) obj).getName()) && this.getLevel() == ((TemporaryItem) obj).getLevel()
				&& this.getBuff() == ((TemporaryItem) obj).getBuff();
	}

	/*
	 * Compare all temporary items by levels, if they have one
	 * For testing purposes.
	 */
	@Override
	public int compareTo(TemporaryItem t) {
		if (!(t instanceof TemporaryItem) || !(t.getBuff() == this.getBuff()) || !(t.getName().equals(this.getName()))) {
			return 0;
		}
		if (this.level < t.level) {
			return -1;
		}
		else if (this.level > t.level) {
			return 1;
		}
		return 0;
	}
	
}
