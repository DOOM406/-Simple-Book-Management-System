package com.management.system.BookException;

public class BookAlreadyBorrowedException extends RuntimeException{
	public BookAlreadyBorrowedException(String msj) {
		super(msj);
	}
}
