package com.picom.services;

import com.picom.dao.CityDAO;
import com.picom.dao.CountryDAO;
import com.picom.dao.RoleDAO;
import com.picom.dao.UserDAO;
import com.picom.exceptions.DbUniqueFieldThisValueExist;
import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.Role;
import com.picom.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    private CityDAO cityDAO;
    private CountryDAO countryDAO;
    private RoleDAO roleDAO;


    public UserService() {
        this.userDAO = new UserDAO();
        this.cityDAO = new CityDAO();
        this.countryDAO = new CountryDAO();
        this.roleDAO = new RoleDAO();
    }

    public List<User> findAll() {
        try {
            return this.userDAO.findAll();
        } catch (SQLException e){
            return null;
        }
    }

    public User register(String lastName, String firstName, String email, String password, String phoneNumber, String numSiret,
                       String companyName, String roadName, String postalCode, String cityName, String countryName,
                         String phoneIndicative) throws DbUniqueFieldThisValueExist {
        try {
            //Check if email already use
            if (userDAO.checkIfFieldExist("email", email)){
                throw new DbUniqueFieldThisValueExist("email");
            }
            //Check if num siret already use
            if (userDAO.checkIfFieldExist("num_siret", numSiret)){
                throw new DbUniqueFieldThisValueExist("num siret");
            }
            //Check if country exist in db and create if he doesn't
            Country checkCountryExist = countryDAO.findByName(countryName);
            if (checkCountryExist == null){
                checkCountryExist = countryDAO.create(countryName, phoneIndicative);
            }
            //Check if city exist in db and create if he doesn't
            City checkCityExist = cityDAO.findByName(cityName);
            if (checkCityExist == null){
                checkCityExist = cityDAO.create(cityName, checkCountryExist);
            }
            //By default all new User have role user
            Role role = roleDAO.findByName("User");

            return this.userDAO.create(lastName, firstName, email, password, phoneNumber, numSiret, companyName,
                    roadName, postalCode, checkCityExist, role);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public User findById(Long id){
        try{
            return this.userDAO.findById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteById(Long id){
        try {
            return this.userDAO.deleteById(id);
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
