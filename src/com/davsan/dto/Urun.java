package com.davsan.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class Urun {
	private String productName = null;
	
	@Id
	@Property("id")
	private ObjectId id = null;
	private int productCount = 0;
	private float price = 0L;

	public Urun(String productName, int productCount, float price) {
		this.id = new ObjectId(new Date());
		this.productName = productName;
		this.productCount = productCount;
		this.price = price;
	}

	public Urun() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ObjectId getProductID() {
		return id;
	}

	public void setProductID(ObjectId productID) {
		this.id = productID;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
