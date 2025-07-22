package com.aurionpro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Date date;
	private List<LineItem> items;
	private Customer customer;
	private String deliveryPartnerName;
	private double deliveryCharge;
	private boolean isPaid = false;
	public Order(int id, Date date , Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.customer= customer;
		this.items = new ArrayList<>();
	}
	
	public void setDeliveryPartnerName(String deliveryPartnerName) {
		this.deliveryPartnerName = deliveryPartnerName;
	}


	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public void addLineItem(LineItem lineitem) {
		items.add(lineitem);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<LineItem> getItems() {
		return items;
	}
	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	
	public double calculateOrderPrice() {
		double total =0;
		for(LineItem item : items) {
			total += item.calculateLineItemCost();
		}
		return total;
	}
	public double totalDiscount() {
		double dis = 0;
		if(calculateOrderPrice() > 500) {
			dis =  calculateOrderPrice() * 0.10;
		}
		return dis;
		
	}
	public double discountafterTotal() {
		return calculateOrderPrice()- totalDiscount();
	}

	
	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("============================================================\n");
	    sb.append("🧾                   Order Summary                          \n");
	    sb.append("============================================================\n");
	    sb.append(String.format("Customer Name    : %s\n", customer.getName()));
	    sb.append(String.format("Customer ID      : %d\n", customer.getId()));
	    sb.append(String.format("Order Date       : %s\n", 
	        (date != null) ? date.toString() : "N/A"));

	    sb.append("\n==================== Your Shopping Cart ====================\n");
	    sb.append("+------------+-----------------+----------+-------------+-------------+\n");
	    sb.append(String.format("| %-10s | %-15s | %-8s | %-11s | %-11s |\n",
	            "Product ID", "Product Name", "Quantity", "Unit Price", "Total Price"));
	    sb.append("+------------+-----------------+----------+-------------+-------------+\n");

	    for (LineItem item : items) {
	        sb.append(String.format("| %-10d | %-15s | %-8d | ₹%-10.2f | ₹%-11.2f |\n",
	                item.getProduct().getId(),
	                item.getProduct().getName(),
	                item.getQuantity(),
	                item.getProduct().getPrice(),
	                item.calculateLineItemCost()));
	    }

	    sb.append("+------------+-----------------+----------+-------------+-------------+\n");
	    sb.append(String.format("Subtotal          : ₹%.2f\n", calculateOrderPrice()));

	    if (calculateOrderPrice() > 500) {
	        sb.append(String.format("Discount (10%%)     : ₹%.2f\n", totalDiscount()));
	        sb.append(String.format("Grand Total        : ₹%.2f\n", discountafterTotal()));
	    } else {
	        sb.append("No discount applied.\n");
	        sb.append(String.format("Total Amount       : ₹%.2f\n", calculateOrderPrice()));
	    }

	    sb.append(String.format("Payment Status     : %s\n", isPaid ? "✅ Paid" : "❌ Not Paid"));
	    sb.append(String.format("Delivery Partner   : %s\n", 
	        (deliveryPartnerName != null) ? deliveryPartnerName : "Not Assigned"));
	    sb.append("============================================================\n");

	    return sb.toString();
	}


}
