package com.demo.ecom.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.demo.ecom.request.CarRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String carNo;
	
	
	@NotNull
	private String brand;
	
	@NotNull
	private String name;

	@NotNull
	private int modelYear;

	@NotNull
	private String startedDate;

	private String photo;

	@NotNull
	private String color;

	@NotNull
	private String taxiNo;
	
	@NotNull
	private int dailyAmount;

	@NotNull
	private String licenseDate;

	@OneToMany(mappedBy = "car",
			fetch = FetchType.LAZY,
			orphanRemoval = false)
	@JsonIgnore
	private List<DailyTransaction> dailyList;
	
	@JsonIgnore
	private Long created_date;

	@JsonIgnore
	private Long updated_date;

	/**
	 * 
	 */
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(CarRequest request) {
		this.carNo = request.getCarNo();
		this.brand = request.getBrand();
		this.name = request.getName();
		this.modelYear = request.getModelYear();
		this.startedDate = request.getStartedDate();
		this.color = request.getColor();
		this.taxiNo = request.getTaxiNo();
		this.dailyAmount = request.getDailyAmount();
		this.licenseDate = request.getLicenseDate();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Long created_date) {
		this.created_date = created_date;
	}

	public Long getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Long updated_date) {
		this.updated_date = updated_date;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<DailyTransaction> getDailyList() {
		return dailyList;
	}

	public void setDailyList(List<DailyTransaction> dailyList) {
		this.dailyList = dailyList;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", carNo=" + carNo + ", name=" + name + ", modelYear=" + modelYear + ", startedDate="
				+ startedDate + ", photo=" + photo + ", color=" + color + ", taxiNo=" + taxiNo 
				+ ", dailyAmount=" + dailyAmount +", licenseDate="
				+ licenseDate + ", created_date=" + created_date + ", updated_date="
				+ updated_date + "]";
	}

}
