package com.picom.services;

import com.picom.dao.AreaDAO;
import com.picom.dao.TimeIntervalDAO;
import com.picom.exceptions.DbUniqueFieldThisValueExist;
import com.picom.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdServiceTest {

    private AdService adService;
    private UserService userService;
    private TimeIntervalDAO timeIntervalDAO;
    private AreaDAO areaDAO;

    @BeforeEach
    public void prepareTest() throws DbUniqueFieldThisValueExist{
        System.out.println("Im here !!!!");
        this.userService = new UserService();
        this.adService = new AdService();
        this.timeIntervalDAO = new TimeIntervalDAO();
        this.areaDAO = new AreaDAO();
        this.userService.register("Grandvaux", "Clément", "clement.grandvaux@hotmail.com",
                "Admin123", "6 29 16 89 43","25487563256632", "Tesla",
                "14 boulevard Gambetta", "39000", "Lons-le-saunier", "France", "+33");


    }

    @Test
    @DisplayName("Test to add new ad")
    public void testCreateAd() throws SQLException {
        List<TimeInterval> allTimeIntervalList = this.timeIntervalDAO.findAll();
        List<TimeInterval> timeIntervalList = new ArrayList<>();
        timeIntervalList.add(allTimeIntervalList.get(2));
        timeIntervalList.add(allTimeIntervalList.get(5));
        timeIntervalList.add(allTimeIntervalList.get(8));
        Area area = areaDAO.findById(1L);
        area.setTimeIntervalList(timeIntervalList);
        List<Area> areaList = new ArrayList<>();
        areaList.add(area);

        User user = userService.findById(2L);

        Ad ad = this.adService.create("lala.png", "Lorem ipsum super texte qui va servir de description", new Date(),
                15, 1L,  areaList);


        Ad adWithId = adService.findById(2L);

        List<Ad> adList = adService.findAll();
        Assertions.assertEquals("lala.png", ad.getImage());
        Assertions.assertEquals("lala.png", adList.get(adList.size()-1).getImage());
    }

    @Test
    @DisplayName("Test find all ad")
    public void findAll() {
        List<Ad> adList = adService.findAll();
        Assertions.assertEquals("image.png", adList.get(0).getImage());
    }

    @Test
    @DisplayName("Test calculate total price of an ad for one day")
    public void getTotalPriceOfOneAdForOneDay() {
        Ad ad = adService.findById(1L);
        Assertions.assertEquals("370,44", String.format("%.2f",ad.getTotalPriceOfAdForOneDay()));
    }

    @Test
    @DisplayName("Test calculate total price of ad for one area and one day")
    public void getTotalPriceOfAdForOneArea(){
        Ad ad = adService.findById(1L);
        Assertions.assertEquals("192,78", String.format("%.2f", ad.getAreaList().get(0).getTotalPriceOfAllTimeIntervalSelected()));

    }

    @Test
    @DisplayName("Test calculate total price of ad for all area selected and num days of diffusion")
    public void getTotalPriceOfOneAd(){
        Ad ad = adService.findById(1L);
        Assertions.assertEquals("7408,80",String.format("%.2f", ad.getTotalPriceForAllDay()));
    }

}
