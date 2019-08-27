package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.*;

public class KalahaTest {
	Bowl testBowl;
	Player testPlayer;
	
	@Before
	public void setUp() {
		testPlayer = new Player();
		testBowl = new Bowl(testPlayer);
	}
	
	@Test
	public void assertKalahaNotBowl() {		
		int expected = testBowl.findNextKalaha().getNoOfStones();
		int actual = testBowl.getNoOfStones();
		assertNotEquals("A kalaha should start with a different number of stones than a bowl", expected, actual);
	}
	
	@Test
	public void assertKalahaNotBowlType() {		
		Class expected = testBowl.findNextKalaha().getClass();
		Class actual = testBowl.getClass();
		assertNotEquals("Kalaha and bowl should be two different classes", expected, actual);
	}
	
	@Test
	public void differentKalahas() {
		Kalaha expected = testBowl.findNextKalaha().getNeighbour().findNextKalaha();
		Kalaha actual = testBowl.findNextKalaha();
		assertNotEquals("There should be two different kalahas", expected, actual);
	}
	
	@Test
	public void differentKalahasButSameType() {
		Class expected = testBowl.findNextKalaha().getNeighbour().findNextKalaha().getClass();
		Class actual = testBowl.findNextKalaha().getClass();
		assertEquals("There should be two different kalahas", expected, actual);
	}
	
	@Test(expected = Exception.class)
	public void findBowlKalahaTest() throws Exception {
		Kalaha expected = testBowl.findNextKalaha();
		Kalaha actual = ((Bowl) testBowl).findBowl(7);
		assertEquals("The findBowl function should not be able to return a kalaha", expected, actual);
	}
	
	@Test
	public void neighbourDifferentOwnerTest() {
		Player expected = testBowl.findNextKalaha().getNeighbour().getOwner().getOpponent();
		Player actual = testBowl.owner;
		assertEquals("The owner of the first bowl should be the opponent of the kalaha's neighbour's owner", expected, actual);
	}
}