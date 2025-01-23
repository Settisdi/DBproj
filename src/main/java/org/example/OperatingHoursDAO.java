package org.example;
import java.util.List;

public interface OperatingHoursDAO {
    void insertOperatingHours(OperatingHours hours);
    List<OperatingHours> getOperatingHoursByRestaurant(int restaurantID);
    void updateOperatingHours(int id, String dayOfWeek, String openTime, String closeTime);
    void deleteOperatingHours(int id);
}

