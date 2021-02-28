package com.exception;

public class ApiNoDataException extends RuntimeException{
	public ApiNoDataException(String message) {
		super(message);
	}
	
	public ApiNoDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
