package com.demo.ecom.config;

/**
 * @author tmn
 *
 */
public enum ApplicationUserPermission {

	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write"),
	DRIVER_READ("student:read"),
	DRIVER_WRITE("student:write");

	private final String permission;
	
	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
