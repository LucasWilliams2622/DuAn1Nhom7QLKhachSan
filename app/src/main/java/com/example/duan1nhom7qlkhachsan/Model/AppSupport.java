package com.example.duan1nhom7qlkhachsan.Model;

public class AppSupport {
    private Integer id;
    private String nameCustomer,phoneNumberCustomer,contentSupport,supportId;

    public AppSupport() {
    }

    public AppSupport(Integer id, String nameCustomer, String phoneNumberCustomer, String contentSupport) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.phoneNumberCustomer = phoneNumberCustomer;
        this.contentSupport = contentSupport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumberCustomer() {
        return phoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(String phoneNumberCustomer) {
        this.phoneNumberCustomer = phoneNumberCustomer;
    }

    public String getContentSupport() {
        return contentSupport;
    }

    public void setContentSupport(String contentSupport) {
        this.contentSupport = contentSupport;
    }

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }
}
