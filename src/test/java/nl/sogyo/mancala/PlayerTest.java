package nl.sogyo.mancala;

import static org.junit.Assert.*;
import org.junit.*;

public class PlayerTest {

	@Test
	public void findOpponentTest() {
		Player testPlayer = new Player();
		Player expected = testPlayer.opponent.opponent;
		assertEquals("Player object's opponent's opponent should match the player object", expected, testPlayer);
	}
}