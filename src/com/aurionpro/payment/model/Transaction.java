package com.aurionpro.payment.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerName;
    private int customerId;
    private double amount;
    private String paymentMethod;
    private Date date;
    private String DeliveryPartner;

    public Transaction(String customerName,int customerId, double amount, String paymentMethod, Date date , String DeliveryPartner) {
        this.customerName = customerName;
        this.customerId=customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.DeliveryPartner=DeliveryPartner;
    }
    

    public String getDeliveryPartner() {
		return DeliveryPartner;
	}


	public void setDeliveryPartner(String deliveryPartner) {
		DeliveryPartner = deliveryPartner;
	}


	public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    StringBuilder sb = new StringBuilder();

	    sb.append("============================================================\n");
	    sb.append("                   🧾Transaction Receipt                     \n");
	    sb.append("============================================================\n");
	    sb.append(String.format(" Customer ID       : %s\n", customerId));
	    sb.append(String.format(" Customer Name     : %s\n", customerName));
	    sb.append(String.format(" Payment Method    : %s\n", paymentMethod));
	    sb.append(String.format(" Delivery Partner  : %s\n", DeliveryPartner));
	    sb.append(String.format(" Amount Paid       : ₹%.2f\n", amount));
	    sb.append(String.format(" Date & Time       : %s\n", sdf.format(date)));
	    sb.append("============================================================");

	    return sb.toString();
	}


	

}
