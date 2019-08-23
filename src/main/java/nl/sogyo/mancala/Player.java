package nl.sogyo.mancala;

public class Player {
	protected boolean myTurn;
	protected Player opponent;
	
	Player(){
		this.myTurn = true;
		this.opponent = new Player(this);
	}
	
	Player(Player player){
		this.myTurn = false;
		this.opponent = player;
	}
	
	Player getOpponent(){
		return this.opponent;
	}	
}
