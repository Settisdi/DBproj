package org.example;
public class Discount {
    private int discountID;
    private int discountPrice;

    public Discount(int discountID, int discountPrice) {
        this.discountID = discountID;
        this.discountPrice = discountPrice;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }
}

