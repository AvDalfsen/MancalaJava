package nl.sogyo.mancala;

public class Kalaha {
	protected int noOfStones;
	protected Player owner;
	protected Kalaha neighbour;
	
	public Kalaha(Player player) {
	}
	
	public Kalaha(Player player, int count, Kalaha firstBowl) {
		this.noOfStones = 0;
		this.owner = player;
		player = owner.getOpponent();
		if(count == 7) {
			this.neighbour = new Bowl(player, count - 1, firstBowl);
		}
		else {
			this.neighbour = firstBowl;
		}
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public Kalaha getNeighbour() {
		return this.neighbour;
	}
	
	public int getNoOfStones() {
		return this.noOfStones;
	}
	
	public Kalaha findNextKalaha() {
		return this;
	}
}
