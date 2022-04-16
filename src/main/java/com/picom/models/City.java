package com.picom.models;

import com.picom.models.db.TableName;

public class City extends AbstractEntity{

    private Long id;

    private String name;

    private Country country;

    public City(Long id, String name, Country country) {
        super(TableName.CITY);
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
