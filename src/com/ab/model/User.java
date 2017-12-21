package com.ab.model;

/**
 * Created by guocui on 2017/10/18.
 */
public class User {
    private String name;
    private String phoneNumber;
    private String profile;
    private String info;

    public User(String name, String phoneNumber, String profile, String info) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.info = info;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profile='" + profile + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

