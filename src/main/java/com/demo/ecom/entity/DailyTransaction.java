package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.demo.ecom.request.DailyTransactionRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DailyTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String transactionCode;
	private String status; // DAILY, REPAIR, ABSENT
	private double amount = 0.0;
	private double fee = 0.0;
	private double totalAmount = 0.0;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id", nullable = false)
	private Driver driver;
	
	@JsonIgnore
	private long created_date;

	@JsonIgnore
	private long updated_date;
	
	public DailyTransaction() {}
	
	public DailyTransaction(DailyTransactionRequest request) {
		this.id = request.getId();
		this.status = request.getStatus();
		this.amount = request.getAmount();
		this.fee = request.getFee();
		System.out.println("this is enity constructor" + this.status + " " + this.amount + " " + this.getFee());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
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
		return "DailyTransaction [id=" + id + ", transactionCode=" + transactionCode + ", status=" + status
				+ ", amount=" + amount + ", fee=" + fee + ", totalAmount=" + totalAmount + ", car=" + car + ", driver="
				+ driver + ", created_date=" + created_date + ", updated_date=" + updated_date + "]";
	}

}
