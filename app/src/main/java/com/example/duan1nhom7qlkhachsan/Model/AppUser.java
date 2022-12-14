package com.example.duan1nhom7qlkhachsan.Model;

public class AppUser {
    private Integer id ;
    private String idUser,nameUser,emailUser,phoneNumUser,idRoom, codeUser,imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumUser() {
        return phoneNumUser;
    }

    public void setPhoneNumUser(String phoneNumUser) {
        this.phoneNumUser = phoneNumUser;
    }

    public AppUser(Integer id, String idUser, String nameUser, String emailUser, String phoneNumUser, String idRoom,String imageUrl) {
        this.id =id;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneNumUser = phoneNumUser;
        this.idRoom = idRoom;
        this.imageUrl = imageUrl;
    }
    public AppUser(String nameUser, String emailUser, String phoneNumUser) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneNumUser = phoneNumUser;
    }

    public AppUser() {
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
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
