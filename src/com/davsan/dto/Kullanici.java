package com.davsan.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class Kullanici
{
	@Id
	@Property("id")
	private ObjectId id = null;
	
	private String userName = null;
	private String password = null;
	private String name = null;
	private String surname = null;
	private String phone = null;
	private String address = null;
	private String tcNo = null;
	
	public Kullanici()
	{
	}
	
	public Kullanici(String userName, String password, String name, String surname, String phone,
			String address, String tcNo) 
	{
		this.id = new ObjectId(new Date()); 
		this.name = name;
		this.surname = surname;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.tcNo = tcNo;
	}

	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}
}
