package com.example.duan1nhom7qlkhachsan.Model;

public class AppAdmin {
    private String idAdmin,emailAdmin,nameAdmin,passwordAdmin,role;

    public AppAdmin() {
    }

    public AppAdmin(String idAdmin, String emailAdmin, String nameAdmin, String passwordAdmin, String role) {
        this.idAdmin = idAdmin;
        this.emailAdmin = emailAdmin;
        this.nameAdmin = nameAdmin;
        this.passwordAdmin = passwordAdmin;
        this.role = role;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
