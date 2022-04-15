package com.picom.services;

import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CityServiceTest {

    private CityService cityService;

    @BeforeEach
    public void prepareTest(){
        this.cityService = new CityService();
        Country country = new Country(1L, "France", "+33");
        this.cityService.create("Genève", country);
    }

    @Test
    public void testCreate() {
        List<City> cityList = cityService.findAll();
        int nbUsers = cityList.size();
        Country country = new Country(1L, "France", "+33");

        this.cityService.create("Lons-le-saunier", country);

        cityList = cityService.findAll();
        assertThat(cityList, is(notNullValue()));
        assertThat(nbUsers + 1, is(equalTo(cityList.size())));
    }

    @Test
    public void testFindAll() {
        List<City> cityList = cityService.findAll();
        Assertions.assertEquals(1, cityList.size());
        Assertions.assertEquals("Lyon", cityList.get(0).getName());
    }

}
