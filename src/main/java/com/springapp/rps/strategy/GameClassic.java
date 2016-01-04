package com.springapp.rps.strategy;

import static com.springapp.rps.model.MoveType.PAPER;
import static com.springapp.rps.model.MoveType.ROCK;
import static com.springapp.rps.model.MoveType.SCISSORS;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.springapp.rps.exception.InvalidMoveException;
import com.springapp.rps.exception.InvalidPlayerSetupException;
import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;

/**
 * Classic rock-paper-scissors game with 3 possible move and 1 winner or draw
 * 
 * @author Mariusz Dekarski
 *
 */
public class GameClassic implements Strategy {

	private static MoveType[] possibleMoves = { ROCK, PAPER, SCISSORS };

	@Override
	public GameResult play(Player... players) {

		// make sure that players are setup
		validatePlayerSetup(players);

		for (Player player : players) {
			if (player.isComputer())
				generateComputerMove(player);
			else
				validatePlayerMove(player); // validate if the moves are
											// possible
		}
		int winner = getWinner(players[0], players[1]);

		return new GameResult(players[0], players[1], winner);
	}

	private void validatePlayerSetup(Player... players) {
		if (players[0] == null || players[1] == null)
			throw new InvalidPlayerSetupException(
					"Need to setup 2 players to play");
	}

	private void generateComputerMove(Player player) {
		MoveType move = getRandomMove();
		// TODO change to logger
		System.out.println("Computer move: " + player.getName() + " | " + move);
		player.setMove(move);
	}

	private MoveType getRandomMove() {
		int randomNumber = new Random().nextInt(3);
		return getPossibleMoves()[randomNumber];
	}

	/**
	 * Who is the winner 0 - draw, 1 - first player, 2 - second
	 * 
	 * @param playerOne
	 *            player
	 * @param playerTwo
	 *            player
	 * @return int representation of the winners
	 */
	private int getWinner(Player playerOne, Player playerTwo) {
		int winner = 0;

		MoveType move1 = playerOne.getMove();
		MoveType move2 = playerTwo.getMove();

		// first check if it's a draw
		if (move1.equals(move2)) {
			winner = 0; // draw
		} else {
			switch (move1) {
			case ROCK:
				winner = move2.equals(SCISSORS) ? 1 : 2;
				break;
			case PAPER:
				winner = move2.equals(ROCK) ? 1 : 2;
				break;
			case SCISSORS:
				winner = move2.equals(PAPER) ? 1 : 2;
				break;
			}
		}
		System.out.println("Winner: " + winner);
		return winner;
	}

	/**
	 * Validates if selected move is possible for this game
	 * 
	 * @param playerOne
	 *            move map
	 */
	private void validatePlayerMove(Player player) {
		List<MoveType> moveList = Arrays.asList(possibleMoves);

		System.out.println("Player move: " + player.getName() + " | "
				+ player.getMove());

		if (!moveList.contains(player.getMove())) {
			throw new InvalidMoveException("This move is not alowed");
		}
	}

	@Override
	public MoveType[] getPossibleMoves() {
		return possibleMoves;
	}

}
