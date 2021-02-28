package com.app;

@SuppressWarnings("serial")
public class ApiNoDataException extends RuntimeException{
	public ApiNoDataException(String message) {
		super(message);
	}
	
	public ApiNoDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
