package org.example;
public class Admin {
    private int adminID;
    private String userName;
    private String password;

    public Admin(int adminID, String userName, String password) {
        this.adminID = adminID;
        this.userName = userName;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

