package org.example;
public class ItemCategory {
    private int itemCategoryID;
    private int categoryID;
    private int itemID;

    // Constructor
    public ItemCategory(int itemCategoryID, int categoryID, int itemID) {
        this.itemCategoryID = itemCategoryID;
        this.categoryID = categoryID;
        this.itemID = itemID;
    }

    public int getItemCategoryID() {
        return itemCategoryID;
    }

    public void setItemCategoryID(int itemCategoryID) {
        this.itemCategoryID = itemCategoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}

