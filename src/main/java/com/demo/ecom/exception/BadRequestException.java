package com.demo.ecom.exception;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("serial")
public class BadRequestException extends DemoBasedException{

	public BadRequestException(String message) {
		super(message);
	}

	@Override
	public ResponseEntity<Object> response() {
		return badRequestResponse();
	}

	
}
