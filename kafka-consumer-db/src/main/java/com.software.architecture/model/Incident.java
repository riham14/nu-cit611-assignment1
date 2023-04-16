package com.software.architecture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidents")
public class Incident
{
	@Id
	private String id;

	public String title;
	public String description;
	public String address;
	public String phone;
	public String createdDate;

	public Incident() {

	}

	public Incident(String title, String description, String address, String phone, String createdDate) {
		this.title = title;
		this.description = description;
		this.address = address;
		this.phone = phone;
	}

	public Incident(String id, String title, String description, String address, String phone, String createdDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.address = address;
		this.phone = phone;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
  	public String toString() {
		return "Incident { " 
			+ "id=" + id 
			+ ", title=" + title 
			+ ", description=" + description 
			+ ", address=" + address 
			+ ", phone=" + phone 
			+ "}";
	}
}