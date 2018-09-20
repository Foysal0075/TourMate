package com.codex.tourmate;

import android.graphics.Bitmap;

public class User {
    private String name;
    private String email;
    private String contactNo;
    private String address;
    private String profilePicData;
    private String uId;

    public User(String name, String email, String contactNo, String address, String profilePicData, String uId) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.profilePicData = profilePicData;
        this.uId = uId;
    }

    public User(String name, String email, String contactNo, String address, String uId) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.uId = uId;
    }

    public User(){

    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public String getProfilePicData() {
        return profilePicData;
    }

    public String getuId() {
        return uId;
    }
}
