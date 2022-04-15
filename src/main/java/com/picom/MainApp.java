package com.picom;

import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.User;
import com.picom.services.UserService;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        UserService userService = new UserService();

     /*   Country country = new Country(1L, "France", "+33");
        City city = new City(1L, "Lyon", country);
        userService.create("Grandvaux", "Clément", "clement.grandvaux@hotmail.com",
                "Admin123", "6 29 16 89 43","25487563256632", "Tesla",
                "14 boulevard Gambetta", "39000", city);*/

        List<User> userList = userService.findAll();
        System.out.println(userList.size() + " users  in db");
    }
}
