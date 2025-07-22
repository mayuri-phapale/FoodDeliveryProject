package com.aurionpro.payment.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
	private List<Transaction> history;

	public TransactionManager() {
		this.history = new ArrayList<>();
		loadTransaction();
	}

	public void addTransaction(Transaction transaction) {
		history.add(transaction);
		saveTransaction();
	}

	public void showTransaction() {
		if (history.isEmpty()) {
			System.out.println("No transactions are done till now.");
			return;
		}

		System.out.println("\n------------------ Transaction History ------------------");
		for (Transaction t : history) {
			System.out.println(t);
		}
		System.out.println("---------------------------------------------------------");
	}
	
	public void ShowTransactionByCustomer(int customerId ,String name) {
		boolean isfound = false;
		
		for(Transaction t : history) {
			if(t.getCustomerId()== customerId && t.getCustomerName().equalsIgnoreCase(name)) {
				if(!isfound) {
					System.out.println("transaction history of customer id :" +customerId);;
					isfound = true;
				}
				System.out.println(t);
			}
		}
	}

	private void saveTransaction() {
		try (FileOutputStream fos = new FileOutputStream("transactions.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(history);
			System.out.println(" Transaction serialized successfully.");

		} catch (IOException e) {
			System.out.println("Error saving transaction: " + e.getMessage());
		}
	}

	private void loadTransaction() {
		File file = new File("transactions.txt");
		if (!file.exists()) {
			return;  
		}

		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream ois = new ObjectInputStream(fis)) {

			history = (List<Transaction>) ois.readObject();
			System.out.println(" Transaction deserialized successfully.");

		} catch (Exception e) {
			System.out.println("Error loading transaction: " + e.getMessage());
			history = new ArrayList<>();  // fallback
		}
	}
}
