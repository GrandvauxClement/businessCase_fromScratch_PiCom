package com.picom.models;

import com.picom.models.db.TableName;

public class Stop extends AbstractEntity{

    private Long id;

    private String name;

    private Float latitude;

    private Float longitude;

    private Area area;

    private String address_ip;

    public Stop(Long id, String name, Float latitude, Float longitude, Area area, String address_ip) {
        super(TableName.STOP);
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.address_ip = address_ip;
    }
    // Create without Area
    public Stop(Long id, String name, Float latitude, Float longitude, String address_ip) {
        super(TableName.STOP);
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address_ip = address_ip;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getAddressIP() {
        return address_ip;
    }

    public void setAddressIP(String adressIP) {
        this.address_ip = adressIP;
    }
}
