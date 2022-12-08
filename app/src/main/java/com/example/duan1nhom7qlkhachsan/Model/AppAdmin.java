package com.example.duan1nhom7qlkhachsan.Model;

public class AppAdmin {
    private Integer id;
    private String idAdmin,emailAdmin,nameAdmin,passwordAdmin,role,codeAdmin;

    public AppAdmin() {
    }

    public AppAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public AppAdmin(Integer id,String idAdmin, String emailAdmin, String nameAdmin, String passwordAdmin, String role) {
        this.id = id;
        this.idAdmin = idAdmin;
        this.emailAdmin = emailAdmin;
        this.nameAdmin = nameAdmin;
        this.passwordAdmin = passwordAdmin;
        this.role = role;
    }

    public AppAdmin(String nameAdmin,  String emailAdmin, String passwordAdmin) {
        this.nameAdmin = nameAdmin;

        this.emailAdmin = emailAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeAdmin() {
        return codeAdmin;
    }

    public void setCodeAdmin(String codeAdmin) {
        this.codeAdmin = codeAdmin;
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
