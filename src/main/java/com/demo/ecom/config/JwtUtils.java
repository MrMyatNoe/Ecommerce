package com.demo.ecom.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author tmn
 *
 */
@Component
public class JwtUtils {

	@Value("${tmn.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${tmn.app.jwtExpirationMs}")
	private int jwtExpirations;
	
	public String generateTokenForAdmin(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
	}
	
	public String generateTokenForDriver(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getPhone())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
	}
}
