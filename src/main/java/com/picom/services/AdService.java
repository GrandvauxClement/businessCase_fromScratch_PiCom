package com.picom.services;

import com.picom.dao.AbstractGenericDAO;
import com.picom.dao.AdDAO;
import com.picom.models.Ad;
import com.picom.models.Area;
import com.picom.models.TimeInterval;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AdService {

    private AdDAO adDAO;

    public AdService() {
        this.adDAO = new AdDAO();
    }

    public Ad create(String image, String text, Date startDate, int numDaysOfDiffusion, Long userId,
                     List<Area> areaList) {
        try {
           return this.adDAO.createAd(image,text, startDate, numDaysOfDiffusion, userId, areaList);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Ad> findAll(){
        try {
            return this.adDAO.findAll();
        } catch (SQLException e){
            return null;
        }
    }

    public Ad findById(Long id){
        try {
            return this.adDAO.findById(id);
        } catch (SQLException e){
            return null;
        }
    }

    public List<Ad> findAllAdOfOneUser(Long idUser){
        try {
            return this.adDAO.findAllAdOfOneUser(idUser);
        }catch (SQLException e){
            return null;
        }
    }


}
