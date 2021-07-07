package com.demo.ecom.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String nrc;

	@NotNull
	private String license;

	@NotNull
	private String gender;

	@NotNull
	private String address;

	@NotNull
	private String phone;
	
	@NotNull
	private String password;
	private String imageName;

	@OneToMany(mappedBy = "driver")
	@JsonIgnore
	private List<DailyTransaction> dailyList;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id",nullable = false)
	@JsonIgnore
	private Role role;
	
	@JsonIgnore
	private long created_date;

	@JsonIgnore
	private long updated_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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

	public List<DailyTransaction> getDailyList() {
		return dailyList;
	}

	public void setDailyList(List<DailyTransaction> dailyList) {
		this.dailyList = dailyList;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", nrc=" + nrc + ", license=" + license + ", gender=" + gender
				+ ", address=" + address + ", phone=" + phone + ", password=" + password + ", imageName=" + imageName
				+ ", dailyList=" + dailyList + ", role=" + role + ", created_date=" + created_date + ", updated_date="
				+ updated_date + "]";
	}

}
