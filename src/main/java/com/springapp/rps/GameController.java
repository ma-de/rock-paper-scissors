package com.springapp.rps;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.rps.model.GameMode;
import com.springapp.rps.model.GameResult;
import com.springapp.rps.model.GameType;
import com.springapp.rps.model.Player;
import com.springapp.rps.service.GameService;
import com.springapp.rps.view.GameForm;

@Controller
@RequestMapping("/")
public class GameController {

	private static final String COMPUTER_1_NAME = "C-3PO";
	private static final String COMPUTER_2_NAME = "R2-D2";
	private static final String GAME_FORM = "gameForm";
	private static final String PAGE = "game";

	@Autowired
	private GameService gameService;

	private Game game;

	private Player player1;
	private Player player2;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcomeScreen(ModelMap model, HttpServletRequest request) {
		System.out.println("welcome!");
		model.addAttribute("gameTypes", Arrays.asList(GameType.values()));
		return new ModelAndView(PAGE, GAME_FORM, new GameForm());
	}

	@RequestMapping(value = "/setGameType", method = RequestMethod.POST)
	public ModelAndView setGameType(ModelMap model,
			@ModelAttribute(GAME_FORM) GameForm gameForm,
			HttpServletRequest request) {
		model.addAttribute("gameModes", Arrays.asList(GameMode.values()));

		GameType gameType = gameForm.getGameType();
		game = gameService.initializeGame(gameType);

		return new ModelAndView(PAGE, GAME_FORM, gameForm);
	}

	@RequestMapping(value = "/setGameMode", method = RequestMethod.POST)
	public ModelAndView setGameMode(ModelMap model,
			@ModelAttribute(GAME_FORM) GameForm gameForm,
			HttpServletRequest request) {

		System.out.println("Setting game mode to: " + gameForm.getGameMode());
		return new ModelAndView(PAGE, GAME_FORM, gameForm);
	}

	@RequestMapping("/setPlayers")
	public ModelAndView setPlayers(ModelMap model,
			@Valid @ModelAttribute(GAME_FORM) GameForm gameForm) {

		setPossibleMoves(model);

		setupPlayers(gameForm);

		System.out.println(gameForm.getPlayerOne());
		System.out.println(gameForm.getPlayerTwo());

		return new ModelAndView(PAGE, GAME_FORM, gameForm);
	}

	private void setupPlayers(GameForm gameForm) {
		if (gameForm.getGameMode().equals(GameMode.PLAYER_PLAYER)) {
			player1 = gameService.buildPlayer(gameForm.getPlayerOne(), false);
			player2 = gameService.buildPlayer(gameForm.getPlayerOne(), false);
		} else if (gameForm.getGameMode().equals(GameMode.PLAYER_COMPUTER)) {
			player1 = gameService.buildPlayer(gameForm.getPlayerOne(), false);
			player2 = gameService.buildPlayer(COMPUTER_1_NAME, true);
			gameForm.setPlayerTwo(COMPUTER_1_NAME);
		} else {
			player1 = gameService.buildPlayer(COMPUTER_1_NAME, true);
			player2 = gameService.buildPlayer(COMPUTER_2_NAME, true);
			gameForm.setPlayerOne(COMPUTER_1_NAME);
			gameForm.setPlayerTwo(COMPUTER_2_NAME);
		}
	}

	@RequestMapping("/setPlayerMove")
	public ModelAndView setPlayerMove(ModelMap model,
			@ModelAttribute(GAME_FORM) GameForm gameForm) {

		setPossibleMoves(model);

		setPlayersMove(gameForm);

		playGame(gameForm);

		return new ModelAndView(PAGE, GAME_FORM, gameForm);
	}

	private void setPlayersMove(GameForm gameForm) {
		System.out.println(gameForm.getPlayerOne() + " | "
				+ gameForm.getPlayerOneMove());
		System.out.println(gameForm.getPlayerTwo() + " | "
				+ gameForm.getPlayerTwoMove());

		player1.setMove(gameForm.getPlayerOneMove());
		player2.setMove(gameForm.getPlayerTwoMove());
	}

	private void playGame(GameForm gameForm) {
		GameResult result = null;

		if (gameForm.getGameMode().equals(GameMode.PLAYER_PLAYER)) {
			if (gameForm.getPlayerOneMove() != null
					&& gameForm.getPlayerTwoMove() != null) {
				// check result
				result = game.play(player1, player2);
			}
		} else if (gameForm.getGameMode().equals(GameMode.PLAYER_COMPUTER)) {
			if (gameForm.getPlayerOneMove() != null) {
				// check result
				result = game.play(player1, player2);
				gameForm.setPlayerTwoMove(result.getPlayerTwo().getMove());
			}
		} else {
			// check result
			result = game.play(player1, player2);
			gameForm.setPlayerOneMove(result.getPlayerOne().getMove());
			gameForm.setPlayerTwoMove(result.getPlayerTwo().getMove());
		}
		gameForm.setResult(result);
	}

	/**
	 * Setting possible move according to game type
	 * 
	 * @param model
	 */
	private void setPossibleMoves(ModelMap model) {
		model.addAttribute("gamePossibleMoves",
				Arrays.asList(game.getPossibleMoves()));
	}
}