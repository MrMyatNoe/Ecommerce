package com.demo.ecom.service;

import com.demo.ecom.entity.Driver;

public interface IDriverService extends IBaseService<Driver> {

	void deleteAll();
	
	Driver login(String phone, String password);
	
	void resetPassword(String email, String password);
}
