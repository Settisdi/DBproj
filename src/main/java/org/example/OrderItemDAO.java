package org.example;
import java.util.List;

public interface OrderItemDAO {
    void insertOrderItem(OrderItem item);
    List<OrderItem> getOrderItemsByCartID(int cartID);
    void deleteOrderItem(int orderItemID);
}

