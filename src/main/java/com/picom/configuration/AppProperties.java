package com.picom.configuration;

import com.picom.exceptions.CantLoadPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static AppProperties INSTANCE;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public AppProperties(String dbUrl, String dbUser, String dbPassword){
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public static AppProperties getInstance() {
        if (INSTANCE == null){
            try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
                Properties prop = new Properties();

                if (input == null){
                    throw new CantLoadPropertiesException();
                }

                prop.load(input);

                String dbUrl = prop.getProperty("db.url");
                String dbUser = prop.getProperty("db.user");
                String dbPassword = prop.getProperty("db.password");

                INSTANCE = new AppProperties(dbUrl, dbUser, dbPassword);

            } catch (IOException ex){
                throw new CantLoadPropertiesException();
            }
        }

        return INSTANCE;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
