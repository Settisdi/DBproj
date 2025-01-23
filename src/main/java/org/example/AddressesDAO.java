package org.example;
import java.util.List;

public interface AddressesDAO {
    void insertAddress(Address address);
    List<Address> getAddressesByPersonID(int personID);
    void updateAddress(int addressID, String street, String city, String exactAddress, String postalCode, boolean isPrimary);
    void deleteAddress(int addressID);
}
