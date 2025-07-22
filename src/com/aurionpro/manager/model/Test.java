package com.aurionpro.manager.model;

import java.util.Scanner;

import com.aurionpro.foodtype.model.FoodManager;
import com.aurionpro.model.Customer;
import com.aurionpro.payment.model.PaymentManager;
import com.aurionpro.payment.model.TransactionManager;

public class Test {
	private TransactionManager transactionManager;
	private FoodManager foodmanager;
	private Manager manager;
	private CustomerManager customermanager;
	private AdminManager adminmanager;
	private PaymentManager payManager;
	private Scanner sc ;
	
	
	public Test() {
	    sc = new Scanner(System.in);
	    transactionManager = new TransactionManager();
	    payManager = new PaymentManager(sc, transactionManager);
	    foodmanager = new FoodManager(sc);
	    manager = new Manager();

	    adminmanager = new AdminManager(sc, manager, payManager); 
	}

	public static void main(String[] args) {
		
		Test test = new Test();
		test.displayMenu();
	}

	public void displayMenu() {
		while (true) {
			 System.out.println("\n==================== * Welcome to Shopping Cart * ====================");
			    System.out.println("1️  -> Customer Portal");
			    System.out.println("2️  -> Admin Portal");
			    System.out.println("3️  -> Exit");
			    System.out.println("======================================================================");			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
				case 1:
					System.out.println("customer mode selected >>>>>>>> \n");
					Customer customer = createCustomer();
					System.out.println("Welcome to Shopping Cart : " +customer.getName());
					customermanager = new CustomerManager(customer, manager, foodmanager, payManager, transactionManager);
					customermanager.CustomerMenu();  
					break;
				case 2:
					adminmanager.AdminMenu(); 
					manager.loadProducts();
					break;
				case 3:
					System.out.println("Thank you for visiting Shopping Cart!");
					return;
				default:
					System.out.println("Invalid choice. Try again.");
					break;
			}
		}
	}

	private Customer createCustomer() {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("enter the customer name");
		String name = sc.nextLine();
		

		System.out.println("enter the id");
		int id = sc.nextInt();
		
		return new Customer(id , name);
	}

}
