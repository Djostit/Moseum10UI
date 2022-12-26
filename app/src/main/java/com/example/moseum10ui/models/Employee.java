package com.example.moseum10ui.models;

public class Employee {
    public String Code, FullName;

    public Employee(String code, String fullName) {
        Code = code;
        FullName = fullName;
    }

    public String getCode() {
        return Code;
    }

    public String getFullName() {
        return FullName;
    }
}
