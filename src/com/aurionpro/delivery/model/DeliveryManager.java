package com.aurionpro.delivery.model;

import java.io.*;
import java.util.*;

public class DeliveryManager {
    private List<IDelivery> activePartners;
    private List<IDelivery> inactivePartners;
    private final String ACTIVE_FILE = "activeDeliveries.txt";
    private final String INACTIVE_FILE = "inactiveDeliveries.txt";

    public DeliveryManager() {
        loadPartners();
    }

    @SuppressWarnings("unchecked")
    private void loadPartners() {
        activePartners = deserializeList(ACTIVE_FILE);
        inactivePartners = deserializeList(INACTIVE_FILE);

        if (activePartners == null) {
            activePartners = new ArrayList<>();
            activePartners.add(new ZeptoDelievry());
            activePartners.add(new ZomatoDelivery());
        }

        if (inactivePartners == null) {
            inactivePartners = new ArrayList<>();
            inactivePartners.add(new SwiggyDelivery());
        }

        savePartners();
    }

    private void savePartners() {
        serializeList(activePartners, ACTIVE_FILE);
        serializeList(inactivePartners, INACTIVE_FILE);
    }
    public IDelivery getBestPartner() {
        if (activePartners.isEmpty()) {
            System.out.println(" No delivery partners available.");
            return null;
        }

        IDelivery best = activePartners.get(0);
        for (IDelivery dp : activePartners) {
            if (dp.getDeliveryCharge() < best.getDeliveryCharge()) {
                best = dp;
            }
        }
        return best;
    }

    private void serializeList(List<IDelivery> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving file: " + fileName + " - " + e.getMessage());
        }
    }

    private List<IDelivery> deserializeList(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<IDelivery>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + fileName + " - " + e.getMessage());
            return null;
        }
    }

    public void showActivePartners() {
        System.out.println("\nActive Delivery Partners:");
        for (IDelivery d : activePartners) {
            System.out.println("- " + d.getName());
        }
    }

    public void showInactivePartners() {
        System.out.println("\nInactive Delivery Partners:");
        for (IDelivery d : inactivePartners) {
            System.out.println("- " + d.getName());
        }
    }

    public void addDeliveryPartner(String name) {
        for (IDelivery d : new ArrayList<>(inactivePartners)) {
            if (d.getName().equalsIgnoreCase(name)) {
                activePartners.add(d);
                inactivePartners.remove(d);
                savePartners();
                System.out.println(name + " added to active partners.");
                return;
            }
        }
        System.out.println("Delivery partner not found in inactive list.");
    }

    public void removeDeliveryPartner(String name) {
        for (IDelivery d : new ArrayList<>(activePartners)) {
            if (d.getName().equalsIgnoreCase(name)) {
                inactivePartners.add(d);
                activePartners.remove(d);
                savePartners();
                System.out.println(name + " removed from active partners.");
                return;
            }
        }
        System.out.println("Delivery partner not found in active list.");
    }

    public List<IDelivery> getActivePartners() {
        return activePartners;
    }

    public List<IDelivery> getInactivePartners() {
        return inactivePartners;
    }
} 