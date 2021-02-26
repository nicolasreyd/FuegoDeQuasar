package com.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.ApiREST;



@ControllerAdvice(assignableTypes = ApiREST.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {ApiNoDataException.class})
	public ResponseEntity<Object> handleApiNoDataException(ApiNoDataException e){
		
		ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}

}
