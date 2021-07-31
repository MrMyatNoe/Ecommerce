/**
 * 
 */
package com.demo.ecom.request;

import java.io.Serializable;

/**
 * @author msi
 *
 */
public class DriverRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private long id;
	private String name;
	private String nrc;
	private String license;
	private String gender;
	private String password;
	private String phone;
	private String imageName;
	private String address;
	
	/**
	 * 
	 */
	public DriverRequest() {
		// TODO Auto-generated constructor stub
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
