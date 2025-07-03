import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public final class Game {
	private int days;
	private int moneyAmount;
	private int expectedRevenue;
	private int score;
	private int spentMoney;
	private int totalRevenue;
	private ArrayList<TemporaryItem> activeItems;
	private ArrayList<Business> boughtBusinesses;
	private ArrayList<TemporaryItem> itemShopItems;
	private Random random = new Random();
	
	// Business price multipliers (purchase and upgrade) for temporary items
	private double businessPurchaseMultiplier = 1.0;
	
	private double businessUpgradeMultiplier = 1.0;
	
	// Business revenue multiplier for temporary items
	private double revenueMultiplier = 1.0;
	
	// Item upgrade price multiplier of temporary items
	private double itemUpgradePriceMultiplier = 1.0;
	
	// General upgrade cost multiplier
	private double upgradeCostMultiplier = 1.0;
	
	// Number of upgrades can be 1 or 2. 
	private int nbUpgrades = 1;
	
	private int nbPermanentItems;

	/*
	 * All temporary items are stored in this list
	 */
	private TemporaryItem[] temporaryItems = {
			new TemporaryItem(this, Buff.ONE_FREE_PERMANENT_ITEM_UPGRADE, "Hammer", 30),
			new TemporaryItem(this, Buff.ONE_FREE_BUSINESS_UPGRADE, "Temporary construction contract", 50),
			new TemporaryItem(this, Buff.TWO_UPGRADES, "Lucky golden coin", 60),
			new TemporaryItem(this, Buff.BUSINESS_UPGRADE_COST_DECREASE, "Mysterious chocolate", 30, 1),
			new TemporaryItem(this, Buff.BUSINESS_UPGRADE_COST_DECREASE, "Mysterious chocolate", 40, 2),
			new TemporaryItem(this, Buff.BUSINESS_UPGRADE_COST_DECREASE, "Mysterious chocolate", 50, 3),
			new TemporaryItem(this, Buff.BUSINESS_UPGRADE_COST_DECREASE, "Mysterious chocolate", 60, 4),
			new TemporaryItem(this, Buff.BUSINESS_PURCHASE_COST_DECREASE, "Perfectly fresh pizza", 20, 1),
			new TemporaryItem(this, Buff.BUSINESS_PURCHASE_COST_DECREASE, "Perfectly fresh pizza", 30, 2),
			new TemporaryItem(this, Buff.BUSINESS_PURCHASE_COST_DECREASE, "Perfectly fresh pizza", 40, 3),
			new TemporaryItem(this, Buff.BUSINESS_PURCHASE_COST_DECREASE, "Perfectly fresh pizza", 50, 4),
			new TemporaryItem(this, Buff.GENERAL_UPGRADE_COST_DECREASE, "Hardware store coupon", 40, 1),
			new TemporaryItem(this, Buff.GENERAL_UPGRADE_COST_DECREASE, "Hardware store coupon", 50, 2),
			new TemporaryItem(this, Buff.GENERAL_UPGRADE_COST_DECREASE, "Hardware store coupon", 60, 3),
			new TemporaryItem(this, Buff.GENERAL_UPGRADE_COST_DECREASE, "Hardware store coupon", 70, 4),
			new TemporaryItem(this, Buff.REVENUE_INCREASE, "Investment plan", 30, 1),
			new TemporaryItem(this, Buff.REVENUE_INCREASE, "Investment plan", 40, 2),
			new TemporaryItem(this, Buff.REVENUE_INCREASE, "Investment plan", 50, 3),
			new TemporaryItem(this, Buff.REVENUE_INCREASE, "Investment plan", 60, 4),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_UPGRADE_COST_DECREASE, "Efficient tools renting coupon", 20, 1),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_UPGRADE_COST_DECREASE, "Efficient tools renting coupon", 30, 2),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_UPGRADE_COST_DECREASE, "Efficient tools renting coupon", 40, 3),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_UPGRADE_COST_DECREASE, "Efficient tools renting coupon", 50, 4),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_BUFF_ONE_DAY, "Energy drink slush", 10, 1),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_BUFF_ONE_DAY, "Energy drink slush", 20, 2),
			new TemporaryItem(this, Buff.PERMANENT_ITEM_BUFF_ONE_DAY, "Energy drink slush", 30, 3)
	};
	
	/*
	 * Permanent items are stored here
	 * 0 = Book of business
	 * 1 = Construction license
	 * 2 = Multi-use voucher
	 * 3 = Tool box
	 */
	private PermanentItem [] permanentItems = {
		new PermanentItem(this, "Book of business", 300, new ImageIcon("book.png")),
		new PermanentItem(this, "Construction license", 3000, new ImageIcon("license.png")),
		new PermanentItem(this, "Multi-use voucher", 30000, new ImageIcon("voucher.png")),
		new PermanentItem(this, "Tool box", 300000, new ImageIcon("toolbox.png"))
	};
	
	// Permanent item multipliers
	private double permItemRevenueMultiplier = 1.0, 
			permItemBusinessUpgradeMultiplier = 1.0, 
			permItemBusinessPurchaseMultiplier = 1.0, 
			permItemUpgradeMultiplier = 1.0;
	
	private GUI gui;
	
	/*
	 * Create a new instance of the game with the starting money amount as argument.
	 * Initialize the game with no days passed, no revenue, no money spent and an empty linked list of active items
	 * and no bought businesses. Initialize temporary items shop
	 */
	public Game(GUI gui, int moneyAmount) {
		this.gui = gui;
		this.moneyAmount = moneyAmount;
		this.days = 0;
		this.expectedRevenue = 0;
		this.spentMoney = 0;
		this.activeItems = new ArrayList<TemporaryItem>();
		this.boughtBusinesses = new ArrayList<Business>();
		this.itemShopItems = new ArrayList<TemporaryItem>();
		generateNewItemShop();
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}
	
	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public int getDays() {
		return days;
	}
	
	public GUI getGui() {
		return gui;
	}

	/*
	 * When the day ends, earn money, clear items and refresh item shop
	 * Increase cost of temporary items after each day
	 * Updates the number of days on the GUI
	 */
	public void endDay() {
		int revenue = this.calculateRevenue();
		this.days++;
		this.moneyAmount += revenue;
		this.totalRevenue += revenue;
		for (TemporaryItem t: activeItems) {
			t.removeBuff();
		}
		activeItems.clear();
		for (TemporaryItem t: temporaryItems) {
			t.setCost((int) (t.getCost() * 1.5));
		}
		this.gui.getDayNumberField().setText(Integer.toString(days));
		this.gui.getMoneyAmount().setText(Integer.toString(moneyAmount));
		this.gui.getNbrDaysTextArea().setText(Integer.toString(days));
		this.gui.getTotalMoneyEarnedTextArea().setText(Integer.toString(totalRevenue) + "$");
		generateNewItemShop();
	}
	
	// Getters for lists
	public ArrayList<TemporaryItem> getActiveItems() {
		return activeItems;
	}

	public ArrayList<Business> getBoughtBusinesses() {
		return boughtBusinesses;
	}
	
	public ArrayList<TemporaryItem> getItemShopItems() {
		return itemShopItems;
	}

	public TemporaryItem[] getTemporaryItems() {
		return temporaryItems;
	}
	
	public PermanentItem[] getPermanentItems() {
		return permanentItems;
	}

	/*
	 * Calculate the expected revenue of the day and returns it
	 * Updates the GUI with the new revenue.
	 */
	public int calculateRevenue() {
		int totRevenue = 0;
		for (Business b: boughtBusinesses) {
			totRevenue += permItemRevenueMultiplier * revenueMultiplier * b.getRevenue();
		}
		this.expectedRevenue = totRevenue;
		this.getGui().getExpectedRevenue().setText(Integer.toString(totRevenue));
		return totRevenue;
	}
	
	/*
	 * Add item to the player's active item list represented by a linked list
	 * of item objects, if the player has enough money. Otherwise show user a warning and
	 * do nothing.
	 */
	public boolean buy(Item i) {
		if (i instanceof TemporaryItem) {
			TemporaryItem boughtItem = (TemporaryItem) i;
			moneyAmount -= i.getCost();
			spentMoney += i.getCost();
			this.gui.getMoneyAmount().setText(Integer.toString(moneyAmount));
			this.gui.getTotalMoneySpentTextArea().setText(Integer.toString(spentMoney) + "$");
			this.addTemporaryItem(boughtItem);

			if (activeItems.size() == 1) {
				boughtItem.applyBuff();
				return true;
			}
			
			// Apply the strongest buff
			for (TemporaryItem t: activeItems) {
				if (t.getName().equals(boughtItem.getName()) && t.compareTo(boughtItem) == 1) {
					t.applyBuff();
				}
				else if (t.getName().equals(boughtItem.getName()) && t.compareTo(boughtItem) == -1) {
					boughtItem.applyBuff();
				}
			}
			return true;
		}
		else if (i instanceof PermanentItem) {
			PermanentItem permanentItem = ((PermanentItem) i);
			moneyAmount -= i.getCost();
			spentMoney += i.getCost();
			this.gui.getMoneyAmount().setText(Integer.toString(moneyAmount));
			this.gui.getTotalMoneySpentTextArea().setText(Integer.toString(spentMoney) + "$");
			permanentItem.setLevel(1);
			permanentItem.applyPermanentBuff(i.getName());
			gui.getPermanentItemsInventory().setValueAt(permanentItem.getIcon(), 0, nbPermanentItems);
			permanentItem.setCost((int) (permanentItem.getCost() * 1.2));
			switch (permanentItem.getName()) {
				case "Book of business":
					gui.getBookUpgradeProgressBar().setValue(permanentItem.getLevel());
					gui.getBookUpgradeProgressBar().setString("Level " + permanentItem.getLevel());
					int newRevenue = this.calculateRevenue();
					gui.getExpectedRevenue().setText(Integer.toString(newRevenue));
					break;
				case "Construction license":
					gui.getLicenseUpgradeProgressBar().setValue(permanentItem.getLevel());
					gui.getLicenseUpgradeProgressBar().setString("Level " + permanentItem.getLevel());
					break;
				case "Multi-use voucher":
					gui.getVoucherUpgradeProgressBar().setValue(permanentItem.getLevel());
					gui.getVoucherUpgradeProgressBar().setString("Level " + permanentItem.getLevel());
					break;
				case "Tool box":
					gui.getToolboxUpgradeProgressBar().setValue(permanentItem.getLevel());
					gui.getToolboxUpgradeProgressBar().setString("Level " + permanentItem.getLevel());
					break;
			}
			nbPermanentItems++;
			return true;
		} 
		return false;
	}
	
	public boolean buy(Business b) {
		/*
		 * Adds business to the list of bought businesses, if it hasn't already been bought and the player has enough money 
		 */
		if (!boughtBusinesses.contains(b) && moneyAmount >= b.getCost()) {
			boughtBusinesses.add(b);
			moneyAmount -= b.getCost();
			spentMoney += b.getCost();
			this.getGui().getMoneyAmount().setText(Integer.toString(moneyAmount));
			this.getGui().getTotalMoneySpentTextArea().setText(Integer.toString(spentMoney) + "$");
			b.setLevel(1);
			b.setCost((int) (b.getCost() * 1.3));
			this.businessPurchaseMultiplier = 1.0;
			int newRevenue = this.calculateRevenue();
			this.getGui().getExpectedRevenue().setText(Integer.toString(newRevenue));
			return true;
		}

		return false;
	}
	
	/*
	 * Adds input item to the list of active items if it isn't already there
	 */
	public void addTemporaryItem(TemporaryItem i) {
		if (!activeItems.contains(i)) {
			activeItems.add(i);
		}
	}
	
	// For testing only.
	/*
	 * Removes input item from the list of active items if it's inside the list
	 */
	public TemporaryItem removeItem(TemporaryItem i) {
		if (activeItems.size() != 0 && activeItems.contains(i)) {
			activeItems.remove(i);
			return i;
		}
		return null;
	}
	
	/*
	 * Refresh the temporary item shop with 5 new temporary items randomly selected from
	 * the list containing all temporary items
	 */
	public void generateNewItemShop() {
		itemShopItems.clear();
		this.getGui().getTemporaryItemShop().removeAll();
		HashSet<Integer> objectsIndices = new HashSet<Integer>();
		while (objectsIndices.size() < 5) {
			int objectIndex = random.nextInt(0, temporaryItems.length);
			objectsIndices.add(objectIndex);
		}
		
		for (Integer i: objectsIndices) {
			JMenuItem item = new JMenuItem(temporaryItems[i].toString());
			item.addActionListener(e -> {
				if (this.moneyAmount < temporaryItems[i].getCost()) {
					JOptionPane.showMessageDialog(null, "You cannot afford to buy this item!", "Insufficient funds",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (activeItems.contains(temporaryItems[i])) {
					JOptionPane.showMessageDialog(null, "You already own this item!", "Item already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int option = JOptionPane.showConfirmDialog(null, "Do you want to purchase this item?", "Confirm purchase",
						JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					this.buy(temporaryItems[i]);
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully purchased this item!", "Purchase successful",
						JOptionPane.INFORMATION_MESSAGE);
			});
			this.gui.getTemporaryItemShop().add(item);
			itemShopItems.add(temporaryItems[i]);
		}
		
	}
	
	public int calculateScore() {
		/*
		 * Calculates the total score with the formula 10^9 / (number of days spent * 100)
		 */
		score = (int) (Math.pow(10, 9) / (days * 100));
		return score;
	}
	
	// Calculate permanent item upgrade cost using this function
	// Helper for upgrade() method for permanent items
	public int calculateItemUpgradeCost(PermanentItem i) {
		if (i.getLevel() >= 1) {
			return (int) (i.getCost() * permItemUpgradeMultiplier * itemUpgradePriceMultiplier * upgradeCostMultiplier);
		}
		return i.getCost();
	}
	
	// Helper for upgrade() method for businesses
	public int calculateBusinessUpgradeCost(Business b) {
		if (b.getLevel() >= 1) {
			return (int) (b.getCost() * permItemBusinessUpgradeMultiplier * businessUpgradeMultiplier * upgradeCostMultiplier);
		}
		return b.getCost();
	}
	
	public int calculateBusinessPurchaseCost(Business b) {
		if (b.getLevel() > 0) {
			return b.getCost();
		}
		return (int) (b.getCost() * permItemBusinessPurchaseMultiplier * businessPurchaseMultiplier);
	}
	
	// Methods for recalculating costs from item buffs are here
	public void recalculateItemUpgradeCost() {
		for (int i=0; i<permanentItems.length; i++) {
			PermanentItem p = permanentItems[i];
			if (p.getLevel() >= 1) {
				p.setCost((int) (p.getCost() * permItemUpgradeMultiplier * itemUpgradePriceMultiplier));
			}
		}
	}
	
	public void recalculateBusinessUpgradeCost() {
		for (int j=0; j<this.getGui().getBusinessesList().size(); j++) {
			Business b = this.getGui().getBusinessesList().get(j);
			if (b.getLevel() >= 1) {
				b.setCost((int) (b.getCost() * permItemBusinessUpgradeMultiplier * businessUpgradeMultiplier * upgradeCostMultiplier));
			}
		}
	}
	
	public void recalculateBusinessPurchaseCost() {
		for (int k=0; k<this.getGui().getBusinessesList().size(); k++) {
			Business b = this.getGui().getBusinessesList().get(k);
			if (b.getLevel() == 0) {
				b.setCost((int) (b.getCost() * permItemBusinessPurchaseMultiplier * businessPurchaseMultiplier));
			}
		}
	}
	
	// Win the game
	public boolean isBillionaire() {
		return this.moneyAmount >= 1000000000;
	}
	
	public int getExpectedRevenue() {
		return expectedRevenue;
	}

	// Multipliers for temporary items
	public double getBusinessPurchaseMultiplier() {
		return businessPurchaseMultiplier;
	}

	public void setBusinessPurchaseMultiplier(double businessPurchaseMultiplier) {
		this.businessPurchaseMultiplier = businessPurchaseMultiplier;
	}
	
	public double getBusinessUpgradeMultiplier() {
		return businessUpgradeMultiplier;
	}

	public void setBusinessUpgradeMultiplier(double businessUpgradeMultiplier) {
		this.businessUpgradeMultiplier = businessUpgradeMultiplier;
	}

	public double getRevenueMultiplier() {
		return revenueMultiplier;
	}

	public void setRevenueMultiplier(double revenueMultiplier) {
		this.revenueMultiplier = revenueMultiplier;
	}
	
	public double getItemUpgradePriceMultiplier() {
		return itemUpgradePriceMultiplier;
	}

	public void setItemUpgradePriceMultiplier(double itemUpgradePriceMultiplier) {
		this.itemUpgradePriceMultiplier = itemUpgradePriceMultiplier;
	}
	
	public double getUpgradeCostMultiplier() {
		return upgradeCostMultiplier;
	}

	public void setUpgradeCostMultiplier(double upgradeCostMultiplier) {
		this.upgradeCostMultiplier = upgradeCostMultiplier;
	}

	public int getNbUpgrades() {
		return nbUpgrades;
	}
	
	public void setNbUpgrades(int nbUpgrades) {
		if (nbUpgrades == 1 || nbUpgrades == 2) {
			this.nbUpgrades = nbUpgrades;
		}
		else {
			System.out.println("Number of upgrades is invalid!");
		}
	}
	
	// Player stats
	public int getSpentMoney() {
		return spentMoney;
	}

	public int getTotalRevenue() {
		return totalRevenue;
	}
	
	public void setSpentMoney(int spentMoney) {
		this.spentMoney = spentMoney;
	}

	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	// Permanent items multipliers
	public double getPermItemRevenueMultiplier() {
		return permItemRevenueMultiplier;
	}

	public void setPermItemRevenueMultiplier(double permItemRevenueMultiplier) {
		this.permItemRevenueMultiplier = permItemRevenueMultiplier;
	}

	public double getPermItemBusinessUpgradeMultiplier() {
		return permItemBusinessUpgradeMultiplier;
	}

	public void setPermItemBusinessUpgradeMultiplier(double permItemBusinessUpgradeMultiplier) {
		this.permItemBusinessUpgradeMultiplier = permItemBusinessUpgradeMultiplier;
	}

	public double getPermItemBusinessPurchaseMultiplier() {
		return permItemBusinessPurchaseMultiplier;
	}

	public void setPermItemBusinessPurchaseMultiplier(double permItemBusinessPurchaseMultiplier) {
		this.permItemBusinessPurchaseMultiplier = permItemBusinessPurchaseMultiplier;
	}

	public double getPermItemUpgradeMultiplier() {
		return permItemUpgradeMultiplier;
	}

	public void setPermItemUpgradeMultiplier(double permItemUpgradeMultiplier) {
		this.permItemUpgradeMultiplier = permItemUpgradeMultiplier;
	}
	
}
