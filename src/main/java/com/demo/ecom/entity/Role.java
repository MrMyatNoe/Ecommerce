package com.demo.ecom.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import com.demo.ecom.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;
	@NotNull
	private int level;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Admin> admins;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Driver> drivers;

	@JsonIgnore
	private long created_date;

	@JsonIgnore
	private long updated_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	public Set<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	@PreRemove
    public void checkReviewAssociationBeforeRemoval() {
        if (!this.admins.isEmpty()) {
            throw new BadRequestException("Can't remove a role that has admin.");
        }
        if (!this.drivers.isEmpty()) {
            throw new BadRequestException("Can't remove a role that has driver.");
        }
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", level=" + level + ", created_date=" + created_date
				+ ", updated_date=" + updated_date + "]";
	}
	
}
