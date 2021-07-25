package com.demo.ecom.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.demo.ecom.response.DefaultResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class BaseController {

	protected ResponseEntity<Object> notFoundResponse(Object obj){
		return new ResponseEntity<Object>(obj,HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> badRequestResponse(String s){
		DefaultResponse resp = new DefaultResponse();
		resp.setMessage(s);
		return new ResponseEntity<Object>(resp,HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> successResponse(Object obj){
		return new ResponseEntity<Object>(obj,HttpStatus.OK);
	}
	
	protected ResponseEntity<Object> deleteSuccessResponse(Object obj){
		return new ResponseEntity<Object>(obj,HttpStatus.OK);
	}
	
	protected void logInfo(String msg) {
		System.out.println(msg);
	}
	
	protected void logError(Throwable t,String msg) {
		t.printStackTrace();
		System.out.println(msg);
	}
}

