package com.springapp.rps.strategy;

import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.MoveType;
import com.springapp.rps.model.Player;

public interface Strategy {

	public MoveType[] getPossibleMoves();

	public GameResult play(Player... players);
}
