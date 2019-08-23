package nl.sogyo.mancala;

public class Bowl extends Kalaha {
	public Bowl(Player player) {
		super(player);
		this.noOfStones = 4;
		this.owner = player;
		firstBowl = this;
		this.neighbour = new Bowl(player, 2, this);
	}
	
	public Bowl(Player player, int count, Kalaha firstBowl) {
		super(player, count, firstBowl);
		this.noOfStones = 4;
		this.owner = player;
		if(count == 6 || count == 13) {
			this.neighbour = new Kalaha(player, ++count, firstBowl);
		}
		else {
			this.neighbour = new Bowl(player, ++count, firstBowl);
		}
	}
	
	@Override
	public Kalaha findNextKalaha() {
		return getNeighbour().findNextKalaha();
	}
	
	public void makeMove() throws Exception {
		if (this.getOwner().getMyTurn() == true) {
			int stonesToPassOn = this.getNoOfStones();
			this.noOfStones = 0;
			passOnStones(this.neighbour, stonesToPassOn);
		}
		else {
			throw new Exception("The player tried to play on a bowl that was not theirs.");
		}
	}
}