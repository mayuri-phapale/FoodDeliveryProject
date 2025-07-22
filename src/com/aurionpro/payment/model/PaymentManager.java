package com.aurionpro.payment.model;

import java.io.*;
import java.util.*;

import com.aurionpro.delivery.model.DeliveryManager;
import com.aurionpro.delivery.model.IDelivery;
import com.aurionpro.model.Order;

public class PaymentManager {
    private TransactionManager tranManager;
    private Scanner sc;
    private List<IPayment> activePayments;
    private List<IPayment> inactivePayments;
    private final String ACTIVE_FILE = "activePayments.txt";
    private final String INACTIVE_FILE = "inactivePayments.txt";

    public PaymentManager(Scanner sc, TransactionManager tranManager) {
        this.sc = sc;
        this.tranManager = tranManager;
        loadPayments();
    }
    

    public List<IPayment> getActivePayments() {
		return activePayments;
	}


	public List<IPayment> getInactivePayments() {
		return inactivePayments;
	}


	private void loadPayments() {
        activePayments = deserializeList(ACTIVE_FILE);
        inactivePayments = deserializeList(INACTIVE_FILE);

        if (activePayments == null) {
            activePayments = new ArrayList<>();
            activePayments.add(new DebitCardPayment());
            activePayments.add(new UpiPayment());
        }

        if (inactivePayments == null) {
            inactivePayments = new ArrayList<>();
            inactivePayments.add(new PayPalPayment());
        }

        savePayments();
    }

    private void savePayments() {
        serializeList(activePayments, ACTIVE_FILE);
        serializeList(inactivePayments, INACTIVE_FILE);
    }

    private void serializeList(List<IPayment> list, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving payments: " + e.getMessage());
        }
    }

    private List<IPayment> deserializeList(String filename) {
        File file = new File(filename);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<IPayment>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading payments: " + e.getMessage());
            return null;
        }
    }

    public void addPaymentMethod(String methodName) {
        for (IPayment method : new ArrayList<>(inactivePayments)) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                activePayments.add(method);
                inactivePayments.remove(method);
                savePayments();
                System.out.println(methodName + " added successfully.");
                return;
            }
        }
        System.out.println("Method not found in inactive list.");
    }

    public void removePaymentMethod(String methodName) {
        for (IPayment method : new ArrayList<>(activePayments)) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                inactivePayments.add(method);
                activePayments.remove(method);
                savePayments();
                System.out.println(methodName + " removed successfully.");
                return;
            }
        }
        System.out.println("Method not found in active list.");
    }

    public void showActiveMethods() {
        System.out.println("\nActive Payment Methods:");
        for (int i = 0; i < activePayments.size(); i++) {
            System.out.println((i + 1) + " -> " + activePayments.get(i).getName());
        }
    }

    public void makePayment(Order order, String customerName, int customerId) {
        if (order.isPaid()) {
            System.out.println("Payment is already done for this order");
            return;
        }

        showActiveMethods();
        System.out.println("Enter your choice:");
        int choice = sc.nextInt();

        if (choice < 1 || choice > activePayments.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        IPayment payment = activePayments.get(choice - 1);
        double amount = order.discountafterTotal();
        boolean success = payment.pay(amount, sc);

        if (success) {
            order.setPaid(true);
            DeliveryManager deliveryManager = new DeliveryManager();
            IDelivery selectedPartner = deliveryManager.getBestPartner();

            order.setDeliveryPartnerName(selectedPartner.getName());
            order.setDeliveryCharge(selectedPartner.getDeliveryCharge());

            Transaction txt = new Transaction(customerName, customerId, amount, payment.getName(), new Date(), selectedPartner.getName());
            tranManager.addTransaction(txt);

            System.out.println("Transaction saved in the history");
            System.out.println(order);
        } else {
            System.out.println("Payment failed.");
        }
    }

    public void showInactiveMethods() {
        System.out.println("\nInactive Payment Methods:");
        for (IPayment method : inactivePayments) {
            System.out.println("- " + method.getName());
        }
    }
}
