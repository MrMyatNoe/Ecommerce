package com.demo.ecom.service;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.DemoBasedException;

public interface IRoleService extends IAsyncService<Role>{

	Role findByName(String name)  throws DemoBasedException;
}
