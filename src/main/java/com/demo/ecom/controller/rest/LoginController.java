package com.demo.ecom.controller.rest;

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
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.LoginRequest;
import com.demo.ecom.response.JwtResponse;
import com.demo.ecom.service.IAdminService;

@RestController
@RequestMapping("/v1/auth")
public class LoginController extends BaseController{

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	IAdminService adminService;
	
	@PostMapping("/login")
	public synchronized ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest loginRequest){
		try {
		Admin admin = adminService.findByemail(loginRequest.getEmail());
		
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(admin.getName(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
							.map(item -> item.getAuthority())
								.collect(Collectors.toList());
		
		return successResponse(new JwtResponse(jwt, 
										userDetails.getId(), 
										userDetails.getUsername(), 
										userDetails.getEmail(), 
										roles));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
