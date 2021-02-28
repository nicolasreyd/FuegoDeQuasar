package com.app;

import org.springframework.http.HttpStatus;

public class ApiException {
	private final String message;
	private final HttpStatus httpSatuts;
	private final int httpStatusCode;

	public ApiException(String message, HttpStatus httpSatuts, int httpSatutscode) {
		super();
		this.message = message;
		this.httpSatuts = httpSatuts;
		this.httpStatusCode = httpSatutscode;
	}

	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpSatuts() {
		return httpSatuts;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

}
