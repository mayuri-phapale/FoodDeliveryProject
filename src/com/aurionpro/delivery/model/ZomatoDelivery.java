package com.aurionpro.delivery.model;

import java.io.Serializable;

public class ZomatoDelivery implements IDelivery,Serializable{
	 private static final long serialVersionUID = 1L;
	@Override
    public String getName() {
        return "Zomato";
    }

    @Override
    public double getDeliveryCharge() {
        return 40.0;
    }

    @Override
    public String getEstimatedTime() {
        return "30 mins";
    }

    @Override
    public void deliverOrder(String customerName) {
        System.out.println("Order delivered to " + customerName + " via Zomato!");
    }
}
