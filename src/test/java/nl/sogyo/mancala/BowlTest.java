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
		int actual = testBowl.noOfStones;
		assertEquals("Bowl object should be created with 4 stones", expected, actual);
	}

	@Test
	public void bowlNeighbourOpponentTest() {
		Player expected = testBowl.neighbour.owner;
		Player actual = testBowl.owner;
		assertEquals("The owner of the first bowl should be the same as its neighbour's", expected, actual);
	}
	
	@Test
	public void bowlEmptiedTest() throws Exception {
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.noOfStones;
		assertEquals("After being subjected to a 'move', the picked bowl should be empty", expected, actual);
	}
	
	@Test
	public void stoneAddedToBowlTest() throws Exception {
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.neighbour.noOfStones;
		assertEquals("Once being passed to, a bowl should add a stone to their collection", expected, actual);
	}
	
	@Test
	public void makeMoveFunctionEndTest() throws Exception {
		testBowl.makeMove();
		int expected = 4;
		int actual = testBowl.findBowl(6, testBowl).noOfStones;
		assertEquals("The 6th bowl should not have gotten an extra stone", expected, actual);
	}
	
	@Test
	public void makeMoveFunctionLastBowlTest() throws Exception {
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.findBowl(5, testBowl).noOfStones;
		assertEquals("The 5th bowl should be the last to have gotten an extra stone", expected, actual);
	}
	
	@Test
	public void changeTurnTestOpponent() throws Exception {
		testBowl.makeMove();
		boolean expected = false;
		boolean actual = testBowl.owner.myTurn;
		assertEquals("After a move the turn of the player should be set to false", expected, actual);
	}
	
	@Test
	public void changeTurnTestPlayer() throws Exception {
		testBowl.makeMove();
		boolean expected = true;
		boolean actual = testBowl.owner.opponent.myTurn;
		assertEquals("After a move the turn of the opponent should be set to true", expected, actual);
	}
	
	@Test
	public void stepsToKalahaTest() {
		int expected = testBowl.stepsToKalaha(testBowl, testBowl.id, testBowl.findNextKalaha().id, 0);
		int actual = 6;
		assertEquals("It should take 6 steps to get from the first bowl to the next Kalaha", expected, actual);
	}
	
	@Test
	public void findOppositeBowlFromFirstBowlTest() {
		Bowl expected = testBowl.findOppositeBowl();
		Bowl actual = testBowl.findBowl(13, testBowl);
		assertEquals("The opposite bowl of the first bowl should be the 13th bowl", expected, actual);
	}

	@Test
	public void findOppositeBowlFromLastBowlTest() {
		Bowl expected = testBowl.findBowl(13, testBowl).findOppositeBowl();
		Bowl actual = testBowl;
		assertEquals("The opposite bowl of the first bowl should be the 13th bowl", expected, actual);
	}
	
	@Test
	public void findOppositeBowlFromAnyBowlTest() {
		Bowl expected = testBowl.findBowl(4, testBowl).findOppositeBowl();
		Bowl actual = testBowl.findBowl(10, testBowl);
		assertEquals("The opposite bowl of the fourth bowl should be the tenth bowl", expected, actual);
	}
	
	@Test
	public void findBowlTest() {
		Bowl expected = testBowl.findBowl(4, testBowl);
		Kalaha actual = testBowl.neighbour.neighbour.neighbour;
		assertEquals("The 2nd bowl should match the neighbour of the first bowl", expected, actual);
	}
	
	@Test
	public void stealTestFinalBowl() throws Exception {
		testBowl.findBowl(5, testBowl).noOfStones = 0;
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.findBowl(5, testBowl).noOfStones;
		assertEquals("After a steal the number of stones in the final bowl to receive a stone should be 0", expected, actual);
	}
	
	@Test
	public void stealTestOppositeBowl() throws Exception {
		testBowl.findBowl(5, testBowl).noOfStones = 0;
		testBowl.makeMove();
		int expected = 0;
		int actual = testBowl.findBowl(9, testBowl).noOfStones;
		assertEquals("After a steal the number of stones in the opposite bowl of the final bowl to receive a stone should be 0", expected, actual);
	}
	
	@Test
	public void stealTestKalaha() throws Exception {
		testBowl.findBowl(5, testBowl).noOfStones = 0;
		testBowl.makeMove();
		int expected = 5;
		int actual = testBowl.findNextKalaha().noOfStones;
		assertEquals("After a steal the total number of stones (stolen + 1 final stone triggering the steal) should be added to the kalaha", expected, actual);
		Kalaha currentbowl = testBowl;
		for (int i = 0; i < 14; i++){
			System.out.println(currentbowl.noOfStones + " " + currentbowl.id);
			currentbowl = currentbowl.neighbour;
		}
	}

	@Test
	public void kalahaStateAfterMove() throws Exception {
		testBowl.findBowl(5, testBowl).makeMove();
		int expected = 1;
		int actual = testBowl.findNextKalaha().noOfStones;
		assertEquals("The kalaha should get one stone after a move has been made and it gets passed", expected, actual);
	}
}
