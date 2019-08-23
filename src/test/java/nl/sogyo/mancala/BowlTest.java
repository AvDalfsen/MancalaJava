package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlTest {
	Bowl testBowl = new Bowl(new Player());
	@Test
	public void bowlCreationNoOfStonesTest() {
		int expected = 4;
		int actual = testBowl.getNoOfStones();
		assertEquals("Bowl object should be created with 4 stones", expected, actual);
	}

	@Test
	public void bowlNeighbourOpponentTest() {
		Player expected = testBowl.neighbour.owner;
		Player actual = testBowl.getOwner();
		assertEquals("The owner of the first bowl should be the same as its neighbour's", expected, actual);
	}
	
	@Test
	public void bowlEmptiedTest() throws Exception {
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.getNoOfStones();
		assertEquals("After being subjected to a 'move', the picked bowl should be empty", expected, actual);
	}
	
	@Test
	public void stoneAddedToBowlTest() throws Exception {
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.getNeighbour().getNoOfStones();
		assertEquals("Once being passed to, a bowl should add a stone to their collection", expected, actual);
	}
	
	@Test
	public void makeMoveFunctionEndTest() throws Exception {
		testBowl.makeMove();
		int expected = 4;
		int actual = testBowl.findBowl(6).getNoOfStones();
		assertEquals("The 6th bowl should not have gotten an extra stone", expected, actual);
	}
	
	@Test
	public void makeMoveFunctionLastBowlTest() throws Exception {
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.findBowl(5).getNoOfStones();
		assertEquals("The 5th bowl should be the last to have gotten an extra stone", expected, actual);
	}
	
	@Test
	public void changeTurnTestOpponent() throws Exception {
		testBowl.makeMove();
		boolean expected = false;
		boolean actual = testBowl.getOwner().getMyTurn();
		assertEquals("After a move the turn of the player should be set to false", expected, actual);
	}
	
	@Test
	public void changeTurnTestPlayer() throws Exception {
		testBowl.makeMove();
		boolean expected = true;
		boolean actual = testBowl.getOwner().getOpponent().getMyTurn();
		assertEquals("After a move the turn of the opponent should be set to true", expected, actual);
	}
}
