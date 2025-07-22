package com.aurionpro.payment.model;

import java.io.Serializable;
import java.util.Scanner;

public interface IPayment extends Serializable{
	public boolean pay(double amount ,Scanner sc);
	public String getName();

}
