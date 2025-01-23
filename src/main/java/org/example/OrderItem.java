package org.example;
public class OrderItem {
    private int orderItemID;
    private int cartID;
    private int feedbackID;
    private int itemCategoryID;

    public OrderItem(int orderItemID, int cartID, int feedbackID, int itemCategoryID) {
        this.orderItemID = orderItemID;
        this.cartID = cartID;
        this.feedbackID = feedbackID;
        this.itemCategoryID = itemCategoryID;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getItemCategoryID() {
        return itemCategoryID;
    }

    public void setItemCategoryID(int itemCategoryID) {
        this.itemCategoryID = itemCategoryID;
    }
}

