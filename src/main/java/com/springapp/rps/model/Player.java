package com.springapp.rps.model;

/**
 * Player class describing player by name and if the player is computer or not.
 * Player is build with builder design pattern.
 * 
 * @author Mariusz Dekarski
 */
public class Player {

	private String name; // players name
	private boolean computer; // is the player a computer
	private MoveType move;

	public Player(PlayerBuilder playerBuilder) {
		this.name = playerBuilder.name;
		this.computer = playerBuilder.computer;
	}

	public String getName() {
		return name;
	}

	public boolean isComputer() {
		return computer;
	}

	public void setMove(MoveType move) {
		this.move = move;
	}

	public MoveType getMove() {
		return move;
	}

	public static class PlayerBuilder {
		private String name;
		private boolean computer;

		public PlayerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PlayerBuilder computer(boolean computer) {
			this.computer = computer;
			return this;
		}

		public Player build() {
			return new Player(this);
		}
	}
}
