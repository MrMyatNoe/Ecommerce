package com.demo.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonIgnore
    private Car car;
    private String startedDate;
    private String endDate;
    private int days;
    private int total;

    @JsonIgnore
    private long created_date;

    @JsonIgnore
    private long updated_date;
    private transient long carId;
    private transient String carNo;

    public Maintenance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", shop='" + shop + '\'' +
                ", car=" + car +
                ", startedDate='" + startedDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", days=" + days +
                ", total=" + total +
                ", created_date=" + created_date +
                ", updated_date=" + updated_date +
                ", carId=" + carId +
                ", carNo=" + carNo +
                '}';
    }
}
