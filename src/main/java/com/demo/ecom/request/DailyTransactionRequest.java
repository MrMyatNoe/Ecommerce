package com.demo.ecom.request;

import java.io.Serializable;

public class DailyTransactionRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String status; // DAILY, REPAIR, ABSENT
	private double amount;
	private double fee;
	private long carId;
	private long driverId;
	
	public DailyTransactionRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "DailyTransactionRequest [id=" + id + ", status=" + status + ", amount=" + amount + ", fee=" + fee
			 + ", carId=" + carId + ", driverId=" + driverId + "]";
	}

}
