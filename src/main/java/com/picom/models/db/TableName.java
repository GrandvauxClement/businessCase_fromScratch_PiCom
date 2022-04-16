package com.picom.models.db;

public enum TableName {
    USER("user"),
    CITY("city"),
    COUNTRY("country"),
    ROLE("role");

    private final String name;

    TableName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
