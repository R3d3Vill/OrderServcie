package com.capgemini.go.exceptions;

public class DuplicateOrderException extends RuntimeException{

	public DuplicateOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateOrderException(String message) {
		super(message);
	}

	public DuplicateOrderException(Throwable cause) {
		super(cause);
	}
	

}
