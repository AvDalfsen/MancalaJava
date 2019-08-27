package nl.sogyo.mancala;

import static org.junit.Assert.*;
import org.junit.*;

public class BowlTest {
	Bowl testBowl;
	
	@Before
	public void setUp() {
		testBowl = new Bowl(new Player());	
	}
	
	
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
	
	@Test
	public void stepsToKalahaTest() {
		int expected = testBowl.stepsToKalaha();
		int actual = 6;
		assertEquals("It should take 6 steps to get from the first bowl to the next Kalaha", expected, actual);
	}
	
	@Test
	public void findOppositeBowlFromFirstBowlTest() throws Exception {
		Bowl expected = testBowl.findOppositeBowl();
		Bowl actual = testBowl.findBowl(13);
		assertEquals("The opposite bowl of the first bowl should be the 13th bowl", expected, actual);
	}
	
	@Test
	public void findOppositeBowlFromAnyBowlTest() throws Exception {
		Bowl expected = testBowl.findBowl(4).findOppositeBowl();
		Bowl actual = testBowl.findBowl(10);
		assertEquals("The opposite bowl of the fourth bowl should be the tenth bowl", expected, actual);
	}
	
	@Test
	public void findBowlTest() throws Exception {
		Bowl expected = testBowl.findBowl(4);
		Kalaha actual = testBowl.getNeighbour().getNeighbour().getNeighbour();
		assertEquals("The 2nd bowl should match the neighbour of the first bowl", expected, actual);
	}
	
	@Test
	public void stealTestFinalBowl() throws Exception {
		testBowl.findBowl(5).noOfStones = 0;
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.findBowl(5).getNoOfStones();
		assertEquals("After a steal the number of stones in the final bowl to receive a stone should be 0", expected, actual);
	}
	
	@Test
	public void stealTestOppositeBowl() throws Exception {
		testBowl.findBowl(5).noOfStones = 0;
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.findBowl(9).getNoOfStones();
		assertEquals("After a steal the number of stones in the opposite bowl of the final bowl to receive a stone should be 0", expected, actual);
	}
	
	@Test
	public void stealTestKalaha() throws Exception {
		testBowl.findBowl(5).noOfStones = 0;
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.findNextKalaha().getNoOfStones();
		assertEquals("After a steal the total number of stones (stolen + 1 final stone triggering the steal) should be added to the kalaha", expected, actual);
	}
}
