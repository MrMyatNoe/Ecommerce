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
	public UserSession saveData(UserSession u) {
		u.setCreated_date(System.currentTimeMillis());
		u.setUpdated_date(u.getCreated_date());
		return userSessionRepository.save(u);
	}

	@Override
	public UserSession updateData(UserSession us) {
		UserSession oldUserSession = getDataById(us.getId());
		us.setExpireTime(System.currentTimeMillis());

		us.setCreated_date(oldUserSession.getCreated_date());
		us.setUpdated_date(System.currentTimeMillis());
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

	@Override
	public UserSession getUserSessionByPhoneandIPAddress(String phone, String ipAddress) {
		System.out.println("phone "+ phone + " : IP " + ipAddress);
		UserSession userSession = userSessionRepository.getUserSessionByPhone(phone);
		if(userSession == null || !ipAddress.equals(userSession.getIpAddress())) {
			return null;
		}
		System.out.println(userSession);
		return userSession;
	}

}
