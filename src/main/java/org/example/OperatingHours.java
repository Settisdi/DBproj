package org.example;
public class OperatingHours {
    private int operatingHoursID;
    private int restaurantID;
    private String dayOfWeek;
    private String openTime;
    private String closeTime;

    public OperatingHours(int operatingHoursID, int restaurantID, String dayOfWeek, String openTime, String closeTime) {
        this.operatingHoursID = operatingHoursID;
        this.restaurantID = restaurantID;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public int getOperatingHoursID() {
        return operatingHoursID;
    }

    public void setOperatingHoursID(int operatingHoursID) {
        this.operatingHoursID = operatingHoursID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}

