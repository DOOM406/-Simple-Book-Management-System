package com.management.system.BookException;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(String msj) {
		super(msj);
	}
}
