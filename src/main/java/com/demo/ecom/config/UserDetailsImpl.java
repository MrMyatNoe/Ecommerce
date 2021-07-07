package com.demo.ecom.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.entity.Driver;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;
	
	private String phone;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username,String email,String phone, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.authorities = authorities;
	}
	
	

	public static UserDetailsImpl buildAdmin(Admin admin) {
		System.out.println("in impl "+ admin);
		List<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority a = new SimpleGrantedAuthority(admin.getRole().getName());
		authorities.add(a);
		return new UserDetailsImpl(admin.getId(), admin.getName(), admin.getEmail(),null, admin.getPassword(), authorities);
	}

	public static UserDetailsImpl buildDriver(Driver driver) {
		System.out.println("in impl "+ driver);
		List<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority a = new SimpleGrantedAuthority(driver.getRole().getName());
		authorities.add(a);
		return new UserDetailsImpl(driver.getId(), driver.getName(), null,driver.getPhone(), driver.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", authorities=" + authorities + "]";
	}

}
