package com.demo.ecom.controller.rest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecom.entity.Driver;
import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.IDriverService;
import com.demo.ecom.service.IRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController extends BaseController {

	@Autowired
	IDriverService driverService;

	@Autowired
	ServletContext context;
	
	@Autowired
	PasswordEncoder passcodeEncoder;
	
	@Autowired
	IRoleService roleService;
	
	private static final String path = "/home/tmn/public/Ecommerce/Images";

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveDriver(@RequestParam("file") MultipartFile file,
			@RequestParam("driver") String driver)
			throws JsonMappingException, JsonProcessingException, DemoBasedException {
		try {
			Driver d = new ObjectMapper().readValue(driver, Driver.class);
			System.out.println(d);
			boolean pathExists = new File(path+ "/Drivers").exists();
			if (!pathExists) {
				new File(path+ "/Drivers").mkdir();
			} else {
				new File(path+ "/Drivers/" + d.getName()).mkdir();
			}
			
			String originalFileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(originalFileName) + "."
					+ FilenameUtils.getExtension(originalFileName);
			File serverFile = new File(path+ "/Drivers/" + d.getName()  + File.separator + newFileName);
			System.out.println("Server File :"+serverFile) ;
			try {
				FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			final String roleName = "Driver";
			Role role = roleService.findByName(roleName);
			d.setPassword(passcodeEncoder.encode(d.getPassword()));
			d.setImageName(newFileName);
			d.setRole(role);
			return successResponse(driverService.saveData(d));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@ApiOperation(value = "Get All Drivers",response = Iterable.class, tags = "getAllDrivers")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Drivers");
		return successResponse(driverService.getAllDatas());
	}
	
//	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> deleteAllDatas(){
//		logInfo("delete all");
//		try {
//			driverService.deleteAll();
//			Map<String, Object> response = new HashMap<>();
//			response.put("message", "Delete Successful");
//			return deleteSuccessResponse(response);
//		} catch (DemoBasedException e) {
//			logError(e, e.getMessage());
//			return e.response();
//		}
//	}
	
	@RequestMapping(method = RequestMethod.DELETE ,produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> deleteDriverById(@RequestParam(name = "id") long id){
		logInfo("delete driver");
		try {
			driverService.deleteById(id);
			return deleteSuccessResponse("Delete successful");
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
