package com.demo.ecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ApiModelProperty(notes = "title of tutorial" ,name = "title", required = true )
	private String title;
	@ApiModelProperty(notes = "description of tutorial" ,name = "description", required = true )
	private String description;
	private boolean published;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
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
		return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", created_date="
				+ created_date + ", updated_date=" + updated_date + "]";
	}

}
