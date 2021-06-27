package com.demo.ecom.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.AdminRespostiory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AdminRespostiory adminRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepo.findByName(username)
				.orElseThrow(() -> new NotFoundException("User not found with username " + username));
		System.out.println("User details impl : " + admin);
		return UserDetailsImpl.buildAdmin(admin);
	}

}
