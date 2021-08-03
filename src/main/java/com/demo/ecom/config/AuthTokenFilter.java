package com.demo.ecom.config;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.ecom.entity.UserSession;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.IUserSessionService;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

public class AuthTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	IUserSessionService userSessionService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private final JsonMapper mapper = new JsonMapper();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("request "+ request);
			String jwt = parseJwt(request);
			if(jwt !=null && !isTokenExpired(request)){
				String name = jwtUtils.getUserNameFromJwtToken(jwt);
				String type = jwtUtils.getUsertypeFromJwtToken(jwt);
				String username = name + "&&" + type;
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		} catch (MalformedJwtException e) {
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(190);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		} catch (ExpiredJwtException e) {
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(190); // for session expired
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		} catch (JwtException e) {
			e.printStackTrace();
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		} catch (UsernameNotFoundException e) {
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		} catch (DemoBasedException e) {
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		} catch (Exception e) {
			Map<String, Object> errorDetails = new HashMap<>();
			errorDetails.put("message", e.getMessage());
			response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			mapper.writeValue(response.getWriter(), errorDetails);
		}
		
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		System.out.println(headerAuth);
		if(StringUtils.hasText(headerAuth)) {
			String token = headerAuth.substring(0,headerAuth.length());
			return token;
		}
		return null;
	}
	
	private boolean isTokenExpired(HttpServletRequest request) throws DemoBasedException, Exception{
		String headerAuthToken = request.getHeader("Authorization");
		UserSession userSessionByToken = userSessionService.getUserSessionByToken(headerAuthToken);
		if(userSessionByToken == null) {
			throw new MalformedJwtException("Your token is invalid. It may have been forbidden by admin.");
		}
		
		Date currentTime = new Date();
		Date expiredTime = new Date(userSessionByToken.getExpireTime());
		System.out.println("current time : " + currentTime);
		System.out.println("expired time : " + expiredTime);
		if(!currentTime.after(expiredTime)) {
			return false;
		}
		throw new ExpiredJwtException(null, null, "Your token has expired!");
	}
}
