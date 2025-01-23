package org.example;

import java.util.List;

public interface RestaurantDAO {
    void insertRestaurant(String name, double minPurchase, double deliveryFee, String city, String address);
    List<Restaurant> getRestaurantsPaged(int offset, int limit);
    void updateRestaurant(int id, String name, double minPurchase, double deliveryFee, String city, String address);
    void deleteRestaurant(int id);
}
