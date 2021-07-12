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
	
	//@Value("${tmn.app.jwtExpirationMs}")
	private int jwtExpirationMs =  300000;
	
	public String generateTokenForAdmin(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//		return Jwts.builder().setSubject(userPrincipal.getEmail())
//				.setIssuedAt(new Date())
//				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
//				.signWith(SignatureAlgorithm.HS512,jwtSecret)
//				.compact();
		
		return Jwts.builder().setSubject((userPrincipal.getUsername()))
				.setIssuer(userPrincipal.getAuthorities().stream().findFirst().get().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes()).compact();
	}
	
	public String generateTokenForDriver(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//		return Jwts.builder().setSubject(userPrincipal.getPhone())
//				.setIssuedAt(new Date())
//				.setExpiration(new Date((new Date()).getTime() + jwtExpirations))
//				.signWith(SignatureAlgorithm.HS512,jwtSecret)
//				.compact();
		
		return Jwts.builder().setSubject((userPrincipal.getUsername()))
				.setIssuer(userPrincipal.getAuthorities().stream().findFirst().get().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes()).compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		System.out.println("in jwt "+Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getSubject());
		return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String getUsertypeFromJwtToken(String token) {
		System.out.println("in jwt "+ Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getIssuer());
		return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getIssuer();
	}
}
