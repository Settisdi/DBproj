package org.example;
import java.util.List;

public interface ItemDAO {
    void insertItem(Item item);
    List<Item> getAllItems();
    void updateItem(int itemID, String name, double price, String ingredient, byte[] photo);
    void deleteItem(int itemID);
}

