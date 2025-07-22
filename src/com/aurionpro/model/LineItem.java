package com.aurionpro.model;

public class LineItem {
	private int id;
	private int quantity;
	private Product product;
	
	public LineItem(int id, int quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	public double calculateLineItemCost() {
		return product.getPrice()*getQuantity();
	}
	public String toStringSingleRow() {
	    return String.format("| %-10d | %-15s | %-8d | ₹%-10.2f | ₹%-10.2f |",
	            id,
	            product.getName(),
	            quantity,
	            product.getPrice(),
	            calculateLineItemCost());
	}



}
