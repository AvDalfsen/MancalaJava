package nl.sogyo.mancala;

class Player {
	boolean myTurn;
	Player opponent;
	
	Player(){
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player player){
		this.myTurn = false;
		this.opponent = player;
	}

	void changeTurn() {
		this.myTurn = !this.myTurn;
		this.opponent.myTurn = !this.opponent.myTurn;
	}
}
