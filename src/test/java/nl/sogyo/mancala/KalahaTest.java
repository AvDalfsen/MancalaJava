package nl.sogyo.mancala;

import static org.junit.Assert.*;

import org.junit.*;

public class KalahaTest {
	private Bowl testBowl;

	@Before
	public void setUp() {
		Player testPlayer = new Player();
		testBowl = new Bowl(testPlayer);
	}
	
	@Test
	public void assertKalahaNotBowl() {		
		int expected = testBowl.findNextKalaha().noOfStones;
		int actual = testBowl.noOfStones;
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
		Kalaha expected = testBowl.findNextKalaha().neighbour.findNextKalaha();
		Kalaha actual = testBowl.findNextKalaha();
		assertNotEquals("There should be two different kalahas", expected, actual);
	}
	
	@Test
	public void differentKalahasButSameType() {
		Class expected = testBowl.findNextKalaha().neighbour.findNextKalaha().getClass();
		Class actual = testBowl.findNextKalaha().getClass();
		assertEquals("There should be two different kalahas", expected, actual);
	}

	@Test(expected = Exception.class)
	public void makeMoveOnKalahaTest() throws Exception {
		testBowl.findNextKalaha().makeMove();
	}
	
	@Test
	public void neighbourDifferentOwnerTest() {
		Player expected = testBowl.findNextKalaha().neighbour.owner.opponent;
		Player actual = testBowl.owner;
		assertEquals("The owner of the first bowl should be the opponent of the kalaha's neighbour's owner", expected, actual);
	}

	@Test
	public void kalahaPassTest() throws Exception {
		testBowl.findBowl(5).noOfStones = 10;
		testBowl.findBowl(5).makeMove();
		int expected = 0;
		int actual = testBowl.findBowl(14).noOfStones;
		assertEquals("After having been passed during the player's turn, the opponent's kalaha should not receive a stone", expected, actual);
	}

	@Test
	public void outOfBoundsFindBowlNumberTest() {
		Kalaha expected = testBowl.findBowl(20);
		assertNull("FindBowl() called with out of bounds number should return null", expected);
	}

	@Test
	public void finalCountTestPlayer1() {
		testBowl.tallyScores(testBowl, testBowl.neighbour);
		int expected = testBowl.owner.finalScore;
		int actual = 24;
		assertEquals("Without having made a move, the final score for the first player should be 24", expected, actual);
	}

	@Test
	public void finalCountTestPlayer2() {
		testBowl.tallyScores(testBowl, testBowl.neighbour);
		int expected = testBowl.findBowl(10).owner.finalScore;
		int actual = 24;
		assertEquals("Without having made a move, the final score for the first player should be 24", expected, actual);
	}

	@Test
	public void endOfGameCheckAfterInitialisationTest() {
		boolean actual = testBowl.checkContinueGame(testBowl);
		assertTrue("After initialisation, there should be playable bowls", actual);
	}

	@Test
	public void continueGameAfterAllBowlsEmptyTest() throws Exception {
		testBowl.findBowl(8).noOfStones = 0;
		testBowl.findBowl(9).noOfStones = 0;
		testBowl.findBowl(10).noOfStones = 0;
		testBowl.findBowl(11).noOfStones = 0;
		testBowl.findBowl(12).noOfStones = 0;
		testBowl.findBowl(13).noOfStones = 0;
		testBowl.makeMove();
		boolean actual = testBowl.checkContinueGame(testBowl);
		assertFalse("When all bowls of the player are empty, the game should not continue when it becomes his turn", actual);
	}
}