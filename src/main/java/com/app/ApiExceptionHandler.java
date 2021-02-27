package com.app;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice(assignableTypes = ApiREST.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@SuppressWarnings("unused")
	private static final HttpStatusCodeException HttpStatusCodeException = null;

	@ExceptionHandler(value = {ApiNoDataException.class})
	public ResponseEntity<Object> handleApiNoDataException(ApiNoDataException e){
		
		ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, 404);
		
		return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
	}

}
