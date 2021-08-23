/**
 * 
 */
package com.demo.ecom.request;

import java.io.Serializable;

/**
 * @author msi
 *
 */
public class CarRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private long id;
	private String carNo;
	private String brand;
	private String name;
	private int modelYear;
	private String startedDate;
	private String color;
	private String taxiNo;
	private int dailyAmount;
	private String licenseDate;
	private String photo;

	/**
	 * 
	 */
	public CarRequest() {
		// TODO Auto-generated constructor stub
	}

//	public long getId() {
//	return id;
//}
//
//public void setId(long id) {
//	this.id = id;
//}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTaxiNo() {
		return taxiNo;
	}

	public void setTaxiNo(String taxiNo) {
		this.taxiNo = taxiNo;
	}

	public int getDailyAmount() {
		return dailyAmount;
	}

	public void setDailyAmount(int dailyAmount) {
		this.dailyAmount = dailyAmount;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
