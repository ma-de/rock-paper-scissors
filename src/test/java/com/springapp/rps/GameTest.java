package com.springapp.rps;

import static com.springapp.rps.model.GameType.CLASSIC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.springapp.rps.exception.InvalidPlayerSetupException;
import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;

public class GameTest {

	@Test
	public void testGetPossibleMoves() {
		Game game = new Game(CLASSIC);

		// Get list of possible moves
		MoveType[] possibleMoveList = game.getPossibleMoves();
		assertEquals(3, possibleMoveList.length);
	}

	@Test
	public void testPlay() {
		Game game = new Game(CLASSIC);

		// players builder
		Player T1 = new Player.PlayerBuilder().name("T-1000").computer(true)
				.build();
		Player T2 = new Player.PlayerBuilder().name("T-800").computer(true)
				.build();

		GameResult result = game.play(T1, T2);
		assertTrue(result != null);
	}

	@Test(expected = InvalidPlayerSetupException.class)
	public void testPlayerNotSetup() {
		Game game = new Game(CLASSIC);
		game.play(null, null);
	}

	@Test
	public void testGetScoreBoard() {
		Game game = new Game(CLASSIC);
		assertTrue(game.getScoreBoard()[0] == 0);
		assertTrue(game.getScoreBoard()[1] == 0);
		assertTrue(game.getScoreBoard()[2] == 0);
	}
}
