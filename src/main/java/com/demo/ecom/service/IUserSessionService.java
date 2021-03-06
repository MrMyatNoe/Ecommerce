package com.demo.ecom.service;

import com.demo.ecom.entity.UserSession;

public interface IUserSessionService extends IBaseService<UserSession>{

	UserSession getUserSessionByEmailandIPAddress(String email, String ipAddress);
}
