package com.aurionpro.payment.model;

import java.util.Scanner;

public class UpiPayment implements IPayment{


	
	@Override
	public boolean pay(double amount, Scanner sc) {
		System.out.println("enter the upi id :");
		 sc.nextLine();
		String upiId = sc.nextLine();
		
		
		 if (upiId.matches("^[\\w.-]+@[a-zA-Z]+$")) {
			System.out.println("payment of " + amount + "id done by upi" +upiId);
			return true;
		}
		else {
			System.out.println("you entered invalid upi");
			return false;
		}
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Upi";
	}

	

}
