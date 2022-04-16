package com.picom.models;

import com.picom.models.db.TableName;

public class Area extends AbstractEntity{

   private Long id;

   private String name;

   private Float price;

    public Area(Long id, String name, Float price) {
        super(TableName.AREA);
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
