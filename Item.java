
public abstract class Item {
	private Buff buff;
	private String name;
	private int baseCost;
	
	private Game game;
	/*
	 * Template for permanent items
	 * It will interface with the game, and it will have a name and a base cost (cost before multipliers are applied)
	 */
	public Item(Game game, String name, int baseCost) {
		this.game = game;
		this.name = name;
		this.baseCost = baseCost;
	}
	
	/*
	 * Template for temporary items, similar to the template for permanent items.
	 * However, it will contain an enum that specifies the buff of this item
	 */
	public Item(Game game, Buff buff, String name, int baseCost) {
		this.game = game;
		this.buff = buff;
		this.name = name;
		this.baseCost = baseCost;
	}
	

	public Buff getBuff() {
		return buff;
	}

	public String getName() {
		return name;
	}

	public int getBaseCost() {
		return baseCost;
	}
	
	public void setBaseCost(int baseCost) {
		this.baseCost = baseCost;
	}

	public Game getGame() {
		return game;
	}
	
}
