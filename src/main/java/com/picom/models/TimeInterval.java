package com.picom.models;

import com.picom.models.db.TableName;

import java.util.List;

public class TimeInterval extends AbstractEntity{

    private Long id;

    private Double timeSlot;

    private Integer nbreAd;

    private Float coefMulti;

    private List<Ad> adList;

    public TimeInterval(Long id, Double timeSlot, Integer nbreAd, Float coefMulti, List<Ad> adList) {
        super(TableName.TIME_INTERVAL);
        this.id = id;
        this.timeSlot = timeSlot;
        this.nbreAd = nbreAd;
        this.coefMulti = coefMulti;
        this.adList = adList;
    }
}
