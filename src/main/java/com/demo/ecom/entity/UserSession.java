package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String phone;
	private String token;
	private String ipAddress;
	private long loginTime;
	private long expireTime;
	private String role;
	@JsonIgnore
	private long created_date;

	@JsonIgnore
	private long updated_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getCreated_date() {
		return created_date;
	}

	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}

	public long getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(long updated_date) {
		this.updated_date = updated_date;
	}

	@Override
	public String toString() {
		return "UserSession [id=" + id + ", username=" + username + ", email=" + email + ", token=" + token
				+ ", ipAddress=" + ipAddress + ", loginTime=" + loginTime + ", expireTime=" + expireTime + ", role="
				+ role + ", created_date=" + created_date + ", updated_date=" + updated_date + "]";
	}

}
