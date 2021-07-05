package com.demo.ecom.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.AdminRepostiory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AdminRepostiory adminRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepo.findByName(username)
				.orElseThrow(() -> new NotFoundException("User not found with username " + username));
		return UserDetailsImpl.buildAdmin(admin);
	}

}
