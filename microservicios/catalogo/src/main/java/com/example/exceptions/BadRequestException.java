package com.example.exceptions;

public class BadRequestException extends Exception{
	private static final long serialVersionUID = 1L;
	final static String MESSAGE_STRING = "Bad Request";
	
	public BadRequestException() {
		this(MESSAGE_STRING);	
	}
	
	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public BadRequestException(Throwable cause) {
		super(MESSAGE_STRING, cause);
		
	}
}
