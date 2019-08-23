package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlTest {

	@Test
	public void bowlCreationNoOfStonesTest() {
		Bowl testBowl = new Bowl(new Player());
		int expected = 4;
		int actual = testBowl.getNoOfStones();
		assertEquals("Bowl object should be created with 4 stones", expected, actual);
	}

	@Test
	public void bowlNeighbourOpponentTest() {
		Bowl testBowl = new Bowl(new Player());
		Player expected = testBowl.neighbour.owner;
		Player actual = testBowl.getOwner();
		assertEquals("The owner of the first bowl should be the same as its neighbour's", expected, actual);
	}
}
