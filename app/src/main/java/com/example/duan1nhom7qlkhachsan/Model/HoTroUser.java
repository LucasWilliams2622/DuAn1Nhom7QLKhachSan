package com.example.duan1nhom7qlkhachsan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class HoTroUser implements Parcelable {
    String email;
    String name;
    String phone;
    String text;

    public HoTroUser(String email, String name, String phone, String text) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.text = text;
    }

    protected HoTroUser(Parcel in) {
        email = in.readString();
        name = in.readString();
        phone = in.readString();
        text = in.readString();
    }

    public static final Creator<HoTroUser> CREATOR = new Creator<HoTroUser>() {
        @Override
        public HoTroUser createFromParcel(Parcel in) {
            return new HoTroUser(in);
        }

        @Override
        public HoTroUser[] newArray(int size) {
            return new HoTroUser[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(text);
    }
}
