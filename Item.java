
public abstract class Item {
	private Buff buff;
	private String name;
	private int cost;
	
	private Game game;
	
	public Item(Game game, String name, int cost) {
		this.game = game;
		this.name = name;
		this.cost = cost;
	}
	
	
	public Item(Game game, Buff buff, String name, int cost) {
		this.game = game;
		this.buff = buff;
		this.name = name;
		this.cost = cost;
	}
	

	public Buff getBuff() {
		return buff;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Game getGame() {
		return game;
	}
	
}
