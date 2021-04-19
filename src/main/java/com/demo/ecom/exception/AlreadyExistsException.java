package com.demo.ecom.exception;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("serial")
public class AlreadyExistsException extends DemoBasedException{

	public AlreadyExistsException(String message) {
		super(message);
	}

	@Override
	public ResponseEntity<Object> response() {
		return notFoundResponse();
	}

}
