package com.picom.models;

import com.picom.models.db.TableName;

import java.util.Date;
import java.util.List;

public class Ad extends AbstractEntity{

    private Long id;

    private String image;

    private String text;

    private Date createdAt;

    private Date startDate;

    private Integer numDaysOfDiffusion;

    private User user;

    private List<TimeInterval> timeIntervalList;

    public Ad(Long id, String image, String text, Date createdAt, Date startDate, Integer numDaysOfDiffusion, User user,
              List<TimeInterval> timeIntervalList) {
        super(TableName.AD);
        this.id = id;
        this.image = image;
        this.text = text;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.numDaysOfDiffusion = numDaysOfDiffusion;
        this.user = user;
        this.timeIntervalList = timeIntervalList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getNumDaysOfDiffusion() {
        return numDaysOfDiffusion;
    }

    public void setNumDaysOfDiffusion(Integer numDaysOfDiffusion) {
        this.numDaysOfDiffusion = numDaysOfDiffusion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TimeInterval> getTimeIntervalList() {
        return timeIntervalList;
    }

    public void setTimeIntervalList(List<TimeInterval> timeIntervalList) {
        this.timeIntervalList = timeIntervalList;
    }
}
