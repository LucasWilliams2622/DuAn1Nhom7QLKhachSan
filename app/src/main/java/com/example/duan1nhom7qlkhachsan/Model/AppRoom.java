package com.example.duan1nhom7qlkhachsan.Model;

public class AppRoom {
    private String idRoom,nameRoom,typeRoom,priceRoom,startDay,endDay;

    public AppRoom() {
    }

    public AppRoom(String idRoom, String nameRoom, String typeRoom, String priceRoom, String startDay, String endDay) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
        this.priceRoom = priceRoom;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public String getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(String priceRoom) {
        this.priceRoom = priceRoom;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
