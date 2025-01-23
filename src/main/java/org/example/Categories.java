package org.example;

public class Categories {
    private int categoryID;
    private int restaurantID;
    private String name;
    private String description;

    // Constructor
    public Categories(int categoryID, int restaurantID, String name, String description) {
        this.categoryID = categoryID;
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", restaurantID=" + restaurantID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

