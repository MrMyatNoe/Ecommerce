package com.demo.ecom.controller.rest;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.config.JwtUtils;
import com.demo.ecom.config.UserDetailsImpl;
import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.Category;
import com.demo.ecom.entity.Role;
import com.demo.ecom.entity.UserSession;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.AdminRequest;
import com.demo.ecom.response.JwtResponse;
import com.demo.ecom.service.IAdminService;
import com.demo.ecom.service.IRoleService;
import com.demo.ecom.service.IUserSessionService;

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

	@Autowired
	PasswordEncoder passcodeEncoder;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IUserSessionService userSessionService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllDatas() {
		logInfo("Get All Admins");
		return successResponse(adminService.getAllDatas());
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveAdmin(@RequestBody AdminRequest request) {
		logInfo("save admin");
		try {
			Role role = roleService.getDataById(request.getRoleId());
			Admin admin = new Admin(request);
			admin.setPassword(passcodeEncoder.encode(request.getPassword()));
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

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
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

	@PostMapping("/login")
	public synchronized ResponseEntity<Object> login(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) throws Exception {
		try {
			String ipAddress = InetAddress.getLocalHost().getHostAddress();
			Admin admin = adminService.login(email, password);

			JwtResponse jwtResponse = new JwtResponse();
			List<String> roles = new ArrayList<>();

			UserSession searchedUserSession = userSessionService.getUserSessionByEmailandIPAddress(email, ipAddress);
			if (searchedUserSession != null) {
				Date currentTime = new Date();

				Date expiredTime = new Date(searchedUserSession.getExpireTime());

				if (!currentTime.after(expiredTime)) {
					jwtResponse.setId(admin.getId());
					jwtResponse.setUsername(admin.getName());
					jwtResponse.setEmail(admin.getEmail());
					jwtResponse.setToken(searchedUserSession.getToken());
					roles.add(searchedUserSession.getRole());
					jwtResponse.setRoles(roles);
					return successResponse(jwtResponse);
				} else {
					userSessionService.deleteById(searchedUserSession.getId());
				}
			}
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(admin.getName(), password));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtUtils.generateToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

			jwtResponse.setId(admin.getId());
			jwtResponse.setUsername(admin.getName());
			jwtResponse.setEmail(admin.getEmail());
			jwtResponse.setToken(jwt);
			jwtResponse.setRoles(roles);

			UserSession newUserSession = new UserSession();
			newUserSession.setUsername(admin.getName());
			newUserSession.setEmail(admin.getEmail());
			newUserSession.setToken(jwt);
			newUserSession.setIpAddress(ipAddress);
			newUserSession.setRole(roles.get(0));
			newUserSession.setLoginTime(System.currentTimeMillis());
			newUserSession.setExpireTime(System.currentTimeMillis() + 300000);
			userSessionService.saveData(newUserSession);

			return successResponse(jwtResponse);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> logout(@RequestParam(name = "email") String email) throws Exception {
		try {
			String ipAddress = InetAddress.getLocalHost().getHostName();
			String alreadyLogout = "Already Logged out";
			String logoutSuccess = "Logged out successfully";
			UserSession userSession = userSessionService.getUserSessionByEmailandIPAddress(email, ipAddress);
			if (userSession != null) {
				return badRequestResponse(alreadyLogout);
			}
			userSessionService.updateData(userSession);
			return successResponse(logoutSuccess);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> resetPassword(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "role") String role)
			throws Exception {
		try {
			adminService.resetPassword(email, passcodeEncoder.encode(password));
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Password Updated Successfully");
			return successResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
