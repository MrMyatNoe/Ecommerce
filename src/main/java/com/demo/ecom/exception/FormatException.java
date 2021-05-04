package com.demo.ecom.exception;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("serial")
public class FormatException extends DemoBasedException{

	public FormatException(String message) {
		super(message);
	}

	@Override
	public ResponseEntity<Object> response() {
		return notFoundResponse();
	}

}
