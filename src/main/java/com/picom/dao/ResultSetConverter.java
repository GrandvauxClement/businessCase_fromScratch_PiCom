package com.picom.dao;

import com.picom.models.*;
import com.picom.models.db.TableName;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    public static User convertToUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String lastname = resultSet.getString("last_name");
        String firstname = resultSet.getString("first_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phone_number");
        boolean isVerified = resultSet.getBoolean("is_verified");
        String numSiret = resultSet.getString("num_siret");
        String companyName = resultSet.getString("company_name");
        String roadName = resultSet.getString("road_name");
        String postalCode = resultSet.getString("postal_code");
        Long idCity = resultSet.getLong("id_city");
        String cityName = resultSet.getString("city.name");
        Long idCountry = resultSet.getLong("city.id_country");
        String countryName = resultSet.getString("country.name");
        String phoneIndicative = resultSet.getString("country.phone_indicative");
        Long idRole = resultSet.getLong("id_role");
        String roleName = resultSet.getString("roles.name");
        Country country = new Country(idCountry, countryName, phoneIndicative);
        City citySelected = new City(idCity, cityName, country);
        Role role = new Role(idRole, roleName);

        return new User(id, lastname, firstname, email, password, phoneNumber,
                isVerified, numSiret,companyName, roadName, postalCode, citySelected, role);
    }

    public static Country convertToCountry(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String phoneIndicative = resultSet.getString("phone_indicative");

        return new Country(id, name, phoneIndicative);
    }

    public static City convertToCity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Long idCountry = resultSet.getLong("id_country");

        CountryDAO countryDAO = new CountryDAO();
        Country country = countryDAO.findById(idCountry);

        return new City(id, name, country);
    }

    public static TimeInterval convertToTimeInterval(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Double timeSlot = resultSet.getDouble("time_slot");
        Float coefMulti = resultSet.getFloat("coef_multi");

        return new TimeInterval(id, timeSlot, coefMulti);
    }

    public static Area convertToAreaInsideAd(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Float price = resultSet.getFloat("price");

        TimeIntervalDAO timeIntervalDAO = new TimeIntervalDAO();
        List<TimeInterval> timeIntervalList = null;

        timeIntervalList = timeIntervalDAO.findTimeIntervalByIdAdArea(resultSet.getLong("ad_area.id"));

        return new Area(id, name, price, timeIntervalList);
    }

    public static Area convertToArea(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Float price = resultSet.getFloat("price");

        List<TimeInterval> timeIntervalList = new ArrayList<>();

        return new Area(id, name, price, timeIntervalList);
    }

    public static Stop convertToStop(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Float latitude = resultSet.getFloat("latitude");
        Float longitude = resultSet.getFloat("longitude");
        String adressIp = resultSet.getString("adress_ip");
        return new Stop(id, name, latitude, longitude, adressIp);
    }

    public static Ad convertToAd(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong("id");
        String image = resultSet.getString("image");
        String text = resultSet.getString("text");
        Date createdAt = resultSet.getDate("created_at");
        Date startDate = resultSet.getDate("start_date");
        Integer numDaysOfDiffusion = resultSet.getInt("num_days_of_diffusion");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(resultSet.getLong("id_user"));

        AreaDAO areaDAO = new AreaDAO();
        List<Area> areaList = areaDAO.findAreaAndTimeIntervalByIdAd(id);

        return new Ad(id, image, text, createdAt, startDate, numDaysOfDiffusion, user, areaList);
    }

    public static Role convertToRole(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        return new Role(id, name);
    }

    static <T> T getModelFromResult(TableName tableName, ResultSet resultSet) throws SQLException{

        if (tableName.equals(TableName.USER)){
            return (T) convertToUser(resultSet);

        } else if (tableName.equals(TableName.COUNTRY)){
            return (T) convertToCountry(resultSet);

        } else if (tableName.equals(TableName.CITY)){
            return (T) convertToCity(resultSet);

        } else if (tableName.equals(TableName.ROLES)) {
            return (T) convertToRole(resultSet);

        } else if (tableName.equals(TableName.STOP)){
            return (T) convertToStop(resultSet);

        } else if ((tableName.equals(TableName.TIME_INTERVAL))){
            return (T) convertToTimeInterval(resultSet);

        }else if (tableName.equals(TableName.AREA)){
            return (T) convertToArea(resultSet);

        }else if (tableName.equals(TableName.AD)){
            return (T) convertToAd(resultSet);
        }

        return null;
    }

    static <T> T getSpecialModelFromResult(String string, ResultSet resultSet) throws SQLException{

        if (string.equals("AreaInsideAd")){
            return (T) convertToAreaInsideAd(resultSet);
        }
        return null;
    }
}
