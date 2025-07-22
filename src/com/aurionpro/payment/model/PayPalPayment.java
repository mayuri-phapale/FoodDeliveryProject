package com.aurionpro.payment.model;

import java.util.Scanner;

public class PayPalPayment  implements IPayment{

	@Override
    public boolean pay(double amount, Scanner sc) {
        System.out.println("Enter your PayPal email:");
        String email = sc.next();

        System.out.println("Enter your PayPal password:");
        String password = sc.next();

        if (email.contains("@") && password.length() >= 4) {
            System.out.println(" PayPal payment of ₹" + amount + " successful.");
            return true;
        } else {
            System.out.println(" Invalid PayPal credentials. Payment failed.");
            return false;
        }
    }

    @Override
    public String getName() {
        return "PayPal";
    }

}
