package com.springapp.rps.service;

import org.springframework.stereotype.Service;

import com.springapp.rps.Game;
import com.springapp.rps.model.GameType;
import com.springapp.rps.model.Player;

@Service
public class GameServiceImpl implements GameService {

	@Override
	public Game initializeGame(GameType gameType) {
		System.out.println("Game initialized with type: " + gameType);
		return new Game(gameType);
	}

	@Override
	public Player buildPlayer(String name, boolean isComputer) {
		return new Player.PlayerBuilder().name(name).computer(isComputer)
				.build();
	}
}
