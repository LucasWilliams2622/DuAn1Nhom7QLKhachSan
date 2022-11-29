package com.example.duan1nhom7qlkhachsan.Model;


public class AppService {
    private String idRoom, idService, nameService, priceService, typeService;
    private Integer id;

    public AppService() {
    }

    public AppService(Integer id, String idRoom, String idService, String nameService, String priceService, String typeService) {
        this.idRoom = idRoom;
        this.id = id;
        this.idService = idService;
        this.nameService = nameService;
        this.priceService = priceService;
        this.typeService = typeService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getPriceService() {
        return priceService;
    }

    public void setPriceService(String priceService) {
        this.priceService = priceService;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }
}
