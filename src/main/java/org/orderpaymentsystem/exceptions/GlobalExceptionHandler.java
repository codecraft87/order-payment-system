package org.orderpaymentsystem.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderAlreadyCancelledException.class)
	public ResponseEntity<ErrorResponse> handleAlreadyCancelled(OrderAlreadyCancelledException ex){
		
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						"ORDER_ALREADY_CREATED",
						ex.getMessage(),
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(OrderCanNotBeModify.class)
	public ResponseEntity<ErrorResponse> handleUpdateOrder(OrderCanNotBeModify ex){
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(
						"ORDER_CAN_NOT_MODIFIED",
						ex.getMessage(),
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException ex){
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(
						"ORDER_NOT_FOUND",
						ex.getMessage(),
						LocalDateTime.now()));
	}
}
