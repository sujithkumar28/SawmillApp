package com.sawmill.exception;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResouceNotFoundException(ResourceNotFoundException rException){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), rException.getMessage());
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage());
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(InputValidationException.class)
	public ResponseEntity<?> handleInputValidationException(InputValidationException inputValidationException){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), inputValidationException.getMessage());
		return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	
    
}
