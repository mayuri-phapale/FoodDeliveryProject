package com.aurionpro.manager.model;

import java.util.Scanner;

import com.aurionpro.delivery.model.DeliveryManager;
import com.aurionpro.model.Customer;
import com.aurionpro.model.Order;
import com.aurionpro.payment.model.PaymentManager;
import com.aurionpro.payment.model.TransactionManager;

public class AdminManager {

    private Manager manager;
    private PaymentManager paymentManager;
    private DeliveryManager deliverymanager;
    private Scanner sc;

    public AdminManager(Scanner sc, Manager manager, PaymentManager paymentManager) {
        this.sc = sc;
        this.manager = manager;
        this.paymentManager = paymentManager;
        this.deliverymanager = new DeliveryManager(); 
    }


    public void AdminMenu() {
        while (true) {
        	System.out.println("=======================AdminMenu=========================\n");
            System.out.println("1 -> Show Product Menu");
            System.out.println("2 -> Add Product");
            System.out.println("3 -> Remove Product");
            System.out.println("4 -> Add Payment Method");
            System.out.println("5 -> Remove Payment Method");
            System.out.println("6 -> Add Delivery Partner");
            System.out.println("7 -> Remove Delivery Partner");
            System.out.println("8 -> Exit");
            System.out.println("===========================================================\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    manager.showMenu();
                    break;
                case 2:
                    addProductName();
                    break;
                case 3:
                    removeProductAdmin();
                    break;
                case 4:
                    addPaymentMethod();
                    break;
                case 5:
                    removePaymentMethod();
                    break;
                case 6:
                	addDeliveryPartner();
                	break;
                case 7: 
                	removeDeliveryPartner();
                	break;
                case 8:
                    System.out.println("Exiting Admin Mode.");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void removeProductAdmin() {
        System.out.print("Enter product ID to remove: ");
        int id = sc.nextInt();
        manager.removeProductFromAdmin(id);
    }

    private void addProductName() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter product category: ");
        String category = sc.nextLine();
        
        
       

        manager.addProduct(id, name, price, category);
    }

    private void addPaymentMethod() {
    	if (paymentManager.getInactivePayments().isEmpty()) {
            System.out.println("\n No inactive payment methods available to add.");
            return;
        }
    	
        paymentManager.showInactiveMethods(); 
        System.out.print("Enter payment method name to add: ");
        String method = sc.nextLine();
        paymentManager.addPaymentMethod(method);  
    }

    private void removePaymentMethod() {
    	if (paymentManager.getActivePayments().isEmpty()) {
            System.out.println("\n No inactive payment methods available to remove.");
            return;
        }
        paymentManager.showActiveMethods();
 
        System.out.print("Enter payment method name to remove: ");
        String method = sc.nextLine();
        paymentManager.removePaymentMethod(method);
    }
    private void addDeliveryPartner() {
        if (deliverymanager.getInactivePartners().isEmpty()) {
            System.out.println("\nNo inactive delivery partners available to add.");
            return;
        }
        deliverymanager.showInactivePartners();
   
        System.out.print("Enter delivery partner name to add: ");
        String name = sc.nextLine();
        deliverymanager.addDeliveryPartner(name);
    }

    private void removeDeliveryPartner() {
        if (deliverymanager.getActivePartners().isEmpty()) {
            System.out.println("\nNo active delivery partners available to remove.");
            return;
        }
        deliverymanager.showActivePartners();
   
        System.out.print("Enter delivery partner name to remove: ");
        String name = sc.nextLine();
        deliverymanager.removeDeliveryPartner(name);
    }
}
