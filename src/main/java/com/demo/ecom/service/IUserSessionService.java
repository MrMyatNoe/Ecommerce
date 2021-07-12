package com.demo.ecom.service;

import com.demo.ecom.entity.UserSession;

public interface IUserSessionService extends IBaseService<UserSession>{

	UserSession getUserSessionByEmailandIPAddress(String email, String ipAddress);
	
	UserSession getUserSessionByPhoneandIPAddress(String phone, String ipAddress);
	
	UserSession getUserSessionByToken(String token);
}
