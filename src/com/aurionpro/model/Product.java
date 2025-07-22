package com.aurionpro.model;

import java.io.Serializable;

public class Product implements Serializable {
	 private static final long serialVersionUID = 1L;
	 
	private int id ;
	private String name;
	private double price;
	private String category;
	public Product(int id, String name, double price , String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category=category;
		
	}
	
	public String getCategory() {
		return category;
	}
	


	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price  + "]";
	}
	

}
