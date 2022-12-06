package com.example.duan1nhom7qlkhachsan.Model;


public class AppBookedService {
    private Integer id;
    private String codeRoom, codeService, nameService, priceService, typeService,serviceId;


    public AppBookedService() {
    }

    public AppBookedService(Integer id, String codeRoom, String codeService, String nameService, String priceService, String typeService) {
        this.id = id;
        this.codeRoom = codeRoom;
        this.codeService = codeService;
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

    public String getCodeRoom() {
        return codeRoom;
    }

    public void setCodeRoom(String codeRoom) {
        this.codeRoom = codeRoom;
    }

    public String getCodeService() {
        return codeService;
    }

    public void setCodeService(String codeService) {
        this.codeService = codeService;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
