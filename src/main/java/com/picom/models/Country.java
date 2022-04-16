package com.picom.models;

import com.picom.models.db.TableName;

public class Country extends AbstractEntity{

    private Long id;

    private String name;

    private String phoneIndicative;

    public Country(Long id, String name, String phoneIndicative) {
        super(TableName.COUNTRY);
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
