package com.management.system.BookException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.management.system.dto.ErrorResponse;

@RestControllerAdvice // <-- esta anotación hace q maneje excepciones GLOBALMENTE
public class GlobalExceptionHandler {

	//con esto manejamos bookNotFound
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				"NOT FOUND",
				ex.getMessage(),
				request.getDescription(false).replace("uri=", ""));
	
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	//con esto manejamos BookAlreadyBorrow
	@ExceptionHandler(BookAlreadyBorrowedException.class)
	public ResponseEntity<ErrorResponse> handleBookAlreadyBorrow(BookAlreadyBorrowedException ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.CONFLICT.value(),
				"Book Already Borrow",
				ex.getMessage(),
				request.getDescription(false).replace("uri=", ""));
		
	
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	// manejamos InvalidBookData LA CUAL NOS SIRVE PARA CONTROLAR ATRIBUTOS
	@ExceptionHandler(InvalidBookDataException.class)
	public ResponseEntity<ErrorResponse> handleInvalidBookData(InvalidBookDataException ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"Bad Request",
				ex.getMessage(),
				request.getDescription(false).replace("uri=", ""));
		
	
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	// manejamos cualquier otra excepcion no controlada
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error",
				"Unexpected error ocurred",
				request.getDescription(false).replace("uri=", ""));
		
	
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
