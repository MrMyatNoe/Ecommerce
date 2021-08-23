package com.demo.ecom.service;

import com.demo.ecom.entity.UserSession;
import com.demo.ecom.exception.DemoBasedException;

public interface IUserSessionService extends IBaseService<UserSession>{

	UserSession getUserSessionByEmailandIPAddress(String email, String ipAddress) throws DemoBasedException, Exception;
	
	UserSession getUserSessionByPhoneandIPAddress(String phone, String ipAddress) throws DemoBasedException, Exception;
	
	UserSession getUserSessionByToken(String token);
}
