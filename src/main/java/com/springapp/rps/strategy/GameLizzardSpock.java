package com.springapp.rps.strategy;

import static com.springapp.rps.model.MoveType.LIZARD;
import static com.springapp.rps.model.MoveType.PAPER;
import static com.springapp.rps.model.MoveType.ROCK;
import static com.springapp.rps.model.MoveType.SCISSORS;
import static com.springapp.rps.model.MoveType.SPOCK;

import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;

public class GameLizzardSpock implements Strategy {

	private static MoveType[] possibleMoves = { ROCK, PAPER, SCISSORS, LIZARD,
			SPOCK };

	@Override
	public MoveType[] getPossibleMoves() {
		return possibleMoves;
	}

	@Override
	public GameResult play(Player... players) {
		// TODO Auto-generated method stub
		return null;
	}

}
