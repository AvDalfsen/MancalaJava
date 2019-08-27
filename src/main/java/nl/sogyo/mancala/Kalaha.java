package nl.sogyo.mancala;

public class Kalaha {
	protected int noOfStones;
	protected Player owner;
	protected Kalaha neighbour;
	protected Bowl firstBowl;
	
	public Kalaha(Player player) {
	}
	
	public Kalaha(Player player, int count, Bowl firstBowl) {
		this.noOfStones = 0;
		this.owner = player;
		player = owner.getOpponent();
		if(count == 7) {
			this.neighbour = new Bowl(player, ++count, firstBowl);
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
	
	public void passOnStones(Kalaha currentBowl, int stonesToPassOn) {
		if (currentBowl.getOwner().myTurn == true) {
			currentBowl.noOfStones = currentBowl.noOfStones + 1;
			checkEndOfPass(currentBowl, --stonesToPassOn, false);
		}
		else if (currentBowl.getOwner().myTurn == false) {
			checkEndOfPass(currentBowl, --stonesToPassOn, false);
		}
	}
	
	public void checkEndOfPass(Kalaha currentBowl, int stonesToPassOn, Boolean finalBowlEmpty) {
		if (stonesToPassOn != 0) {
			passOnStones(currentBowl.getNeighbour(), stonesToPassOn);
		}
		else if (stonesToPassOn == 0 && currentBowl.getOwner().getMyTurn() == true) {
			return; //What kind of statement/return should be given to indicate the player can do another move? Or is that front end? 
		}
		else {
			changeTurn(currentBowl.getOwner());
		}
	}
	
	public void changeTurn(Player player) {
		player.myTurn = !player.myTurn;
		player.getOpponent().myTurn = !player.getOpponent().myTurn;
	}	
}