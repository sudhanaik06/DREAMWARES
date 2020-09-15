package com.integrate.app.dreamwares.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SubscriberRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<SubscriberErrorResponse> handleException(SubscriberNotFoundException e){
		
		SubscriberErrorResponse error = new SubscriberErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<SubscriberErrorResponse> handleException(AccessException e){
		
		SubscriberErrorResponse error = new SubscriberErrorResponse();
		
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	public ResponseEntity<SubscriberErrorResponse> handleException(Exception e){
		
		SubscriberErrorResponse error = new SubscriberErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
