package com.picom.services;

import com.picom.exceptions.DbUniqueFieldThisValueExist;
import com.picom.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void prepareTest() throws DbUniqueFieldThisValueExist {
        this.userService = new UserService();
        this.userService.register("Grandvaux", "Clément", "clement.grandvaux@hotmail.com",
                "Admin123", "6 29 16 89 43","25487563256632", "Tesla",
                "14 boulevard Gambetta", "39000", "Lons-le-saunier", "France", "+33");
    }

    @Test
    public void testRegisterWork() throws DbUniqueFieldThisValueExist {
        List<User> users = userService.findAll();
        int nbUsers = users.size();

        User userCreate = this.userService.register("Dupond", "Antoine", "6nation@grandchelem.com",
                "BestPlayer9", "384253729","25487659846632", "Stade Toulousain",
                "9 rue de la gagne", "27000", "Toulouse", "France", "+33");

        users = userService.findAll();
        assertThat(users, is(notNullValue()));
        assertThat(nbUsers + 1, is(equalTo(users.size())));
        Assertions.assertEquals(users.get(users.size()-1).getEmail(),userCreate.getEmail());
    }

    @Test
   void testRegisterWithEmailAlreadyExist() throws DbUniqueFieldThisValueExist {

        Assertions.assertThrows(DbUniqueFieldThisValueExist.class, () -> {
            this.userService.register("Grandvaux", "Clément", "clement.grandvaux@hotmail.com",
                    "Admin123", "6 29 16 89 43","25487563255532", "Tesla",
                    "14 boulevard Gambetta", "39000", "Lons-le-saunier", "France", "+33");
        });
    }

    @Test
    void testRegisterWithNumSiretAlreadyExist() throws DbUniqueFieldThisValueExist {

        Assertions.assertThrows(DbUniqueFieldThisValueExist.class, () -> {
            this.userService.register("Grandvaux", "Clément", "clem.grandvaux@hotmail.com",
                    "Admin123", "6 29 16 89 43","25487563256632", "Tesla",
                    "14 boulevard Gambetta", "39000", "Lons-le-saunier", "France", "+33");
        });
    }

    @Test
    public void testFindAll() {
        List<User> userList = userService.findAll();
        Assertions.assertEquals(1, userList.size());
        Assertions.assertEquals("Grandvaux", userList.get(0).getLastName());
        Assertions.assertEquals("Lons-le-saunier", userList.get(0).getCity().getName());
    }

    @Test
    public void testDeleteUser(){
        List<User> userList = userService.findAll();

        boolean response = userService.deleteById(userList.get(0).getId());
        Assertions.assertTrue(response);
    }

    @Test
    public void testDeleteUserWithInexistId(){

        boolean response = userService.deleteById(30L);
        Assertions.assertFalse(response);
    }

}
