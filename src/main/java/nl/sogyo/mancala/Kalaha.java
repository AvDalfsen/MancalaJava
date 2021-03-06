package nl.sogyo.mancala;

public class Kalaha {
	int noOfStones;
	int id;
	Player owner;
	Kalaha neighbour;
	Bowl firstBowl;

	Kalaha() {
	}

	Kalaha(Player player, int count, int id, Bowl firstBowl) {
		this.noOfStones = 0;
		this.owner = player;
		this.id = id;
		player = owner.opponent;
		if(count == 7) this.neighbour = new Bowl(player, ++count, ++id, firstBowl);
		else this.neighbour = firstBowl;
	}

	public Kalaha findNextKalaha() {
		return this;
	}

	Kalaha findBowl(int id) throws IndexOutOfBoundsException  {
		if(id < 1 || id > 14){
			return null;
		}
		Kalaha current = this;
		if(id != current.id){
			return current.neighbour.findBowl(id);
		}
		return current;
	}

	Bowl findOppositeBowl() {
		int steps = this.stepsToKalaha(this, this.id, this.findNextKalaha().id, 0);
		Kalaha start = this.findNextKalaha().neighbour;
		return ((Bowl) start).stepToOppositeBowl(steps - 1, (Bowl) start);
	}

	int stepsToKalaha(Kalaha source, int sourceId, int nextKalahaId, int steps) {
		Kalaha currentBowl = source;
		if(sourceId + 1 != nextKalahaId) {
			currentBowl = currentBowl.neighbour;
			return currentBowl.stepsToKalaha(currentBowl, currentBowl.id, nextKalahaId, ++steps);
		}
		return steps + 1;
	}

	public void makeMove() throws Exception {
		throw new Exception("The player tried to play on a bowl that was not theirs or on a kalaha.");
	}

	void receive(int stones){
		if(this.owner.myTurn){
			this.noOfStones++;
			passOn(stones - 1);
		}
		else passOn(stones);
	}

	void passOn(int stones){
		if(stones == 0 && this.owner.myTurn); //TODO How to indicate the player can make another move?
		else neighbour.receive(stones);
		if(!checkContinueGame(this.findBowl(1))) tallyScores(this, this.neighbour);
	}

	boolean checkContinueGame(Kalaha firstBowl) {
		if(firstBowl.owner.myTurn){
			return assertPossibleMoves(firstBowl, 6, false);
		}
		return assertPossibleMoves(firstBowl.findBowl(8), 6, false);
	}

	private boolean assertPossibleMoves(Kalaha currentBowl, int count, boolean canPlay) {
		if(count > 0 && !canPlay) {
			canPlay = checkPlayableBowls(currentBowl);
			return assertPossibleMoves(currentBowl.neighbour, count - 1, canPlay);
		}
		else if(count == 0 && !canPlay) {
			return false;
		}
		return true;
	}

	private boolean checkPlayableBowls (Kalaha currentBowl) {
		if(currentBowl.noOfStones == 0) {
			return false;
		}
		return true;
	}

	void tallyScores(Kalaha starter, Kalaha currentBowl) {
		if(currentBowl.id != starter.id) {
			currentBowl.owner.finalScore = currentBowl.owner.finalScore + currentBowl.noOfStones;
			currentBowl.tallyScores(starter, currentBowl.neighbour);
		}
		else starter.owner.finalScore = starter.owner.finalScore + starter.noOfStones;
	}
}