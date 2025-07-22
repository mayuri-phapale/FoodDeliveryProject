package com.aurionpro.payment.model;

import java.util.Scanner;

public class DebitCardPayment implements IPayment {


	@Override
	public boolean pay(double amount, Scanner sc) {
		sc.nextLine();
		System.out.println("enter the 16 digit pin");
		String cardnumber = sc.nextLine();
		
		
		System.out.println("enter the cvv");
		String cvv = sc.nextLine();
		
		if(cardnumber.matches("\\d{16}")&& cvv.matches("\\d{3}")) {
			System.out.println("payment of "+ amount + " "+ "is done by debit card" );
			return true;
		}
		else {
			System.out.println("invalid card details ");
			return false;
		}
		
		
	}
	
	@Override
	public String getName() {
		return "DebitCard";
	}


}
