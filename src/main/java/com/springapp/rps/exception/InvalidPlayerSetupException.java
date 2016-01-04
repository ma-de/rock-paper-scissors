package com.springapp.rps.exception;

public class InvalidPlayerSetupException extends RuntimeException {

	private static final long serialVersionUID = 8691079113902803858L;

	private String message;

	public InvalidPlayerSetupException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
