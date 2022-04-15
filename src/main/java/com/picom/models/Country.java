package com.picom.models;

public class Country {

    private Long id;

    private String name;

    private String phoneIndicative;

    public Country(Long id, String name, String phoneIndicative) {
        this.id = id;
        this.name = name;
        this.phoneIndicative = phoneIndicative;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneIndicative() {
        return phoneIndicative;
    }

    public void setPhoneIndicative(String phoneIndicative) {
        this.phoneIndicative = phoneIndicative;
    }
}
