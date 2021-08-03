package com.demo.ecom.request;

import java.io.Serializable;

public class DailyTransactionRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String startedDate;
	private String endDate;
	private int paid;
	private int total;
	private long carId;
	private long driverId;

	public DailyTransactionRequest() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getCarId() {
		return carId;
	}	

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "DailyTransactionRequest [id=" + id + ", startedDate=" + startedDate + ", endDate=" + endDate + ", paid="
				+ paid + ", total=" + total + ", carId=" + carId + ", driverId=" + driverId + "]";
	}

}
