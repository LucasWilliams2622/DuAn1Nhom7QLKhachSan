package com.example.duan1nhom7qlkhachsan.Model;

public class AppRoom {

    private Integer id;
    private String codeRoom,nameRoom,typeRoom,priceRoom,startDay,endDay,roomId;

    public AppRoom() {
    }

    public AppRoom(Integer id, String codeRoom, String nameRoom, String typeRoom, String priceRoom, String startDay, String endDay) {
        this.id = id;
        this.codeRoom = codeRoom;
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
        this.priceRoom = priceRoom;
        this.startDay = startDay;
        this.endDay = endDay;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
