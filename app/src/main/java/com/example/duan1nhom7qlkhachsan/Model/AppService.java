package com.example.duan1nhom7qlkhachsan.Model;

public class AppService {
    private String idRoom ,idService,nameService,priceService,timeUseService;

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

    public String getTimeUseService() {
        return timeUseService;
    }

    public void setTimeUseService(String timeUseService) {
        this.timeUseService = timeUseService;
    }

    public AppService(String idRoom, String idService, String nameService, String priceService, String timeUseService) {
        this.idRoom = idRoom;
        this.idService = idService;
        this.nameService = nameService;
        this.priceService = priceService;
        this.timeUseService = timeUseService;
    }

    public AppService() {
    }
}
