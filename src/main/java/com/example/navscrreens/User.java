package com.example.navscrreens;

public class User {

    String UserEmail, Campus, Date, Time;

    public User() {
    }

    public User(String userEmail, String campus, String date, String time) {
        UserEmail = userEmail;
        Campus = campus;
        Date = date;
        Time = time;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String campus) {
        Campus = campus;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

}