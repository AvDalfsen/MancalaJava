package nl.sogyo.mancala;

public class Bowl extends Kalaha {
	public Bowl(Player player) {
		super(player);
		this.noOfStones = 4;
		this.owner = player;
		firstBowl = this;
		this.neighbour = new Bowl(player, 2, this);
	}
	
	public Bowl(Player player, int count, Bowl firstBowl) {
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
	
	public int stepsToKalaha() {
		int steps = 0;
		Kalaha currentBowl = this;
		while (currentBowl instanceof Bowl) {
			currentBowl = currentBowl.getNeighbour();
			steps++;
		}	
		return steps;
	}

	public void makeMove() throws Exception {
		if (this.getOwner().getMyTurn() == true && this.getClass() != this.findNextKalaha().getClass()) {
			int stonesToPassOn = this.getNoOfStones();
			this.noOfStones = 0;
			passOnStones(this.neighbour, stonesToPassOn);
		}
		else {
			throw new Exception("The player tried to play on a bowl that was not theirs or on a kalaha.");
		}
	}
	
	@Override
	public void passOnStones(Kalaha currentBowl, int stonesToPassOn) {
		Boolean finalBowlEmpty = ((Bowl) currentBowl).isCurrentBowlEmpty();
		currentBowl.noOfStones++;
		checkEndOfPass(currentBowl, --stonesToPassOn, finalBowlEmpty);
	}
	
	@Override
	public void checkEndOfPass(Kalaha currentBowl, int stonesToPassOn, Boolean finalBowlEmpty) {
		if (stonesToPassOn != 0) {
			passOnStones(currentBowl.getNeighbour(), stonesToPassOn);
		}
		else if (stonesToPassOn == 0 && currentBowl.getOwner().getMyTurn() == true && finalBowlEmpty == true) {
			((Bowl) currentBowl).steal(((Bowl) currentBowl));
			changeTurn(currentBowl.getOwner());
		}
		else {
			changeTurn(currentBowl.getOwner());
		}
	}
	
	public Boolean isCurrentBowlEmpty() {
		if (this.noOfStones == 0) {
			return true;
		}
		else {
			return false;	
		}
	}
	
	public Bowl findOppositeBowl() {
		int steps = this.stepsToKalaha();
		Kalaha startKalaha = this.findNextKalaha();
		Kalaha oppositeBowl = startKalaha;
		for(int i = 0; i < steps; i++) {
			oppositeBowl = oppositeBowl.getNeighbour();
		}
		return ((Bowl) oppositeBowl);
	}
	
	public void steal(Bowl currentBowl) {
		int totalStones = currentBowl.noOfStones + currentBowl.findOppositeBowl().stealEmpty(currentBowl.findOppositeBowl());
		currentBowl.noOfStones = 0;
		currentBowl.findNextKalaha().noOfStones = currentBowl.findNextKalaha().noOfStones + totalStones;
		changeTurn(currentBowl.getOwner());
	}
	
	public int stealEmpty(Bowl stealTarget) {
		int stolenStones = stealTarget.noOfStones;
		stealTarget.noOfStones = 0;
		return stolenStones;
	}
	
	public Bowl findBowl(int bowlNumber) throws Exception {
		Kalaha currentBowl = firstBowl;
		for(int i = 1; i < bowlNumber; i++) {
			currentBowl = currentBowl.getNeighbour();
		}
		if(currentBowl.getClass() == Kalaha.class) {
			throw new Exception("The number given directs to a kalaha. Please enter a number directing to a bowl.");
		}
		else {return ((Bowl) currentBowl);}
	}
}