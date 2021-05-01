package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String carNo;
	private String name;
	private String modelYear;
	private Long startedDate;
	private String photo;
	private String color;
	private String taxiNo;
	private String licenseDate;
	@JsonIgnore
	private Long created_date;
	@JsonIgnore
	private Long updated_date;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public Long getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Long startedDate) {
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

}
