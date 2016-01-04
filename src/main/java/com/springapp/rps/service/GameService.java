package com.springapp.rps.service;

import com.springapp.rps.Game;
import com.springapp.rps.model.GameType;
import com.springapp.rps.model.Player;

public interface GameService {

	public Game initializeGame(GameType gameType);

	public Player buildPlayer(String name, boolean computer);

}
