package com.aurionpro.manager.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aurionpro.model.Customer;
import com.aurionpro.model.LineItem;
import com.aurionpro.model.Order;
import com.aurionpro.model.Product;

public class Manager {
	private Customer customer;

	
	private List<Product> product;
	
	public Manager() {
		
		product = new ArrayList<>();
		loadProducts();
		
	}
	
	
//	private List<Product> product = new ArrayList<>();
//	private List<LineItem> lineitem = new ArrayList<>();
//	private List<Order> orders = new ArrayList<>();

	public void placeOrder(Customer customer, Order order) {
		if (order == null || order.getItems().isEmpty()) {
			System.out.println("Cart is empty. Cannot place order.");
			return;
		}

		customer.getOrders().add(order);

		System.out.println("Order Placed Successfully:");
		System.out.println("Customer: " + customer.getName() + " | ID: " + customer.getId());
		System.out.println(order);
		
		order.getItems().clear();
	}

	public void showMenu() {
	    System.out.println("\n==================== Shopping Cart Menu ====================");
	    System.out.println("+------------+---------------------+-------------+");
	    System.out.printf("| %-10s | %-19s | %-11s |\n", "Product ID", "Product Name", "Price");
	    System.out.println("+------------+---------------------+-------------+");

	    if (product == null || product.isEmpty()) {
	        System.out.printf("| %-44s |\n", "No products available.");
	    } else {
	        for (Product p : product) {
	            System.out.printf("| %-10d | %-19s | ₹%-10.2f |\n", p.getId(), p.getName(), p.getPrice());
	        }
	    }

	    System.out.println("+------------+---------------------+-------------+");
	    
	}


	public void searchProductByCategory(String name) {
	    System.out.println("\n==================== Product Search Results ====================");
	    System.out.printf("Category Searched: %s\n", name);
	    System.out.println("===============================================================");
	    System.out.println("+------------+---------------------+-------------+");
	    System.out.println("| Product ID | Product Name        | Price       |");
	    System.out.println("+------------+---------------------+-------------+");

	    boolean found = false;
	    name = name.trim().toLowerCase(); 

	    for (Product p : product) {
	        String prodCat = (p.getCategory() != null) ? p.getCategory().trim().toLowerCase() : "";

	        if (prodCat.equals(name)) {
	            System.out.printf("| %-10d | %-19s | ₹%-10.2f |\n",
	                    p.getId(),
	                    p.getName(),
	                    p.getPrice());
	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.println("|         No products found in this category.     |");
	    }

	    System.out.println("+------------+---------------------+-------------+");
	    System.out.println("===============================================================\n");
	}


	
	public void viewCart(Order order) {
	    if (order == null || order.getItems().isEmpty()) {
	        System.out.println(" The cart is empty.");
	        return;
	    }

	    System.out.println("\n==================== Your Shopping Cart ====================");
	    System.out.println("+------------+-----------------+----------+-------------+-------------+");
	    System.out.println("| Product ID | Product Name    | Quantity | Unit Price  | Total Price |");
	    System.out.println("+------------+-----------------+----------+-------------+-------------+");

	    for (LineItem item : order.getItems()) {
	        System.out.println(String.format("| %-10d | %-15s | %-8d | ₹%-10.2f | ₹%-11.2f |",
	                item.getProduct().getId(),
	                item.getProduct().getName(),
	                item.getQuantity(),
	                item.getProduct().getPrice(),
	                item.calculateLineItemCost()));
	    }

	    System.out.println("+------------+-----------------+----------+-------------+-------------+");
	    

	    System.out.println("===========================================================\n");
	}

//
	public void addItem(String name , int quantity ,Customer customer ,Order order) {
		for(Order ord : customer.getOrders()) {
			for(LineItem item : ord.getItems()) {
					if (item.getProduct().getName().equalsIgnoreCase(name)) {
						System.out.println("item is already present in the cart");
						return;
					}
			}
		}
		Product selected = null;
		for (Product p : product) {
			if (p.getName().equalsIgnoreCase(name)) {
				selected = p;
				break;
			}
		}

		if (selected == null) {
			System.out.println("Product not found.");
			return;
		}
		int lineId = order.getItems().size() + 1; 
		LineItem newItem = new LineItem(lineId, quantity, selected);
		order.addLineItem(newItem);
		System.out.println("item added to cart succesfully");

	}

	public void updateCart(int id, int quantity ,Order order) {

		boolean found = false;
		for (LineItem item : order.getItems()) {
			if (item.getProduct().getId() == id) {
				item.setQuantity(quantity);
				System.out.println("update the cart ");
				found = true;
				return;
			}
		}
		if (!found) {
			System.out.println("product not found");
		}

	}
//
	public void removeProduct(String name ,Order order) {
		boolean itemFound = false;
		for (LineItem item : order.getItems()) {
			if (item.getProduct().getName().equalsIgnoreCase(name)) {
				order.getItems().remove(item);
				System.out.println("product remove from the cart");
				itemFound = true;
				break;
			}

		}
		if (!itemFound) {
			System.out.println("product not found in the cart");
		}
	}
//
	public void addProduct(int id, String name, double price , String category) {
		for (Product p : product) {
			if (p.getName().equalsIgnoreCase(name)) {
				System.out.println("product already exists");
				return;
			}
			if(p.getId()== id) {
				System.out.println("id already exists");
				return;
			}
		}

		Product prod = new Product(id, name, price, category);
		product.add(prod);
		saveProducts();
		System.out.println("product added successfully");
	}
//
	public void removeProductFromAdmin(int id) {
		boolean isremove = false;
		for (Product item : product) {
			if (item.getId() == id) {
				product.remove(item);
				System.out.println("product is removed from the cart");
				isremove = true;
				saveProducts();
				break;
			}
		}
		if (!isremove) {
			System.out.println("product not found in cart");
		}

	}
//
	public void saveProducts() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream("product.txt");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(product);
			System.out.println("serialization successfully");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
//
	public void loadProducts() {
	    File file = new File("product.txt");
	    if (!file.exists()) {

//	        product = new ArrayList<>();
	    	product.add(new Product(101, "Oreo", 20, "Snacks"));
	    	product.add(new Product(102, "Parle-G", 10, "Snacks"));
	    	product.add(new Product(103, "Hide & Seek", 50, "Snacks"));
	    	product.add(new Product(104, "Dark Fantasy", 70, "Snacks"));

	    	product.add(new Product(105, "Thums Up", 45, "Drinks"));
	    	product.add(new Product(106, "Coca Cola", 40, "Drinks"));
	    	product.add(new Product(107, "Maaza", 35, "Drinks"));

	        saveProducts();
	        return;
	    }

	    FileInputStream fis = null;
	    ObjectInputStream ois = null;
	    try {
	        fis = new FileInputStream("product.txt");
	        ois = new ObjectInputStream(fis);

	        product = (List<Product>) ois.readObject();
	        System.out.println("deserialization successfully");

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (fis != null) fis.close();
	            if (ois != null) ois.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
