package com.demo.ecom.controller.rest;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecom.config.JwtUtils;
import com.demo.ecom.config.UserDetailsImpl;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.entity.Role;
import com.demo.ecom.entity.UserSession;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.response.JwtResponse;
import com.demo.ecom.service.IDriverService;
import com.demo.ecom.service.IRoleService;
import com.demo.ecom.service.IUserSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
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
	
	@Autowired
	IUserSessionService userSessionService;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	private static final String path = "/home/tmn/public/Ecommerce/Images";

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveDriver(@RequestParam("file") MultipartFile file,
			@RequestParam("driver") String driver)
			throws JsonMappingException, JsonProcessingException, DemoBasedException {
		File serverFile = null;
		try {
			Driver d = new ObjectMapper().readValue(driver, Driver.class);
			boolean pathExists = new File(path+ "/Drivers").exists();
			if (!pathExists) {
				new File(path+ "/Drivers").mkdir();
			} else {
				new File(path+ "/Drivers/" + d.getName()).mkdir();
			}
			
			String originalFileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(originalFileName) + "."
					+ FilenameUtils.getExtension(originalFileName);
			serverFile = new File(path+ "/Drivers/" + d.getName()  + File.separator + newFileName);
			try {
				FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			final String roleName = "Driver";
			Role role = roleService.findByName(roleName);
			d.setPassword(passcodeEncoder.encode(d.getPassword()));
			d.setImageName(serverFile.toString());
			d.setRole(role);
			return successResponse(driverService.saveData(d));
		} catch (DemoBasedException e) {
			// TODO server file remove
			serverFile.delete();	
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
	
	@RequestMapping(method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	public synchronized ResponseEntity<Object> getDriverById(@PathVariable("id") long id){
		try {
		Driver d = this.driverService.getDataById(id);
		System.out.println(" Driver : "+ d);
		return successResponse(d);
		}catch(DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@PostMapping("/login")
	public synchronized ResponseEntity<Object> login(@RequestParam(name = "phone") String phone,
			@RequestParam(name = "password") String password) throws Exception {
		try {
			logInfo("Driver login");
			String ipAddress = InetAddress.getLocalHost().getHostAddress();
			Driver driver = driverService.login(phone, password);
			System.out.println("Driver "+ driver);
			JwtResponse jwtResponse = new JwtResponse();
			List<String> roles = new ArrayList<>();

			UserSession searchedUserSession = userSessionService.getUserSessionByPhoneandIPAddress(phone, ipAddress);
			if (searchedUserSession != null) {
				Date currentTime = new Date();

				Date expiredTime = new Date(searchedUserSession.getExpireTime());

				if (!currentTime.after(expiredTime)) {
					jwtResponse.setId(driver.getId());
					jwtResponse.setUsername(driver.getName());
					jwtResponse.setPhone(driver.getPhone());
					jwtResponse.setToken(searchedUserSession.getToken());
					roles.add(searchedUserSession.getRole());
					jwtResponse.setRoles(roles);
					return successResponse(jwtResponse);
				} else {
					userSessionService.deleteById(searchedUserSession.getId());
				}
			}
			System.out.println(" driver : "+ driver.getName() + password);
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(driver.getName().concat("&&"+driver.getRole().getName()), password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwt = jwtUtils.generateTokenForDriver(authentication);
			System.out.println("jwt : " + jwt);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

			jwtResponse.setId(driver.getId());
			jwtResponse.setUsername(driver.getName());
			jwtResponse.setPhone(driver.getPhone());
			jwtResponse.setToken(jwt);
			jwtResponse.setRoles(roles);

			UserSession newUserSession = new UserSession();
			newUserSession.setUsername(driver.getName());
			newUserSession.setPhone(driver.getPhone());
			newUserSession.setToken(jwt);
			newUserSession.setIpAddress(ipAddress);
			newUserSession.setRole(roles.get(0));
			newUserSession.setLoginTime(System.currentTimeMillis());
			newUserSession.setExpireTime(System.currentTimeMillis() + 3600000);
			userSessionService.saveData(newUserSession);

			return successResponse(jwtResponse);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> resetPassword(@RequestParam(name = "phone") String phone,
			@RequestParam(name = "password") String password)
			throws Exception {
		try {
			System.out.println(phone + " : " + password );
			driverService.resetPassword(phone, passcodeEncoder.encode(password));
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Password Updated Successfully");
			return successResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
