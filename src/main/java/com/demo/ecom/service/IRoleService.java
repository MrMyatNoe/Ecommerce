package com.demo.ecom.service;

import com.demo.ecom.entity.Role;

public interface IRoleService extends IAsyncService<Role>{

	Role findByName(String name);
}
