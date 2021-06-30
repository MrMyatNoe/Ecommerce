package com.demo.ecom.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.service.IUserSessionService;

@RequestMapping("/v1/sessions")
@RestController
public class UserSessionController extends BaseController{

	@Autowired
	IUserSessionService userSessionService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllDatas() {
		logInfo("Get All Sessions");
		return successResponse(userSessionService.getAllDatas());
	}
}
