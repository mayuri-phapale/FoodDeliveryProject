package com.aurionpro.delivery.model;

import java.io.Serializable;

public interface IDelivery {
	 String getName();
	    double getDeliveryCharge();
	    String getEstimatedTime();
	    void deliverOrder(String customerName);

}
