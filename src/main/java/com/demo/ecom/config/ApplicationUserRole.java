package com.demo.ecom.config;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.demo.ecom.config.ApplicationUserPermission.*;

/**
 * @author tmn
 *
 */
public enum ApplicationUserRole {

	ADMIN(Sets.newHashSet(DRIVER_READ, DRIVER_WRITE ,ADMIN_READ, ADMIN_WRITE)),
	DRIVER(Sets.newHashSet());
	
	private final Set<ApplicationUserPermission> permissions;
	
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
}

