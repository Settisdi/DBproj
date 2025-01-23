package org.example;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private double minPurchase;
    private double deliveryFee;
    private String city;
    private String address;

    public Restaurant(int id, String name, double minPurchase, double deliveryFee, String city, String address) {
        this.id = id;
        this.name = name;
        this.minPurchase = minPurchase;
        this.deliveryFee = deliveryFee;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(double minPurchase) {
        this.minPurchase = minPurchase;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    // Getters and setters omitted for brevity
}

