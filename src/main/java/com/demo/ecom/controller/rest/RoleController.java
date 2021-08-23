package com.demo.ecom.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.IRoleService;

@RestController
@RequestMapping("/v1/roles")
public class RoleController extends BaseController{

	@Autowired
	IRoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Roles");
		//long start = System.currentTimeMillis();
		//List<Role> list = roleService.getAllDatas();
		//long end = System.currentTimeMillis();
		//System.out.println("Total time : "+ (end - start));
		return successResponse(roleService.getAllDatas().join());
	}
		
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveRole(@RequestBody Role role){
		logInfo("save role");
		try {
//			long start = System.currentTimeMillis();
//			long end = System.currentTimeMillis();
//			System.out.println("Total time : "+ (end - start));
			role.setCreated_date(System.currentTimeMillis());
			role.setUpdated_date(role.getCreated_date());
			return successResponse(roleService.saveData(role).join());
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editRole(@RequestBody Role role){
		logInfo("edit role");
		try {
			return successResponse(roleService.updateData(role));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteRoleById(@RequestParam(name = "id") long id){
		logInfo("delete role");
		try {
			roleService.deleteById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Delete Successful");
			return deleteSuccessResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, 
	        produces = MediaType.APPLICATION_JSON_VALUE, 
	        value = "/{id}")
	public ResponseEntity<Object> getRoleById(@PathVariable("id") Long id) {
		logInfo("Get Role By Id");
		try {
			return successResponse(roleService.getDataById(id).join());
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
