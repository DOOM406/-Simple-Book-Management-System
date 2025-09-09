package com.management.system.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private int status;
	private String error; //<-- marca error
	private String msj;	//<-- msj declarado
	private String path; //<-- camino/ruta donde ocurre el error
	
	public ErrorResponse() {
		this.timestamp=LocalDateTime.now();
	}

	public ErrorResponse(int status, String error, String msj, String path) {
		this();
		this.status = status;
		this.error = error;
		this.msj = msj;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	/* 
	 	Cliente hace petición: GET /book/999
	 	
		Controller llama al Service: bookService.getBookById(999)
		
		Service no encuentra el libro: throw new BookNotFoundException("Book not found with id: 999")
		
		Spring intercepta la excepción: "¡Hey! Se lanzó una BookNotFoundException"
		
		Spring busca un handler: "¿Hay algún método con @ExceptionHandler(BookNotFoundException.class)?"
		
		Spring encuentra tu método: handleBookNotFound(ex, request)
		
		Tu método crea la respuesta: ErrorResponse con toda la info
		
		Spring devuelve al cliente: JSON con código 404
		
		 */
}
