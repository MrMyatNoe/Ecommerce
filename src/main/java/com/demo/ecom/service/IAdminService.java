package com.demo.ecom.service;

import com.demo.ecom.entity.Admin;

public interface IAdminService extends IBaseService<Admin>{

	void delete();
	
	Admin login(String email, String password);
	
	void resetPassword(String email, String password);
}
