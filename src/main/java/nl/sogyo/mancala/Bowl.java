package nl.sogyo.mancala;

public class Bowl extends Kalaha {
	Bowl(Player player) {
		super(player);
		this.noOfStones = 4;
		this.owner = player;
		this.id = 1;
		firstBowl = this;
		this.neighbour = new Bowl(player, 2, 2, this);
	}
	
	Bowl(Player player, int count, int id, Bowl firstBowl) {
		super(player, count, id, firstBowl);
		this.noOfStones = 4;
		this.owner = player;
		this.id = id;
		if(count == 6 || count == 13) this.neighbour = new Kalaha(player, ++count, ++id, firstBowl);
		else this.neighbour = new Bowl(player, ++count, ++id, firstBowl);
	}
	
	@Override
	public Kalaha findNextKalaha() {
		return this.neighbour.findNextKalaha();
	}

	@Override
	public void makeMove() throws Exception {
		if(this.owner.myTurn == true && this.noOfStones > 0) {
			this.neighbour.receive(this.noOfStones);
			this.empty();
			return;
		}
		else {
			throw new Exception("The player tried to play on an empty bowl.");
		}
	}

	@Override
	void receive(int stones){
		if(owner.myTurn == true && stones == 1 && isCurrentBowlEmpty()) this.steal();
		else {
			this.noOfStones++;
			passOn(--stones);
		}
	}

	void empty() {
		this.noOfStones = 0;
		return;
	}
	
	Boolean isCurrentBowlEmpty() {
		if(this.noOfStones == 0) return true;
		else return false;
	}

	int stepsToKalaha(Bowl source, int sourceId, int nextKalahaId, int steps) {
		Bowl currentBowl = source;
		if(sourceId + 1 != nextKalahaId) {
			currentBowl = (Bowl) currentBowl.neighbour;
			return currentBowl.stepsToKalaha(currentBowl, currentBowl.id, nextKalahaId, ++steps);
		}
		return steps + 1;
	}

	Bowl findOppositeBowl() {
		int steps = this.stepsToKalaha(this, this.id, this.findNextKalaha().id, 0);
		Kalaha start = this.findNextKalaha().neighbour;
		Bowl oppositeBowl = ((Bowl) start).stepToOppositeBowl(steps - 1, (Bowl) start);
		return oppositeBowl;
	}

	Bowl stepToOppositeBowl(int steps, Bowl currentBowl) {
		if(currentBowl.id != currentBowl.id + steps){
			return currentBowl.stepToOppositeBowl(--steps, (Bowl) currentBowl.neighbour);
		}
		return currentBowl;
	}
	
	void steal() {
		int totalStones = 1 + this.findOppositeBowl().stealEmpty(this.findOppositeBowl());
		this.noOfStones = 0;
		this.findNextKalaha().noOfStones = this.findNextKalaha().noOfStones + totalStones;
		this.owner.changeTurn();
	}
	
	int stealEmpty(Bowl stealTarget) {
		int stolenStones = stealTarget.noOfStones;
		stealTarget.noOfStones = 0;
		return stolenStones;
	}
}