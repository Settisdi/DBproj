package org.example;

import java.util.List;

public interface CategoriesDAO {
    void insertCategory(Categories categories);
    List<Categories> getCategoriesByRestaurant(int restaurantID);
    void updateCategory(int categoryID, String name, String description);
    void deleteCategory(int categoryID);
}
