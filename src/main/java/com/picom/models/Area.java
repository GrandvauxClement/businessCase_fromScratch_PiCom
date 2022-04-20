package com.picom.models;

import com.picom.models.db.TableName;

import java.util.List;

public class Area extends AbstractEntity{

   private Long id;

   private String name;

   private Float price;

   private List<TimeInterval> timeIntervalList;

    public Area(Long id, String name, Float price) {
        super(TableName.AREA);
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Area(Long id, String name, Float price, List<TimeInterval> timeIntervalList) {
        super(TableName.AREA);
        this.id = id;
        this.name = name;
        this.price = price;
        this.timeIntervalList = timeIntervalList;
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

    public List<TimeInterval> getTimeIntervalList() {
        return timeIntervalList;
    }

    public void setTimeIntervalList(List<TimeInterval> timeIntervalList) {
        this.timeIntervalList = timeIntervalList;
    }

    public Float getPriceOfAdByTimeInterval(TimeInterval timeInterval){
        return price * timeInterval.getCoefMulti();
    }

    public Float getTotalPriceOfAllTimeIntervalSelected(){
        Float stockValue = 0F;
        for (TimeInterval timeInterval : timeIntervalList){
            stockValue += getPriceOfAdByTimeInterval(timeInterval);
        }
        return stockValue;
    }
}
