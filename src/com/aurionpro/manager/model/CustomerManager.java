package com.aurionpro.manager.model;

import java.util.Scanner;

import com.aurionpro.foodtype.model.FoodManager;
import com.aurionpro.model.Customer;
import com.aurionpro.model.Order;
import com.aurionpro.payment.model.PaymentManager;
import com.aurionpro.payment.model.TransactionManager;

public class CustomerManager {
	private TransactionManager transactionManager;
	private PaymentManager paymanager;
	private FoodManager foodmanager;
	private Manager manager;
	private Scanner sc ;
	private Customer customer;
	private Order order;
	
	public CustomerManager(Customer customer ,Manager manager , FoodManager foodmanager , PaymentManager paymanager , TransactionManager transactionManager) {
		sc = new Scanner(System.in);
		this.customer= customer;
		this.manager = manager;
		this.foodmanager=foodmanager;
		this.paymanager= paymanager;
		this.transactionManager =  transactionManager;
		order = new Order(0, null, customer);
	}
	 
public void CustomerMenu() {
	
	while (true) {
		System.out.println("\n==================== 🛒 Shopping Menu ====================");
		System.out.println("1 -> show menu");
		System.out.println("2 -> Search product by name");
		System.out.println("3 -> add product to cart");
		System.out.println("4 -> view cart");
		System.out.println("5 -> update cart");
		System.out.println("6 -> remove product");
		System.out.println("7 -> Make Payment");
		System.out.println("8 -> View Transaction history");
		System.out.println("9 -> exit");
		System.out.println("===========================================================\n");
		System.out.println("enter your choice");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			manager.showMenu();
			break;
		case 2:
			searchByName();
			break;
		case 3:
			addItemToCart();
			break;
		case 4:
			manager.viewCart(order);
			break;
		case 5:
			updateToCart();
			break;
		case 6:
			removeProduct();
			break;
		case 7:
			paymanager.makePayment(order, customer.getName(), customer.getId());
			break;
		case 8 :
			transactionManager.ShowTransactionByCustomer(customer.getId(), customer.getName());
			break;
		case 9:
			System.out.println("Thank you ! exiting menu !");
			return;
		default :
			System.out.println("invalid choice");
			break;
		}

	}
		
	}


	private void searchByName() {
	
		System.out.println("Enter the prduct you want to search");
		sc.nextLine();
		String name = sc.nextLine();
		
		manager.searchProductByCategory(name);
		
	}

//	private void showFoodCusine() {
//		System.out.println("-----menu--------");
//		foodmanager.showFoodCusine();
//		System.out.println("enter your choice");
//		int choice = sc.nextInt();
//		
//		foodmanager.showMenuType(choice);
//	
//}

	private void addItemToCart() {
		sc.nextLine();
		System.out.println("enter the name");
		String name = sc.nextLine();
	
		System.out.println("enter the quantity");
		int quantity = sc.nextInt();
	
		manager.addItem(name, quantity , customer , order);
	
	}
	
	private void removeProduct() {
		sc.nextLine();
		System.out.println("enter the product name");
		String name = sc.nextLine();

		manager.removeProduct(name, order);

	}
	
	public void updateToCart() {
		System.out.println("enter id");
		int id = sc.nextInt();

		System.out.println("enter quantity");
		int quantity = sc.nextInt();

		manager.updateCart(id, quantity, order);
	}

}
