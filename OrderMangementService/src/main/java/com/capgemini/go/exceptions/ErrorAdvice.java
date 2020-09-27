package com.capgemini.go.exceptions;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdvice {

	 @ExceptionHandler({ OrderNotFoundException.class, SQLException.class, NullPointerException.class })
	    public ResponseEntity<Object> handle(OrderNotFoundException ce) {
	   
	        return new ResponseEntity<Object>(ce.getMessage(),HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(DuplicateOrderException.class)
	    public ResponseEntity<Object> handle(DuplicateOrderException ce) {
	   
	        return new ResponseEntity<Object>(ce.getMessage(),HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(IllegalOrderActionException.class)
	    public ResponseEntity<Object> handle(IllegalOrderActionException ce) {
	   
	        return new ResponseEntity<Object>(ce.getMessage(),HttpStatus.FORBIDDEN);
	    }
}
