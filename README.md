# 🛒 Shopping Cart Application

A Java-based **console application** that simulates a complete shopping cart system with features like product management, cart operations, customer orders, payments, transaction history, and admin functionalities.

---

## 📌 Features

###  Customer Portal:
- View Product Menu
- Search Products by Name and category
- Add/Update/Remove Products in Cart
- View Cart Summary
- Make Payments (UPI/Debit Card)
- View Transaction History (Stored per Customer)
- Order Summary with Discounts & Delivery Partner

###  Admin Portal:
- Add/Remove Products (with category)
- Manage Payment Methods (Add/Remove)
- Manage Delivery Partners (Add/Remove)

---

##  Technologies Used

| Tool/Library       | Purpose                            |
|--------------------|------------------------------------|
| **Java**           | Core application logic             |
| **OOP Concepts**   | Classes, Inheritance, Polymorphism |
| **Serialization**  | Storing & retrieving product data  |
| **Collections**    | `ArrayList`, `List` for data mgmt  |             
| **Scanner**        | Console input                      |
| **Modular Packages** | Organized codebase: `model`, `manager`, `payment`, etc. |

---


##  File-Based Persistence

The app uses **local file storage** for retaining state:

| File                | Description                                 |
|---------------------|---------------------------------------------|
| `product`           | Serialized list of `Product` objects        |
| `transactions`      | Serialized list of all customer transactions |
| `activePayments`    | Active payment options (text format)        |
| `inactivePayments`  | Inactive payment options                    |
| `activeDeliveries`  | Active delivery partners                    |
| `inactiveDeliveries`| Inactive delivery partners                  |

---

##  How to Run

1. Open the project in **Eclipse** 
2. Set the `Test.java` (inside `com.aurionpro.model`) as the entry point
3. Run the program
4. Interact with the menu to test Customer/Admin features

---


## 📁 Project Structure

FoodDeliveryProject/
│
├── com.aurionpro.model/
│ ├── Product.java
│ ├── Customer.java
│ ├── LineItem.java
│ ├── Order.java
│
├── com.aurionpro.manager.model/
│ ├── Manager.java
│ ├── AdminManager.java
│ ├── CustomerManager.java
│ ├── Test.java (Main Entry Point)
│
├── com.aurionpro.payment.model/
│ ├── IPayment.java
│ ├── UpiPayment.java
│ ├── DebitCardPayment.java
│ ├── Transaction.java
│ ├── PaymentManager.java
│ ├── TransactionManager.java
│
├── com.aurionpro.delivery.model/
│ ├── DeliveryManager.java
│ ├── IDelivery.java
│ ├── SwiggyDelivery.java
│ ├── ZeptoDelivery.java
│ └── ZomatoDelivery.java

