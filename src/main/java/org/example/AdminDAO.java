package org.example;
import java.util.List;

public interface AdminDAO {
    void insertAdmin(Admin admin);
    List<Admin> getAllAdmins();
    void deleteAdmin(int adminID);
}

