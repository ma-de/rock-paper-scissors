package com.springapp.rps;

import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.GameType;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;
import com.springapp.rps.strategy.Strategy;

/**
 * Class with game objects and game strategy
 * 
 * @author Mariusz Dekarski
 *
 */
public class Game {

	private Strategy strategy;

	private int[] scoreBoard = new int[3];

	/**
	 * Creates new game according to chosen strategy from enum GameType.
	 * 
	 * @param gameType
	 *            strategy enum
	 */
	public Game(GameType gameType) {
		strategy = new GameFactory().getStrategy(gameType);
	}

	/**
	 * Returns possible moves for given game strategy
	 * 
	 * @return
	 */
	public MoveType[] getPossibleMoves() {
		return strategy.getPossibleMoves();
	}

	/**
	 * Play the game based on strategy
	 * 
	 * @param playerOne
	 *            first player
	 * @param playerTwo
	 *            second player
	 * @return GameResult with winner
	 */
	public GameResult play(Player playerOne, Player playerTwo) {
		GameResult result = strategy.play(playerOne, playerTwo);
		updateScoreBoard(result);
		return result;
	}

	/**
	 * Updates scoreboard based on winner index
	 * 
	 * @param result
	 */
	private void updateScoreBoard(GameResult result) {
		scoreBoard[result.getWinner()]++;
	}

	public int[] getScoreBoard() {
		return scoreBoard;
	}

}
