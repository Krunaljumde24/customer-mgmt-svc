/**
 * 
 */
package com.devspace.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 
 */
@Entity
public class Customer {

	@Id
	private String id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String type;
	private String status;
	private Date registeredAt;
	private Date lastPurchase;
	private String typeOfPurchase;
	private String[] tags;
	private int satisfactionScore;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}

	public Date getLastPurchase() {
		return lastPurchase;
	}

	public void setLastPurchase(Date lastPurchase) {
		this.lastPurchase = lastPurchase;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getSatisfactionScore() {
		return satisfactionScore;
	}

	public void setSatisfactionScore(int satisfactionScore) {
		this.satisfactionScore = satisfactionScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeOfPurchase() {
		return typeOfPurchase;
	}

	public void setTypeOfPurchase(String typeOfPurchase) {
		this.typeOfPurchase = typeOfPurchase;
	}

}
