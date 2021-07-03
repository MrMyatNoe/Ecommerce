package com.demo.ecom.service;

import com.demo.ecom.entity.Role;

public interface IRoleService extends IBaseService<Role>{

	Role findByName(String name);
}
