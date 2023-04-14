package com.software.architecture.model;

import org.springframework.data.annotation.Id;
// import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "incidents")
public class Incident
{
	@Id
	private String id;

	public String title;
	public String description;
	public String address;
	public int phone;

	// @DateTimeFormat(pattern = "dd-MM-yyyy")
	// public Date timestamp;

  public Incident() {

  }

	public Incident(String title, String description, String address, int phone) {
		this.title = title;
		this.description = description;
		this.address = address;
		this.phone = phone;
		// this.timestamp = new Date();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// public Date getTimestamp() {
	// 	return this.timestamp;
	// }

	// public void setTimestamp(Date timestamp) {
	// 	this.timestamp = timestamp;
	// }

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
  public String toString() {
    return "Incident { " +
			"id=" + id + ", title=" + title + ", description=" + description + ", address=" + address + ", phone=" + phone 
			// +	", timestamp=" + timestamp 
			+ "}";
  }
}