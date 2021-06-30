package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.UserSession;
import com.demo.ecom.repository.UserSessionRepository;
import com.demo.ecom.service.IUserSessionService;

@Service
public class UserSessionServiceImpl implements IUserSessionService{

	@Autowired
	UserSessionRepository userSessionRepository;
	
	@Override
	public List<UserSession> getAllDatas() {
		return userSessionRepository.findAll();
	}

	@Override
	public UserSession saveData(UserSession us) {
		us.setCreatedDate(System.currentTimeMillis());
		us.setUpdatedDate(us.getCreatedDate());
		return userSessionRepository.save(us);
	}

	@Override
	public UserSession updateData(UserSession us) {
		UserSession oldUserSession = getDataById(us.getId());
		us.setExpireTime(System.currentTimeMillis());

		us.setCreatedDate(oldUserSession.getCreatedDate());
		us.setUpdatedDate(System.currentTimeMillis());
		return userSessionRepository.save(us);
	}

	@Override
	public void deleteById(long id) {
		this.userSessionRepository.deleteById(id);
	}

	@Override
	public UserSession getDataById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSession getUserSessionByEmailandIPAddress(String email, String ipAddress) {
		UserSession userSession = userSessionRepository.getUserSessionByEmail(email);
		if(userSession == null || !ipAddress.equals(userSession.getIpAddress())) {
			return null;
		}
		return userSession;
	}

}
