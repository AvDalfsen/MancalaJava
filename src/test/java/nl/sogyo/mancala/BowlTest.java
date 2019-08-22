package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlTest {

	@Test
	public void bowlCreationNoOfStonesTest() {
		Bowl testBowl = new Bowl();
		int expected = 4;
		int actual = testBowl.getNoOfStones();
		assertEquals("Bowl object should be created with 4 stones", expected,actual);
	}

}
