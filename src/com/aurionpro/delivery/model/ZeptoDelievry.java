package com.aurionpro.delivery.model;

import java.io.Serializable;

public class ZeptoDelievry implements IDelivery ,Serializable{
	 private static final long serialVersionUID = 1L;
	@Override
    public String getName() {
        return "Zepto";
    }

    @Override
    public double getDeliveryCharge() {
        return 30.0;
    }

    @Override
    public String getEstimatedTime() {
        return "25 mins";
    }

    @Override
    public void deliverOrder(String customerName) {
        System.out.println("Order delivered to " + customerName + " via Zepto!");
    }
}
