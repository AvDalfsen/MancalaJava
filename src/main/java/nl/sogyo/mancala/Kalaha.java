package nl.sogyo.mancala;

public class Kalaha {
	protected int noOfStones;
	protected Player owner;
	protected Kalaha neighbour;
	protected Kalaha firstBowl;
	
	public Kalaha(Player player) {
	}
	
	public Kalaha(Player player, int count, Kalaha firstBowl) {
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
	
	public Kalaha findBowl(int bowlNumber) {
		Kalaha currentBowl = firstBowl;
		for(int i = 1; i < bowlNumber; i++) {
			currentBowl = currentBowl.getNeighbour();
		}
		return currentBowl;
	}
	
	public void passOnStones(Kalaha currentBowl, int stonesToPassOn) {
		if (currentBowl.getClass() == Kalaha.class && currentBowl.getOwner().myTurn == true) {
			currentBowl.noOfStones = currentBowl.noOfStones + 1;
			checkEndOfPass(currentBowl, --stonesToPassOn);
		}
		else if (currentBowl.getClass() == Kalaha.class && currentBowl.getOwner().myTurn == false) {
			checkEndOfPass(currentBowl, --stonesToPassOn);
		}
		else {
			currentBowl.noOfStones++;
			checkEndOfPass(currentBowl, --stonesToPassOn);
		}
	}
	
	public void checkEndOfPass(Kalaha currentBowl, int stonesToPassOn) {
		if (stonesToPassOn != 0 && currentBowl.getOwner().getMyTurn() == true) {
			passOnStones(currentBowl.getNeighbour(), stonesToPassOn);
		}
		else if (stonesToPassOn == 0 && currentBowl.getClass() == Kalaha.class && currentBowl.getOwner().getMyTurn() == true) {
			return; //What kind of statement/return should be given to indicate the player can do another move? Or is that front end? 
		}
		else if (stonesToPassOn == 0 && currentBowl.getOwner().getMyTurn() == true) {
			//check if can steal -- need to keep track of whether the bowl was empty before passing
			changeTurn(currentBowl.getOwner());
		}
		else if (stonesToPassOn == 0 && currentBowl.getOwner().getMyTurn() == false) { //Is this one necessary? Isn't is the only other option left?
			changeTurn(currentBowl.getOwner());
		}
	}
	
	public void changeTurn(Player player) {
		player.myTurn = !player.myTurn;
		player.getOpponent().myTurn = !player.getOpponent().myTurn;
	}	
}