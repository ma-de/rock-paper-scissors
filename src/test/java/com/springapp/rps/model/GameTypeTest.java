package com.springapp.rps.model;

import static com.springapp.rps.model.GameType.CLASSIC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.springapp.rps.Game;

public class GameTypeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGame_Classic_PlayerTwoWin() {
		// Choosing strategy
		Game game = new Game(CLASSIC);

		// players builder
		Player playerOne = new Player.PlayerBuilder().name("Homer Simpson")
				.computer(false).build();
		Player playerTwo = new Player.PlayerBuilder().name("Peter Griffin")
				.computer(false).build();

		// Get list of possible moves
		MoveType[] possibleMoveList = game.getPossibleMoves();

		// Moves selection
		MoveType move1 = possibleMoveList[0]; // rock
		MoveType move2 = possibleMoveList[1]; // paper

		playerOne.setMove(move1);
		playerTwo.setMove(move2);

		// lets play and get result
		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(2, result.getWinner());

		// check scoreboard for winner 2 points
		assertTrue(game.getScoreBoard()[2] == 1);
	}

}
