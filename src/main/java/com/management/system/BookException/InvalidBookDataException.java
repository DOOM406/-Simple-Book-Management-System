package com.management.system.BookException;

public class InvalidBookDataException extends RuntimeException {
	public InvalidBookDataException(String msj) {
		super(msj);
	}
}
