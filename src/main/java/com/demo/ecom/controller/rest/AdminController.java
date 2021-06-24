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

import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.Category;
import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.AdminRequest;
import com.demo.ecom.service.IAdminService;
import com.demo.ecom.service.IRoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/admins")
public class AdminController extends BaseController {

	@Autowired
	IAdminService adminService;

	@Autowired
	IRoleService roleService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDatas() {
		logInfo("Get All Admins");
		return successResponse(adminService.getAllDatas());
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveAdmin(@RequestBody AdminRequest request) {
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
	public synchronized ResponseEntity<Object> editAdmin(@RequestBody AdminRequest adminRequest) {
		logInfo("edit admin" + adminRequest);
		try {
			Role role = roleService.getDataById(adminRequest.getRoleId());
			
			Admin admin = new Admin(adminRequest);
			admin.setId(adminRequest.getId());
			admin.setRole(role);
			return successResponse(adminService.updateData(admin));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
	public synchronized ResponseEntity<Object> deleteAll() {
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

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> deleteAdmin(@RequestParam(name = "id") long id) {
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

	@ApiOperation(value = "Get Admin By Id", response = Category.class, tags = "getAdminById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	// @PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> getAdminById(@PathVariable("id") Long id) {
		logInfo("Get Admin By Id");
		try {
			return successResponse(adminService.getDataById(id));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
