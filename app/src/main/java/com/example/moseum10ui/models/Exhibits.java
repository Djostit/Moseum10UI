package com.example.moseum10ui.models;

public class Exhibits {
    public int Id;
    public String Name, DateReceipt, Status, Place;

    public Exhibits(int id, String name, String dateReceipt, String status, String place) {
        Id = id;
        Name = name;
        DateReceipt = dateReceipt;
        Status = status;
        Place = place;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDateReceipt() {
        return DateReceipt;
    }

    public String getStatus() {
        return Status;
    }

    public String getPlace() {
        return Place;
    }
}
