package org.example;
public class Address {
    private int addressID;
    private int personID;
    private String street;
    private String city;
    private String exactAddress;
    private String postalCode;
    private boolean isPrimary;

    public Address(int addressID, int personID, String street, String city, String exactAddress, String postalCode, boolean isPrimary) {
        this.addressID = addressID;
        this.personID = personID;
        this.street = street;
        this.city = city;
        this.exactAddress = exactAddress;
        this.postalCode = postalCode;
        this.isPrimary = isPrimary;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExactAddress() {
        return exactAddress;
    }

    public void setExactAddress(String exactAddress) {
        this.exactAddress = exactAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}

