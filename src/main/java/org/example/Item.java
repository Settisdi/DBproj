package org.example;
public class Item {
    private int itemID;
    private double price;
    private String ingredient;
    private String name;
    private byte[] photo;

    public Item(int itemID, double price, String ingredient, String name, byte[] photo) {
        this.itemID = itemID;
        this.price = price;
        this.ingredient = ingredient;
        this.name = name;
        this.photo = photo;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}

