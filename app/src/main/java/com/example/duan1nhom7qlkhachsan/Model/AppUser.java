package com.example.duan1nhom7qlkhachsan.Model;

public class AppUser {
    private int  idUser;
    private String nameUser,emailUser,phoneNumUser,idRoom;

    public AppUser(int idUser, String nameUser, String emailUser, String phoneNumUser, String idRoom) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneNumUser = phoneNumUser;
        this.idRoom = idRoom;
    }

    public AppUser() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneNumUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneNumUser = phoneUser;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
}
