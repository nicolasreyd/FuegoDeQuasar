package com.exception;

import org.springframework.http.HttpStatus;

public class ApiException {
	private final String message;
	private final HttpStatus httpSatuts;

	public ApiException(String message, HttpStatus httpSatuts) {
		super();
		this.message = message;
		this.httpSatuts = httpSatuts;
	}

	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpSatuts() {
		return httpSatuts;
	}

}
