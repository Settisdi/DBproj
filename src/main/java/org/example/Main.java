package org.example;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        //RESTAURANT
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        // Insert a restaurant
        restaurantDAO.insertRestaurant("Test Restaurant", 30.00, 5.00, "Shiraz", "123 Test St");
        // Get restaurants with paging
        List<Restaurant> restaurants = restaurantDAO.getRestaurantsPaged(0, 5);
        restaurants.forEach(r -> System.out.println(r.getName()));
        // Update a restaurant
        restaurantDAO.updateRestaurant(1, "Updated Name", 25.00, 3.00, "Tehran", "456 Updated St");
        // Delete a restaurant
        restaurantDAO.deleteRestaurant(1);


        //PERSON
        PersonDAO personDAO = new PersonDAOImpl();
        Person newPerson = new Person(0, "John", "Doe", "john.doe@example.com", "1234567890");
        //personDAO.insertPerson(newPerson);



        //CATEGORIES
        CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
        Categories newCategory = new Categories(0, 2, "Main Dishes", "Delicious main course options");
        categoriesDAO.insertCategory(newCategory);







    }
}