package com.springapp.rps.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.springapp.rps.exception.InvalidMoveException;
import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;

public class GameClassicTest {

	private static GameClassic game;
	private static Player playerOne;
	private static Player playerTwo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		game = new GameClassic();

		// players builder
		playerOne = new Player.PlayerBuilder().name("Homer Simpson")
				.computer(false).build();
		playerTwo = new Player.PlayerBuilder().name("Peter Griffin")
				.computer(false).build();
	}

	@Test
	public void testGetPossibleMoves() {
		GameClassic game = new GameClassic();
		MoveType[] possibleMoves = game.getPossibleMoves();
		assertTrue(possibleMoves.length == 3);
	}

	@Test
	public void testPlayDraw() {
		playerOne.setMove(MoveType.ROCK);
		playerTwo.setMove(MoveType.ROCK);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(0, result.getWinner());
	}

	@Test
	public void testPlayWinRock() {
		playerOne.setMove(MoveType.ROCK);
		playerTwo.setMove(MoveType.SCISSORS);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(1, result.getWinner());
	}

	@Test
	public void testPlayWinPaper() {
		playerOne.setMove(MoveType.PAPER);
		playerTwo.setMove(MoveType.ROCK);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(1, result.getWinner());
	}

	@Test
	public void testPlayWinScissors() {
		playerOne.setMove(MoveType.SCISSORS);
		playerTwo.setMove(MoveType.PAPER);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(1, result.getWinner());
	}

	@Test
	public void testPlayPlayer2WinRock() {
		playerOne.setMove(MoveType.SCISSORS);
		playerTwo.setMove(MoveType.ROCK);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(2, result.getWinner());
	}

	@Test
	public void testPlayPlayer2WinPaper() {
		playerOne.setMove(MoveType.ROCK);
		playerTwo.setMove(MoveType.PAPER);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(2, result.getWinner());
	}

	@Test
	public void testPlayPlayer2WinScissors() {
		playerOne.setMove(MoveType.PAPER);
		playerTwo.setMove(MoveType.SCISSORS);

		GameResult result = game.play(playerOne, playerTwo);
		assertEquals(2, result.getWinner());
	}

	@Test
	public void testPlay_CompRandom() {
		Player playerOne = new Player.PlayerBuilder().name("C-3PO")
				.computer(true).build();
		Player playerTwo = new Player.PlayerBuilder().name("R2-D2")
				.computer(true).build();

		GameResult result = game.play(playerOne, playerTwo);
		int winner = result.getWinner();
		assertTrue(winner >= 0 && winner < 3);
	}

	@Test
	public void testPlay_PlayerROCKVsCompRandom() {
		playerOne.setMove(MoveType.ROCK);

		Player playerTwo = new Player.PlayerBuilder().name("R2-D2")
				.computer(true).build();

		GameResult result = game.play(playerOne, playerTwo);
		int winner = result.getWinner();
		assertTrue(winner >= 0 && winner < 3);
	}

	@Test(expected = InvalidMoveException.class)
	public void testPlayInvalidMove() {
		playerOne.setMove(MoveType.ROCK);
		playerTwo.setMove(MoveType.SPOCK);
		game.play(playerOne, playerTwo);
	}
}
