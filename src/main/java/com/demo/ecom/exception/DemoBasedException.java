package com.demo.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.ecom.response.DefaultResponse;

@SuppressWarnings("serial")
public abstract class DemoBasedException extends RuntimeException{

	public DemoBasedException(String message) {
		super(message);
	}
	
	protected ResponseEntity<Object> badRequestResponse(){
		DefaultResponse resp = new DefaultResponse();
		resp.setMessage(getMessage());
		return new ResponseEntity<Object>(resp,HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> notFoundResponse(){
		DefaultResponse resp = new DefaultResponse();
		resp.setMessage(getMessage());
		return new ResponseEntity<Object>(resp,HttpStatus.NOT_FOUND);
	}
	
	public abstract ResponseEntity<Object> response();
}
