package com.demo.ecom.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.AdminRequest;
import com.demo.ecom.service.IAdminService;
import com.demo.ecom.service.IRoleService;

@RestController
@RequestMapping("v1/admins")
public class AdminController extends BaseController{

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IRoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Admins");
		return successResponse(adminService.getAllDatas());
	}
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveAdmin(@RequestBody AdminRequest request){
		logInfo("save admin");
		try {
			Role role = roleService.getDataById(request.getRoleId());
			Admin admin = new Admin(request);
			admin.setRole(role);
			return successResponse(adminService.saveData(admin));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> editAdmin(@RequestBody Admin admin){
		logInfo("edit admin");
		try {
			return successResponse(adminService.updateData(admin));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			path = "/all")
	public synchronized ResponseEntity<Object> deleteAll(){
		logInfo("delete all");
		try {
			adminService.delete();
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Delete Successful");
			return deleteSuccessResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> deleteAdmin(@RequestParam(name = "id") long id){
		logInfo("delete role");
		try {
			adminService.deleteById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Delete Successful");
			return deleteSuccessResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
