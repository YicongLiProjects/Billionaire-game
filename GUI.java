import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GUI {

	private JFrame frmBillionaire;
	private JPanel mainMenuPanel;
	private JLabel gameNameLabel;
	private JButton playButton;
	private JPanel gamePanel;
	private JMenuBar menuBar;
	private JMenuItem helpMenuItem;
	private JMenuItem statsMenuItem;
	private JMenu temporaryItemShop = new JMenu("Item shop");;
	private JMenu permanentItemsShopMenu;
	private JButton buyButton;
	private JButton upgradeButton;
	private JButton infoButton;
	private JButton activeItemsButton;
	private JButton endDayButton;
	private JTextField moneyAmount;
	private JLabel moneySign;
	private JTextField expectedRevenue;
	private JLabel expectedRevenueLabel;
	private JTable permanentItemsInventory;
	private JLabel permanentItemsLabel;
	private JMenuBar menuBarMainMenu;
	private JMenuItem helpButton;
	private JMenuItem creditsButton;
	private JTextArea dayNumberField;
	private JLabel daysPassed;
	private JPanel gameOverPanel;
	private JLabel gameOverText;
	private JButton quitButton;
	private JButton gameCreditsButton;
	private JLabel scoreLabel;
	private JTextField scoreField;
	
	/*
	 * The player starts the game with 30$
	 */
	private Game game = new Game(this, 30);
	
	// Track the previous tab for the help screen
	private String previousTabHelpScreen;
	private String previousTabCreditsScreen;
	
	private JMenuItem itemUpgradeBtn;
	private JPanel upgradePanel;
	private JLabel bookLabel;
	private JLabel constructionLabel;
	private JLabel voucherLabel;
	private JLabel toolboxLabel;
	private JButton bookUpgradeBtn;
	private JButton licenseUpgradeBtn;
	private JButton voucherUpgradeBtn;
	private JButton toolboxUpgradeBtn;
	private JProgressBar bookUpgradeProgressBar;
	private JProgressBar licenseUpgradeProgressBar;
	private JProgressBar voucherUpgradeProgressBar;
	private JProgressBar toolboxUpgradeProgressBar;
	private JLabel lvlLabel;
	private JButton backToGameBtn;
	private JPanel helpPanel;
	private JLabel helpTitle;
	private JButton backBtn;
	private JScrollPane helpContent;
	private JPanel statsPanel;
	private JLabel daysSpentLbl;
	private JTextArea nbrDaysTextArea;
	private JLabel playerStatsTitle;
	private JLabel totalMoneySpentLbl;
	private JTextArea totalMoneySpentTextArea;
	private JLabel totalMoneyEarnedLbl;
	private JTextArea totalMoneyEarnedTextArea;
	private JButton backToGameBtnStatsMenu;
	private JPanel creditsPanel;
	private JLabel gameCreditsTitle;
	private JTextArea gameCreditsText;
	private JButton menuBtn;
	private JScrollPane businessesSection;
	private JList<Business> businesses;
	private JMenuItem bookOfBusinessEntry;
	private JMenuItem constructionLicenseEntry;
	private JMenuItem multiUseVoucherEntry;
	private JMenuItem toolboxEntry;
	
	DefaultListModel<Business> businessesList = new DefaultListModel<Business>();
	private JLabel moneySignLabel;
	private JTextArea moneyAmountUpgradePanel;
	private JButton priceBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBillionaire.pack();
					window.frmBillionaire.setSize(450, 300);
					window.frmBillionaire.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBillionaire = new JFrame();
		frmBillionaire.setTitle("Billionaire!");
		frmBillionaire.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/money.jpg")));
		frmBillionaire.setBounds(100, 100, 450, 300);
		frmBillionaire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cardLayout = new CardLayout(0, 0);
		frmBillionaire.getContentPane().setLayout(cardLayout);
		
		mainMenuPanel = new JPanel();
		frmBillionaire.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		mainMenuPanel.setLayout(null);
		
		gameNameLabel = new JLabel("Billionaire!");
		gameNameLabel.setIcon(new ImageIcon(getClass().getResource("/images/dollar-symbol.jpg")));
		gameNameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		gameNameLabel.setBounds(117, 35, 196, 55);
		mainMenuPanel.add(gameNameLabel);
		
		playButton = new JButton("Play");
		playButton.setFocusable(false);
		playButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// The card layout is used to switch between the main menu and the game
				previousTabHelpScreen = "mainMenuPanel";
				cardLayout.show(frmBillionaire.getContentPane(), "gamePanel");
			}
		});
		playButton.setBounds(128, 123, 176, 64);
		mainMenuPanel.add(playButton);
		
		menuBarMainMenu = new JMenuBar();
		menuBarMainMenu.setBounds(0, 0, 440, 22);
		mainMenuPanel.add(menuBarMainMenu);
		
		helpButton = new JMenuItem("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabHelpScreen = "mainMenuPanel";
				cardLayout.show(frmBillionaire.getContentPane(), "helpPanel");
			}
		});
		menuBarMainMenu.add(helpButton);
		
		creditsButton = new JMenuItem("Credits");
		creditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabCreditsScreen = "mainMenuPanel";
				cardLayout.show(frmBillionaire.getContentPane(), "creditsPanel");
			}
		});
		menuBarMainMenu.add(creditsButton);
		
		gamePanel = new JPanel();
		frmBillionaire.getContentPane().add(gamePanel, "gamePanel");
		gamePanel.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 440, 22);
		gamePanel.add(menuBar);
		
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabHelpScreen = "gamePanel";
				cardLayout.show(frmBillionaire.getContentPane(), "helpPanel");
			}
		});
		menuBar.add(helpMenuItem);
		
		statsMenuItem = new JMenuItem("Stats");
		statsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frmBillionaire.getContentPane(), "statsPanel");
			}
		});
		menuBar.add(statsMenuItem);
		
		itemUpgradeBtn = new JMenuItem("Upgrade item");
		itemUpgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabHelpScreen = "upgradePanel";
				cardLayout.show(frmBillionaire.getContentPane(), "upgradePanel");
				moneyAmountUpgradePanel.setText(Integer.toString(game.getMoneyAmount()));
			}
		});
		menuBar.add(itemUpgradeBtn);
		
		permanentItemsShopMenu = new JMenu("Permanent items shop");
		menuBar.add(permanentItemsShopMenu);
		
		bookOfBusinessEntry = new JMenuItem(game.getPermanentItems()[0].toString());
		bookOfBusinessEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PermanentItem book = game.getPermanentItems()[0];
				if (game.getMoneyAmount() < book.getBaseCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to purchase this item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (book.getLevel() >= 1) {
					JOptionPane.showMessageDialog(null, "You already own this item!", "Item already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (book.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmPurchase = JOptionPane.showConfirmDialog(null, "Do you want to buy this permanent item?",
						"Confirm purchase", JOptionPane.YES_NO_OPTION);
				
				if (confirmPurchase == 0) {
					game.buy(book);
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully purchased the book of business! Enjoy increased revenue!", 
						"Purchase successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		permanentItemsShopMenu.add(bookOfBusinessEntry);
		
		constructionLicenseEntry = new JMenuItem(game.getPermanentItems()[1].toString());
		constructionLicenseEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem license = game.getPermanentItems()[1];
				if (game.getMoneyAmount() < license.getBaseCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to purchase this item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (license.getLevel() >= 1) {
					JOptionPane.showMessageDialog(null, "You already own this item!", "Item already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (license.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmPurchase = JOptionPane.showConfirmDialog(null, "Do you want to buy this permanent item?",
						"Confirm purchase", JOptionPane.YES_NO_OPTION);
				
				if (confirmPurchase == 0) {
					game.buy(license);
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully purchased the construction license!"
						+ " Enjoy reduced fees for upgrading businesses!", "Purchase successful", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		permanentItemsShopMenu.add(constructionLicenseEntry);
		
		multiUseVoucherEntry = new JMenuItem(game.getPermanentItems()[2].toString());
		multiUseVoucherEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem voucher = game.getPermanentItems()[2];
				if (game.getMoneyAmount() < voucher.getBaseCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to purchase this item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (voucher.getLevel() >= 1) {
					JOptionPane.showMessageDialog(null, "You already own this item!", "Item already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (voucher.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmPurchase = JOptionPane.showConfirmDialog(null, "Do you want to buy this permanent item?",
						"Confirm purchase", JOptionPane.YES_NO_OPTION);
				
				if (confirmPurchase == 0) {
					game.buy(voucher);
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully purchased a multi-use voucher! "
						+ "Enjoy buying businesses with a discount forever!", 
						"Purchase successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		permanentItemsShopMenu.add(multiUseVoucherEntry);
		
		toolboxEntry = new JMenuItem(game.getPermanentItems()[3].toString());
		toolboxEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem toolbox = game.getPermanentItems()[3];
				if (game.getMoneyAmount() < toolbox.getBaseCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to purchase this item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (toolbox.getLevel() >= 1) {
					JOptionPane.showMessageDialog(null, "You already own this item!", "Item already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (toolbox.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmPurchase = JOptionPane.showConfirmDialog(null, "Do you want to buy this permanent item?",
						"Confirm purchase", JOptionPane.YES_NO_OPTION);
				
				if (confirmPurchase == 0) {
					game.buy(toolbox);
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully purchased a mysterious toolbox! "
						+ "Enjoy upgrading your items with reduced cost!", 
						"Purchase successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		permanentItemsShopMenu.add(toolboxEntry);
		
		menuBar.add(temporaryItemShop);
		
		buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (businesses.getSelectedValue() == null) {
					return;
				}
				
				if (game.getBoughtBusinesses().contains(businesses.getSelectedValue())) {
					JOptionPane.showMessageDialog(null, "You already own this business!", "Business already owned",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (!game.getBoughtBusinesses().contains(businesses.getSelectedValue()) && 
						game.getMoneyAmount() < businesses.getSelectedValue().getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to buy this business!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int buyConfirmation = JOptionPane.showConfirmDialog(null, "Do you want to buy this business?", "Buy business",
						JOptionPane.YES_NO_OPTION);
				
				if (buyConfirmation == 0) {
					game.buy(businesses.getSelectedValue());
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You purchased this business successfully", "Business purchased",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buyButton.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		buyButton.setBounds(313, 49, 108, 23);
		buyButton.setFocusable(false);
		gamePanel.add(buyButton);
		
		upgradeButton = new JButton("Upgrade");
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (businesses.getSelectedValue() == null) {
					return;
				}
				
				if (!game.getBoughtBusinesses().contains(businesses.getSelectedValue())) {
					JOptionPane.showMessageDialog(null, "You don't own this business yet! Please buy it first", 
							"Business not yet owned", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (game.getBoughtBusinesses().contains(businesses.getSelectedValue()) && 
						game.getMoneyAmount() < businesses.getSelectedValue().getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade this business!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (businesses.getSelectedValue().getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this business!", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmUpgrade = JOptionPane.showConfirmDialog(null, "Do you want to upgrade this business?", 
						"Upgrade business", JOptionPane.INFORMATION_MESSAGE);
				if (!Business.isDeductMoney()) {
					JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
				}
				if (confirmUpgrade == 0) {
					businesses.getSelectedValue().upgrade();
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You upgraded this business successfully", "Business upgraded",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		upgradeButton.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		upgradeButton.setBounds(313, 83, 108, 23);
		upgradeButton.setFocusable(false);
		gamePanel.add(upgradeButton);
		
		infoButton = new JButton("Information");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Business selectedBusiness = businesses.getSelectedValue();
				if (selectedBusiness == null) {
					return;
				}
				else if (!game.getBoughtBusinesses().contains(selectedBusiness)) {
					JOptionPane.showMessageDialog(null, "Name: " + selectedBusiness.getName() + "\nPrice: " + 
				selectedBusiness.getCost() + "$", "Business information", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, "Name: " + selectedBusiness.getName() + "\nLevel: " + 
				selectedBusiness.getLevel() + "\nRevenue generated: " + selectedBusiness.getRevenue() + "$" +
						"\nUpgrade cost: " + selectedBusiness.getCost() + "$", "Business information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		infoButton.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		infoButton.setBounds(313, 117, 108, 23);
		infoButton.setFocusable(false);
		gamePanel.add(infoButton);
		
		activeItemsButton = new JButton("Active items");
		activeItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String info = "";
				for (TemporaryItem t: game.getActiveItems()) {
					info += (t.toString() + "\nBuff: " + t.getBuff() + "\n");
				}
				if (info.equals("")) {
					info = "No active items";
				}
				JOptionPane.showMessageDialog(null, info, "All active items", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		activeItemsButton.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		activeItemsButton.setBounds(313, 151, 108, 23);
		activeItemsButton.setFocusable(false);
		gamePanel.add(activeItemsButton);
		
		endDayButton = new JButton("End day");
		endDayButton.setBounds(313, 185, 108, 32);
		endDayButton.setFocusable(false);
		endDayButton.setFont(new Font("Dubai", Font.BOLD, 15));
		endDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You earned " + game.getExpectedRevenue() + "$ this day" +
				"\nYou're on day " + Integer.toString(game.getDays() + 1), "Day ended", JOptionPane.INFORMATION_MESSAGE);
				game.endDay();
				if (game.isBillionaire()) {
					cardLayout.show(frmBillionaire.getContentPane(), "gameOverPanel");
					scoreField.setText(Integer.toString(game.calculateScore()));
				}
			}
		});
		gamePanel.add(endDayButton);
		
		moneyAmount = new JTextField();
		moneyAmount.setBounds(10, 26, 96, 20);
		moneyAmount.setFocusable(false);
		moneyAmount.setEditable(false);
		moneyAmount.setText(Integer.toString(game.getMoneyAmount()));
		gamePanel.add(moneyAmount);
		moneyAmount.setColumns(10);
		
		moneySign = new JLabel("$");
		moneySign.setFont(new Font("Elephant", Font.PLAIN, 11));
		moneySign.setBounds(112, 26, 18, 20);
		moneySign.setHorizontalAlignment(SwingConstants.CENTER);
		gamePanel.add(moneySign);
		
		expectedRevenue = new JTextField();
		expectedRevenue.setBounds(136, 26, 96, 20);
		expectedRevenue.setFocusable(false);
		expectedRevenue.setEditable(false);
		expectedRevenue.setText(Integer.toString(game.calculateExpectedRevenue()));
		gamePanel.add(expectedRevenue);
		expectedRevenue.setColumns(10);
		
		expectedRevenueLabel = new JLabel("Expected revenue");
		expectedRevenueLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		expectedRevenueLabel.setBounds(242, 26, 108, 20);
		gamePanel.add(expectedRevenueLabel);
		
		/*
		 * Make each cell non-editable, we want to add the icon of the item
		 * to the JTable each time we buy a permanent item, and we don't want
		 * the user to be able to add something to the JTable. Override the 
		 * getColumnClass method to render the icon of the permanent item.
		 * This syntax is required to override methods. 
		 */
		permanentItemsInventory = new JTable(1, 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			@Override
			public Class<?> getColumnClass(int column) {
				return ImageIcon.class;
			}
		};
		permanentItemsInventory.setBounds(10, 235, 80, 20);
		gamePanel.add(permanentItemsInventory);
		
		permanentItemsLabel = new JLabel("Permanent items");
		permanentItemsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		permanentItemsLabel.setBounds(100, 235, 97, 20);
		gamePanel.add(permanentItemsLabel);
		
		dayNumberField = new JTextArea();
		dayNumberField.setBounds(280, 235, 40, 22);
		dayNumberField.setToolTipText("Number of days passed in game");
		dayNumberField.setBackground(Color.WHITE);
		dayNumberField.setFocusable(false);
		dayNumberField.setEditable(false);
		dayNumberField.setText(Integer.toString(game.getDays()));
		dayNumberField.setBorder(new LineBorder(Color.black));
		gamePanel.add(dayNumberField);
		
		daysPassed = new JLabel("Days Passed");
		daysPassed.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		daysPassed.setBounds(325, 235, 98, 20);
		daysPassed.setIcon(new ImageIcon(getClass().getResource("/images/sun.jpg")));
		gamePanel.add(daysPassed);
		
		businessesSection = new JScrollPane();
		businessesSection.setBounds(10, 52, 230, 175);
		gamePanel.add(businessesSection);
		
		/*
		 * List of all businesses
		 * Add the business objects to the DefaultListModel object
		 */
		businessesList.add(0, new Business(game, "Dollar store", 10));
		businessesList.add(1, new Business(game, "Arcade", 50));
		businessesList.add(2, new Business(game, "Supermarket", 100));
		businessesList.add(3, new Business(game, "High-end restaurant", 500));
		businessesList.add(4, new Business(game, "Fast food chain", 1000));
		businessesList.add(5, new Business(game, "Electronics store", 5000));
		businessesList.add(6, new Business(game, "Luxury items store", 10000));
		businessesList.add(7, new Business(game, "Car dealership", 50000));
		businessesList.add(8, new Business(game, "Shopping mall", 100000));
		businessesList.add(9, new Business(game, "Construction firm", 500000));
		businessesList.add(10, new Business(game, "Casino", 1000000));
		businessesList.add(11, new Business(game, "Bank", 5000000));
		businessesList.add(12, new Business(game, "Tech company", 10000000));
		businesses = new JList<Business>(businessesList);
		businessesSection.setViewportView(businesses);
		
		
		/*
		 * Trigger this when the player has 1 billion dollars (not counting spent money)
		 */
		gameOverPanel = new JPanel();
		frmBillionaire.getContentPane().add(gameOverPanel, "gameOverPanel");
		gameOverPanel.setLayout(null);
		
		gameOverText = new JLabel("You're a billionaire!");
		gameOverText.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		gameOverText.setBounds(73, 11, 297, 43);
		gameOverPanel.add(gameOverText);
		
		quitButton = new JButton("Quit Game");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		quitButton.setBounds(125, 179, 159, 37);
		gameOverPanel.add(quitButton);
		
		gameCreditsButton = new JButton("Credits");
		gameCreditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabCreditsScreen = "gameOverPanel";
				cardLayout.show(frmBillionaire.getContentPane(), "creditsPanel");
			}
		});
		gameCreditsButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		gameCreditsButton.setBounds(125, 121, 159, 37);
		gameOverPanel.add(gameCreditsButton);
		
		scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("Harrington", Font.PLAIN, 15));
		scoreLabel.setBounds(125, 65, 50, 30);
		gameOverPanel.add(scoreLabel);
		
		scoreField = new JTextField();
		scoreField.setEditable(false);
		scoreField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		scoreField.setBounds(185, 65, 96, 31);
		scoreField.setFocusable(false);
		gameOverPanel.add(scoreField);
		scoreField.setColumns(10);
		
		upgradePanel = new JPanel();
		frmBillionaire.getContentPane().add(upgradePanel, "upgradePanel");
		upgradePanel.setLayout(null);
		
		bookLabel = new JLabel("Book of business");
		bookLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		bookLabel.setBounds(47, 37, 135, 31);
		upgradePanel.add(bookLabel);
		
		constructionLabel = new JLabel("Construction license");
		constructionLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		constructionLabel.setBounds(47, 80, 149, 31);
		upgradePanel.add(constructionLabel);
		
		voucherLabel = new JLabel("Multi-use voucher");
		voucherLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		voucherLabel.setBounds(47, 122, 149, 31);
		upgradePanel.add(voucherLabel);
		
		toolboxLabel = new JLabel("Toolbox");
		toolboxLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		toolboxLabel.setBounds(47, 164, 84, 31);
		upgradePanel.add(toolboxLabel);
		
		bookUpgradeBtn = new JButton("Upgrade");
		bookUpgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem book = game.getPermanentItems()[0];
				if (book.getLevel() == 0) {
					JOptionPane.showMessageDialog(null, "You must purchase this item first!", "Item not yet purchased",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (game.getMoneyAmount() < book.getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade the item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (book.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item!", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmUpgrade = JOptionPane.showConfirmDialog(null, "Do you want to upgrade the book of business?",
						"Confirm upgrade", JOptionPane.YES_NO_OPTION);
				
				if (!PermanentItem.isDeductMoney()) {
					JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
				}
				if (confirmUpgrade == 0) {
					book.upgrade();
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully upgraded the book of business!",
						"Upgrade successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		bookUpgradeBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		bookUpgradeBtn.setBounds(216, 43, 89, 23);
		upgradePanel.add(bookUpgradeBtn);
		
		licenseUpgradeBtn = new JButton("Upgrade");
		licenseUpgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem license = game.getPermanentItems()[1];
				if (license.getLevel() == 0) {
					JOptionPane.showMessageDialog(null, "You must purchase this item first!", "Item not yet purchased",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (game.getMoneyAmount() < license.getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade the item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (license.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item!", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmUpgrade = JOptionPane.showConfirmDialog(null, "Do you want to upgrade the construction license?",
						"Confirm upgrade", JOptionPane.YES_NO_OPTION);
				
				if (!PermanentItem.isDeductMoney()) {
					JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (confirmUpgrade == 0) {
					license.upgrade();
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully upgraded the construction license!",
						"Upgrade successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		licenseUpgradeBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		licenseUpgradeBtn.setBounds(216, 86, 89, 23);
		upgradePanel.add(licenseUpgradeBtn);
		
		voucherUpgradeBtn = new JButton("Upgrade");
		voucherUpgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem voucher = game.getPermanentItems()[2];
				if (voucher.getLevel() == 0) {
					JOptionPane.showMessageDialog(null, "You must purchase this item first!", "Item not yet purchased",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (game.getMoneyAmount() < voucher.getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade the item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (voucher.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item!", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmUpgrade = JOptionPane.showConfirmDialog(null, "Do you want to upgrade the multi-use voucher?",
						"Confirm upgrade", JOptionPane.YES_NO_OPTION);
				
				if (!PermanentItem.isDeductMoney()) {
					JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (confirmUpgrade == 0) {
					voucher.upgrade();
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully upgraded the multi-use voucher!",
						"Upgrade successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		voucherUpgradeBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		voucherUpgradeBtn.setBounds(216, 127, 89, 23);
		upgradePanel.add(voucherUpgradeBtn);
		
		toolboxUpgradeBtn = new JButton("Upgrade");
		toolboxUpgradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PermanentItem toolbox = game.getPermanentItems()[3];
				if (toolbox.getLevel() == 0) {
					JOptionPane.showMessageDialog(null, "You must purchase this item first!", "Item not yet purchased",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (game.getMoneyAmount() < toolbox.getCost()) {
					JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade the item!",
							"Insufficient funds", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if (toolbox.getLevel() == 10) {
					JOptionPane.showMessageDialog(null, "You already maxed out this item!", "Max level reached",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int confirmUpgrade = JOptionPane.showConfirmDialog(null, "Do you want to upgrade the tool box?",
						"Confirm upgrade", JOptionPane.YES_NO_OPTION);
				
				if (!PermanentItem.isDeductMoney()) {
					JOptionPane.showMessageDialog(null, "This upgrade is free!", "Free upgrade", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (confirmUpgrade == 0) {
					toolbox.upgrade();
				}
				else {
					return;
				}
				JOptionPane.showMessageDialog(null, "You successfully upgraded the tool box!",
						"Upgrade successful", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		toolboxUpgradeBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		toolboxUpgradeBtn.setBounds(158, 169, 89, 23);
		upgradePanel.add(toolboxUpgradeBtn);
		
		bookUpgradeProgressBar = new JProgressBar();
		bookUpgradeProgressBar.setForeground(Color.LIGHT_GRAY);
		bookUpgradeProgressBar.setStringPainted(true);
		bookUpgradeProgressBar.setMaximum(10);
		bookUpgradeProgressBar.setBounds(315, 46, 113, 23);
		bookUpgradeProgressBar.setString("Not yet purchased");
		upgradePanel.add(bookUpgradeProgressBar);
		
		licenseUpgradeProgressBar = new JProgressBar();
		licenseUpgradeProgressBar.setForeground(Color.LIGHT_GRAY);
		licenseUpgradeProgressBar.setStringPainted(true);
		licenseUpgradeProgressBar.setMaximum(10);
		licenseUpgradeProgressBar.setBounds(315, 88, 113, 23);
		licenseUpgradeProgressBar.setString("Not yet purchased");
		upgradePanel.add(licenseUpgradeProgressBar);
		
		voucherUpgradeProgressBar = new JProgressBar();
		voucherUpgradeProgressBar.setForeground(Color.LIGHT_GRAY);
		voucherUpgradeProgressBar.setStringPainted(true);
		voucherUpgradeProgressBar.setMaximum(10);
		voucherUpgradeProgressBar.setBounds(315, 130, 113, 23);
		voucherUpgradeProgressBar.setString("Not yet purchased");
		upgradePanel.add(voucherUpgradeProgressBar);
		
		toolboxUpgradeProgressBar = new JProgressBar();
		toolboxUpgradeProgressBar.setForeground(Color.LIGHT_GRAY);
		toolboxUpgradeProgressBar.setStringPainted(true);
		toolboxUpgradeProgressBar.setMaximum(10);
		toolboxUpgradeProgressBar.setBounds(315, 172, 113, 23);
		toolboxUpgradeProgressBar.setString("Not yet purchased");
		upgradePanel.add(toolboxUpgradeProgressBar);
		
		lvlLabel = new JLabel("Level");
		lvlLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lvlLabel.setBounds(348, 11, 52, 24);
		upgradePanel.add(lvlLabel);
		
		backToGameBtn = new JButton("Back to game");
		backToGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousTabHelpScreen = "gamePanel";
				cardLayout.show(frmBillionaire.getContentPane(), "gamePanel");
				// Sync with the game
				moneyAmountUpgradePanel.setText(Integer.toString(game.getMoneyAmount()));
			}
		});
		backToGameBtn.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backToGameBtn.setBounds(64, 211, 142, 43);
		upgradePanel.add(backToGameBtn);
		
		moneySignLabel = new JLabel("$");
		moneySignLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		moneySignLabel.setBounds(401, 217, 15, 28);
		upgradePanel.add(moneySignLabel);
		
		moneyAmountUpgradePanel = new JTextArea();
		moneyAmountUpgradePanel.setEditable(false);
		moneyAmountUpgradePanel.setText(Integer.toString(game.getMoneyAmount()));
		moneyAmountUpgradePanel.setBounds(290, 221, 94, 23);
		moneyAmountUpgradePanel.setBorder(new LineBorder(Color.black));
		moneyAmountUpgradePanel.setFocusable(false);
		upgradePanel.add(moneyAmountUpgradePanel);
		
		priceBtn = new JButton("Upgrade prices");
		priceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Book of business: " + game.getPermanentItems()[0].getCost() + 
						"$\nConstruction license: " + game.getPermanentItems()[1].getCost() + "$\nMulti-use voucher: " +
						game.getPermanentItems()[2].getCost() + "$\nTool box: " + game.getPermanentItems()[3].getCost() + "$");
			}
		});
		priceBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		priceBtn.setBounds(22, 8, 135, 23);
		upgradePanel.add(priceBtn);
		
		helpPanel = new JPanel();
		frmBillionaire.getContentPane().add(helpPanel, "helpPanel");
		helpPanel.setLayout(null);
		
		helpTitle = new JLabel("Billionaire! Game help");
		helpTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		helpTitle.setBounds(10, 11, 161, 35);
		helpPanel.add(helpTitle);
		
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (previousTabHelpScreen.equals("gamePanel")) {
					cardLayout.show(frmBillionaire.getContentPane(), "gamePanel");
				}
				else if (previousTabHelpScreen.equals("mainMenuPanel")) {
					cardLayout.show(frmBillionaire.getContentPane(), "mainMenuPanel");
				}
			}
		});
		backBtn.setBounds(339, 19, 89, 23);
		helpPanel.add(backBtn);
		
		JTextPane helpText = new JTextPane();
		helpText.setFont(new Font("Papyrus", Font.PLAIN, 11));
		helpText.setFocusable(false);
		helpText.setEditable(false);
		helpText.setText("Billionaire! is a simple game where the player manage businesses to earn money."
				+ " The goal is to earn 1B $ of in-game money via purchasing and upgrading businesses (10 levels in total) in"
				+ " the fewest days possible.\nThere are also permanent items (also 10 levels in total) and temporary"
				+ " items that the player can purchase to make progressing much easier. Permanent items can be upgraded,"
				+ " and temporary items come in various levels. Each have their own item shop, with the latter's refreshing"
				+ " daily. Temporary items can only be used once per purchase, and if it is not used before the day ends"
				+ " it will be removed from the player's inventory and can no longer be used. They come in 1, 3 or 4 levels."
				+ " To upgrade permanent items, go to the 'upgrade' tab on the menu bar of the game.\n There's also an entry"
				+ " for displaying player stats on the menu bar, as well as the two items shop there. 5 actions"
				+ " are available to the player, to the right of the business list: buy, upgrade, display infomration,"
				+ " see active items (temporary) and end the day. Below that list is the inventory for 4 permanent items,"
				+ " with each having their own icon displayed on each cell.\nAbove that list, the amount of money the"
				+ " player currently has and the expected revenue, equal to the amount that will be added to the player's"
				+ " balance at the end of the day can both be found.");
		helpContent = new JScrollPane(helpText);
		helpContent.setBounds(10, 44, 310, 210);
		helpPanel.add(helpContent);
		
		statsPanel = new JPanel();
		frmBillionaire.getContentPane().add(statsPanel, "statsPanel");
		statsPanel.setLayout(null);
		
		daysSpentLbl = new JLabel("Days spent");
		daysSpentLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		daysSpentLbl.setBounds(29, 70, 74, 23);
		statsPanel.add(daysSpentLbl);
		
		nbrDaysTextArea = new JTextArea();
		nbrDaysTextArea.setText(game.getDays() + " days");
		nbrDaysTextArea.setEditable(false);
		nbrDaysTextArea.setBackground(Color.WHITE);
		nbrDaysTextArea.setBounds(113, 70, 60, 23);
		nbrDaysTextArea.setFocusable(false);
		nbrDaysTextArea.setBorder(new LineBorder(Color.black));
		statsPanel.add(nbrDaysTextArea);
		
		playerStatsTitle = new JLabel("Player stats");
		playerStatsTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		playerStatsTitle.setBounds(170, 22, 123, 27);
		statsPanel.add(playerStatsTitle);
		
		totalMoneySpentLbl = new JLabel("Total money spent");
		totalMoneySpentLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		totalMoneySpentLbl.setBounds(29, 120, 111, 23);
		statsPanel.add(totalMoneySpentLbl);
		
		totalMoneySpentTextArea = new JTextArea();
		totalMoneySpentTextArea.setText(game.getSpentMoney() + "$");
		totalMoneySpentTextArea.setEditable(false);
		totalMoneySpentTextArea.setBackground(Color.WHITE);
		totalMoneySpentTextArea.setBounds(150, 120, 101, 23);
		totalMoneySpentTextArea.setFocusable(false);
		totalMoneySpentTextArea.setBorder(new LineBorder(Color.black));
		statsPanel.add(totalMoneySpentTextArea);
		
		totalMoneyEarnedLbl = new JLabel("Total money earned");
		totalMoneyEarnedLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		totalMoneyEarnedLbl.setBounds(29, 172, 115, 23);
		statsPanel.add(totalMoneyEarnedLbl);
		
		totalMoneyEarnedTextArea = new JTextArea();
		totalMoneyEarnedTextArea.setText(game.getTotalRevenue() + "$");
		totalMoneyEarnedTextArea.setEditable(false);
		totalMoneyEarnedTextArea.setBackground(Color.WHITE);
		totalMoneyEarnedTextArea.setBounds(155, 172, 96, 23);
		totalMoneyEarnedTextArea.setFocusable(false);
		totalMoneyEarnedTextArea.setBorder(new LineBorder(Color.black));
		statsPanel.add(totalMoneyEarnedTextArea);
		
		backToGameBtnStatsMenu = new JButton("Back to game");
		backToGameBtnStatsMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(frmBillionaire.getContentPane(), "gamePanel");
			}
		});
		backToGameBtnStatsMenu.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backToGameBtnStatsMenu.setBounds(257, 212, 150, 42);
		statsPanel.add(backToGameBtnStatsMenu);
		
		creditsPanel = new JPanel();
		frmBillionaire.getContentPane().add(creditsPanel, "creditsPanel");
		creditsPanel.setLayout(null);
		
		gameCreditsTitle = new JLabel("Billlionaire! Game credits");
		gameCreditsTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		gameCreditsTitle.setBounds(88, 20, 251, 37);
		creditsPanel.add(gameCreditsTitle);
		
		gameCreditsText = new JTextArea();
		gameCreditsText.setFocusable(false);
		gameCreditsText.setEditable(false);
		gameCreditsText.setFont(new Font("Papyrus", Font.PLAIN, 12));
		gameCreditsText.setText("Creator: Li Yicong" + "\nGame images download link: flaticon.com"
		+ "\nEverything is made by the game's creator \nfor a first coding project in Java.");
		gameCreditsText.setBounds(57, 66, 244, 121);
		creditsPanel.add(gameCreditsText);
		
		menuBtn = new JButton("Go back");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (previousTabCreditsScreen.equals("mainMenuPanel")) {
					cardLayout.show(frmBillionaire.getContentPane(), "mainMenuPanel");
				}
				else if (previousTabCreditsScreen.equals("gameOverPanel")) {
					cardLayout.show(frmBillionaire.getContentPane(), "gameOverPanel");
				}
			}
		});
		menuBtn.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		menuBtn.setBounds(232, 208, 151, 30);
		creditsPanel.add(menuBtn);
	}
	
	// Reset game after win
	public void resetGame() {
		game = new Game(this, 30);
	}
	
	// Getters

	public JTable getPermanentItemsInventory() {
		return permanentItemsInventory;
	}

	public JMenu getTemporaryItemShop() {
		return temporaryItemShop;
	}

	public JTextField getMoneyAmount() {
		return moneyAmount;
	}

	public JTextField getExpectedRevenue() {
		return expectedRevenue;
	}

	public JProgressBar getBookUpgradeProgressBar() {
		return bookUpgradeProgressBar;
	}

	public JProgressBar getLicenseUpgradeProgressBar() {
		return licenseUpgradeProgressBar;
	}

	public JProgressBar getVoucherUpgradeProgressBar() {
		return voucherUpgradeProgressBar;
	}

	public JProgressBar getToolboxUpgradeProgressBar() {
		return toolboxUpgradeProgressBar;
	}

	public JTextArea getDayNumberField() {
		return dayNumberField;
	}

	public JTextArea getNbrDaysTextArea() {
		return nbrDaysTextArea;
	}

	public JTextArea getTotalMoneySpentTextArea() {
		return totalMoneySpentTextArea;
	}

	public JTextArea getTotalMoneyEarnedTextArea() {
		return totalMoneyEarnedTextArea;
	}
	
	public DefaultListModel<Business> getBusinessesList() {
		return businessesList;
	}

	public JTextArea getMoneyAmountUpgradePanel() {
		return moneyAmountUpgradePanel;
	}
}
