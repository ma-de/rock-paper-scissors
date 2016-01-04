package com.springapp.rps.exception;

/**
 * Custom exception representing invalid move for game
 * 
 * @author Mariusz
 *
 */
public class InvalidMoveException extends RuntimeException {

	private static final long serialVersionUID = 3597813545839871939L;
	private String message;

	public InvalidMoveException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
