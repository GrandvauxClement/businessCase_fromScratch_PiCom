package com.picom.models.db;

public enum TableName {
    USER("user"),
    CITY("city"),
    COUNTRY("country");

    private final String name;

    TableName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
