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
		if(this.owner.myTurn && this.noOfStones > 0) {
			this.neighbour.receive(this.noOfStones);
			this.empty();
		}
		else {
			throw new Exception("The player tried to play on an empty bowl.");
		}
	}

	@Override
	void receive(int stones){
		if(owner.myTurn && stones == 1 && isCurrentBowlEmpty()) this.steal();
		else {
			this.noOfStones++;
			passOn(--stones);
		}
	}

	private void empty() {
		this.noOfStones = 0;
	}
	
	private Boolean isCurrentBowlEmpty() {
		return this.noOfStones == 0;
	}


	Bowl stepToOppositeBowl(int steps, Bowl currentBowl) {
		if(currentBowl.id != currentBowl.id + steps){
			return currentBowl.stepToOppositeBowl(--steps, (Bowl) currentBowl.neighbour);
		}
		return currentBowl;
	}
	
	private void steal() {
		int totalStones = 1 + this.findOppositeBowl().stealEmpty(this.findOppositeBowl());
		this.noOfStones = 0;
		this.findNextKalaha().noOfStones = this.findNextKalaha().noOfStones + totalStones;
		this.owner.changeTurn();
	}
	
	private int stealEmpty(Bowl stealTarget) {
		int stolenStones = stealTarget.noOfStones;
		stealTarget.noOfStones = 0;
		return stolenStones;
	}
}