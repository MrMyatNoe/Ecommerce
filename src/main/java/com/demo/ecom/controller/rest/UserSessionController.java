package com.demo.ecom.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.exception.DemoBasedException;
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
	
	@RequestMapping(method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> deleteUserSessionById(@RequestParam(name = "id") long id){
		logInfo("delete role");
		try {
			userSessionService.deleteById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Delete Successful");
			return deleteSuccessResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
