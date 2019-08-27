package nl.sogyo.mancala;

public class Kalaha {
	int noOfStones;
	int id;
	Player owner;
	Kalaha neighbour;
	Bowl firstBowl;
	
	Kalaha(Player player) {
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

	Bowl findBowl(int bowlNumber, Kalaha targetBowl) {
		if(targetBowl.id != bowlNumber) {
			targetBowl = targetBowl.neighbour;
			return findBowl(bowlNumber, targetBowl);
		}
		return ((Bowl) targetBowl);
	}
	//TODO met je kut hoofd fix die shit yo

	public void makeMove() throws Exception {
		throw new Exception("The player tried to play on a bowl that was not theirs or on a kalaha.");
	}

	void receive(int stones){
		if(this.owner.myTurn == true){
			this.noOfStones++;
			passOn(--stones);
		}
		else{
			this.noOfStones++;
			passOn(--stones);
		}
	}

	public void passOn(int stones){
		if(stones > 0) neighbour.receive(stones);
		else this.owner.changeTurn();
	}
}