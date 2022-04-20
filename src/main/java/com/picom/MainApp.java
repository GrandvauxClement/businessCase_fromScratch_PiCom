package com.picom;

import com.picom.dao.AreaDAO;
import com.picom.dao.TimeIntervalDAO;
import com.picom.exceptions.DbUniqueFieldThisValueExist;
import com.picom.models.*;
import com.picom.services.AdService;
import com.picom.services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();

        AdService adService = new AdService();
       /* TimeIntervalDAO timeIntervalDAO = new TimeIntervalDAO();
        AreaDAO areaDAO = new AreaDAO();

        List<TimeInterval> allTimeIntervalList = timeIntervalDAO.findAll();
        List<TimeInterval> timeIntervalList = new ArrayList<>();
        timeIntervalList.add(allTimeIntervalList.get(2));
        timeIntervalList.add(allTimeIntervalList.get(5));
        timeIntervalList.add(allTimeIntervalList.get(8));
        Area area = areaDAO.findById(1L);
        area.setTimeIntervalList(timeIntervalList);
        List<Area> areaList = new ArrayList<>();
        areaList.add(area);


        Ad ad = adService.create("lala.png", "Lorem ipsum super texte qui va servir de description", new Date(),
                15, 1L,  areaList);

        System.out.println(ad);
        Ad adWithId = adService.findById(2L);*/

        List<Ad> adList = adService.findAll();

        System.out.println(adList.size() + " ad  in db");
    }
}
