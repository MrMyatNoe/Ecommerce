package com.demo.ecom.exception;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("serial")
public class NotFoundException extends DemoBasedException{

	public NotFoundException(String message) {
		super(message);
	}

	@Override
	public ResponseEntity<Object> response() {
		return notFoundResponse();
	}

}
