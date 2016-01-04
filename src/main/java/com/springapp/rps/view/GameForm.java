package com.springapp.rps.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.springapp.rps.model.GameMode;
import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.GameType;
import com.springapp.rps.model.MoveType;

/**
 * Form used for Spring MVC view layer
 * 
 * @author Mariusz Dekarski
 *
 */
public class GameForm {

	private GameType gameType;
	private GameMode gameMode;

	@NotNull
	@Min(3)
	private String playerOne;

	@NotNull
	@Min(3)
	private String playerTwo;

	private MoveType playerOneMove;
	private MoveType playerTwoMove;

	private GameResult result;

	public void setResult(GameResult result) {
		this.result = result;
	}

	public GameResult getResult() {
		return result;
	}

	public MoveType getPlayerOneMove() {
		return playerOneMove;
	}

	public void setPlayerOneMove(MoveType playerOneMove) {
		this.playerOneMove = playerOneMove;
	}

	public MoveType getPlayerTwoMove() {
		return playerTwoMove;
	}

	public void setPlayerTwoMove(MoveType playerTwoMove) {
		this.playerTwoMove = playerTwoMove;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public String getPlayerOne() {
		return playerOne;
	}

	public String getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}

	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}

}
