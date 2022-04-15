package com.picom.services;

import com.picom.dao.CityDAO;
import com.picom.dao.UserDAO;
import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.User;

import java.sql.SQLException;
import java.util.List;

public class CityService {


    private CityDAO cityDAO;

    public CityService() {
        this.cityDAO = new CityDAO();
    }

    public List<City> findAll() {
        try {
            return this.cityDAO.findAll();
        } catch (SQLException e){
            return null;
        }
    }

    public City findById(Long id){
        try{
            return this.cityDAO.findById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public City findByName(String name){
        try{
            return this.cityDAO.findByName(name);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteById(Long id){
        try {
            return this.cityDAO.deleteById(id);
        }catch (SQLException e){
            return false;
        }
    }

    public City create(String name, Country country){
        try {
            return this.cityDAO.create(name, country);
        } catch (SQLException e){
            return null;
        }
    }
}
