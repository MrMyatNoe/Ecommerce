//package com.demo.ecom.config;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import com.google.common.collect.Sets;
//import static com.demo.ecom.config.ApplicationUserPermission.*;
//
///**
// * @author tmn
// *
// */
//public enum ApplicationUserRole {
//
//	ADMIN(Sets.newHashSet(DRIVER_READ, DRIVER_WRITE, ADMIN_READ, ADMIN_WRITE)), 
//	DRIVER(Sets.newHashSet());
//
//	private final Set<ApplicationUserPermission> permissions;
//
//	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
//		this.permissions = permissions;
//	}
//
//	public Set<ApplicationUserPermission> getPermissions() {
//		return permissions;
//	}
//
//	public Set<GrantedAuthority> getGrantedAuthorities() {
//		Set<GrantedAuthority> permissions = getPermissions().stream()
//				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
//		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//		return permissions;
//	}
//
//}