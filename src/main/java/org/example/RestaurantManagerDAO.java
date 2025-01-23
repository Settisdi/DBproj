package org.example;
import java.util.List;

public interface RestaurantManagerDAO {
    void insertRestaurantManager(RestaurantManager manager);
    List<RestaurantManager> getAllRestaurantManagers();
    void deleteRestaurantManager(int managerID);
}

