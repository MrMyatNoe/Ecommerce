package com.demo.ecom.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.repository.AdminRepostiory;
import com.demo.ecom.repository.DriverRepository;

/**
 * @author tmn
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AdminRepostiory adminRepo;

	@Autowired
	DriverRepository driverRepo;

	public UserDetails loadUserByUsername(String userNameType) throws UsernameNotFoundException {
		String username = null;
		String userType = null;
		Pattern pattern = Pattern.compile("&&");
		Matcher matcher = pattern.matcher(userNameType);
		if (matcher.find()) {
			username = userNameType.substring(0, matcher.start());
			userType = userNameType.substring(matcher.end());
		}

		if (userType.equals("Driver")) {
			Driver driver = driverRepo.findByName(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found with username "));
			return UserDetailsImpl.buildDriver(driver);

		} else {
			Admin admin = adminRepo.findByName(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found with username "));
			return UserDetailsImpl.buildAdmin(admin);
		}
	}

}
