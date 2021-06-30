package com.demo.ecom.controller.rest;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.config.JwtUtils;
import com.demo.ecom.config.UserDetailsImpl;
import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.UserSession;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.LoginRequest;
import com.demo.ecom.response.JwtResponse;
import com.demo.ecom.service.IAdminService;
import com.demo.ecom.service.IUserSessionService;

@RestController
@RequestMapping("/v1/auth")
public class LoginController extends BaseController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IUserSessionService userSessionService;

	@PostMapping("/login")
	public synchronized ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
		try {
			String email = loginRequest.getEmail();
			String password = loginRequest.getPassword();
			String ipAddress = InetAddress.getLocalHost().getHostAddress();
			Admin admin = adminService.login(email, password);
			
			JwtResponse jwtResponse = new JwtResponse();
			List<String> roles = new ArrayList<>();
			
			UserSession searchedUserSession = userSessionService.getUserSessionByEmailandIPAddress(email, ipAddress);
			if(searchedUserSession != null) {
				Date currentTime = new Date();
				System.out.println("current time : "+ currentTime);
				
				Date expiredTime = new Date(searchedUserSession.getExpireTime());
				System.out.println("expired time " + expiredTime);
				
				if (!currentTime.after(expiredTime)) {
					jwtResponse.setId(admin.getId());
					jwtResponse.setUsername(admin.getName());
					jwtResponse.setEmail(admin.getEmail());
					jwtResponse.setToken(searchedUserSession.getToken());
					roles.add(searchedUserSession.getRole());
					jwtResponse.setRoles(roles);
				} else {
					userSessionService.deleteById(searchedUserSession.getId());
				}
			}
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(admin.getName(), password));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwt = jwtUtils.generateToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			
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
            System.out.println("controller : "+ newUserSession );
            userSessionService.saveData(newUserSession);
			
            return successResponse(jwtResponse);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
