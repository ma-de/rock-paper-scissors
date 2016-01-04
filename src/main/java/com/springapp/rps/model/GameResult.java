package com.springapp.rps.model;

/**
 * State of game result
 * 
 * @author Mariusz Dekarski
 *
 */
public class GameResult {

	private Player playerOne;
	private Player playerTwo;
	private int winner;

	public GameResult(Player playerOne, Player playerTwo, int winner) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.winner = winner;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public int getWinner() {
		return winner;
	}

}
