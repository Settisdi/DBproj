package org.example;
import java.util.List;

public interface DiscountDAO {
    void insertDiscount(Discount discount);
    void updateDiscount(int discountID, int discountPrice);
    void deleteDiscount(int discountID);
    List<Discount> getAllDiscounts();
}

