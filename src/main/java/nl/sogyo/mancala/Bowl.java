package nl.sogyo.mancala;

public class Bowl extends Kalaha {
	public Bowl(Player player) {
		super(player);
		this.noOfStones = 4;
		this.owner = player;
		this.neighbour = new Bowl(player, 12, this);
	}
	
	public Bowl(Player player, int count, Kalaha firstBowl) {
		super(player, count, firstBowl);
		this.noOfStones = 4;
		this.owner = player;
		if(count == 8 || count == 1) {
			this.neighbour = new Kalaha(player, count - 1, firstBowl);
		}
		else {
			this.neighbour = new Bowl(player, count - 1, firstBowl);
		}		
	}
	
	@Override
	public Kalaha findNextKalaha() {
		return getNeighbour().findNextKalaha();
	}
}