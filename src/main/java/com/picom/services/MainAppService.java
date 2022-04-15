package com.picom.services;

import com.picom.models.City;

public class MainAppService {
    private CityService cityService;
    private UserService userService;

    public MainAppService() {
        this.cityService = new CityService();
        this.userService = new UserService();
    }

    public void register(){

    }
}
