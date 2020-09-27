package com.capgemini.go.exceptions;

public class IllegalOrderActionException extends RuntimeException {

	public IllegalOrderActionException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public IllegalOrderActionException(String message) {
		super(message);
	}

	public IllegalOrderActionException(Throwable cause) {
		super(cause);
	}
	
	

}
