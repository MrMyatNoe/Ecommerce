package com.demo.ecom.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	@Value("${tmn.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${tmn.app.jwtExpirationMs}")
	private int jwtExpirations;
	
	public String generateToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
	}
}
