package com.picom.dao;

import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.Role;
import com.picom.models.User;
import com.picom.models.db.TableName;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        String roleName = resultSet.getString("role.name");
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
        } else if (tableName.equals(TableName.ROLE)) {
            return (T) convertToRole(resultSet);
        }

        return null;
    }
}
