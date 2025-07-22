package com.aurionpro.delivery.model;

import java.io.Serializable;

public class SwiggyDelivery implements IDelivery , Serializable{
	 private static final long serialVersionUID = 1L;
	@Override
    public String getName() {
        return "swiggy";
    }

    @Override
    public double getDeliveryCharge() {
        return 50.0;
    }

    @Override
    public String getEstimatedTime() {
        return "20 mins";
    }

    @Override
    public void deliverOrder(String customerName) {
        System.out.println("Order delivered to " + customerName + " via swiggy!");
    }

}
