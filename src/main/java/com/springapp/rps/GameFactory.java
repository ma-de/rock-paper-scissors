package com.springapp.rps;

import com.springapp.rps.model.GameType;
import com.springapp.rps.strategy.GameClassic;
import com.springapp.rps.strategy.GameLizzardSpock;
import com.springapp.rps.strategy.Strategy;

/**
 * Game strategy factory - breaks SOLID principle but nice way to get easily
 * specific strategy for game.
 * 
 * @author Mariusz Dekarski
 *
 */
public class GameFactory {

	public Strategy getStrategy(GameType gameType) {

		switch (gameType) {
		case CLASSIC:
			return new GameClassic();
		case SPOCK:
			return new GameLizzardSpock();
		default:
			return new GameClassic();
		}
	}

}
