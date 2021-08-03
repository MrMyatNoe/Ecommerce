package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DailyTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String transactionCode;
	private int daily;
	private int paid;
	private int remain;
	private int total;
	private String startedDate;
	private String endDate;

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
	
	public int getDaily() {
		return daily;
	}

	public void setDaily(int daily) {
		this.daily = daily;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
		return "DailyTransaction [id=" + id + ", transactionCode=" + transactionCode + ", daily=" + daily + ", paid="
				+ paid + ", remain=" + remain + ", total=" + total + ", startedDate=" + startedDate + ", endDate="
				+ endDate + ", created_date=" + created_date + ", updated_date=" + updated_date + "]";
	}

}
