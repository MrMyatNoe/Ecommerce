package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String startedDate;
    private String endDate;
    private int days;
    private String reason;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    private Car car;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonIgnore
    private Driver driver;
    
    @JsonIgnore
    private long created_date;

    @JsonIgnore
    private long updated_date;
    
    private transient long carId;
    private transient String carNo;
    
    private transient long driverId;
    private transient String driverName;
    
    public Leave() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
    
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    
    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Leave [id=" + id + ", startedDate=" + startedDate + ", endDate=" + endDate + ", days=" + days
                + ", reason=" + reason + ", car=" + car + ", driver=" + driver + ", created_date=" + created_date
                + ", updated_date=" + updated_date + ", carId=" + carId + ", carNo=" + carNo + ", driverId=" + driverId
                + ", driverName=" + driverName + ", getId()=" + getId() + ", getStartedDate()=" + getStartedDate()
                + ", getEndDate()=" + getEndDate() + ", getDays()=" + getDays() + ", getReason()=" + getReason()
                + ", getCar()=" + getCar() + ", getDriver()=" + getDriver() + ", getCreated_date()=" + getCreated_date()
                + ", getUpdated_date()=" + getUpdated_date() + ", getCarId()=" + getCarId() + ", getCarNo()="
                + getCarNo() + ", getDriverId()=" + getDriverId() + ", getDriverName()=" + getDriverName()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }

   
    
}
