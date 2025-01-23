package org.example;
public class RestaurantManager {
    private int managerID;
    private int restaurantID;

    public RestaurantManager(int managerID, int restaurantID) {
        this.managerID = managerID;
        this.restaurantID = restaurantID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
}

