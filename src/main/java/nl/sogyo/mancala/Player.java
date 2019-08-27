package nl.sogyo.mancala;

class Player {
	boolean myTurn;
	Player opponent;
	int finalScore;
	
	Player(){
		this.finalScore = 0;
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player player){
		this.finalScore = 0;
		this.myTurn = false;
		this.opponent = player;
	}

	void changeTurn() {
		this.myTurn = !this.myTurn;
		this.opponent.myTurn = !this.opponent.myTurn;
	}
}
