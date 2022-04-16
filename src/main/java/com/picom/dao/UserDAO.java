package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.City;
import com.picom.models.Role;
import com.picom.models.User;
import com.picom.models.db.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractGenericDAO<User> {

    public UserDAO(){
        super(TableName.USER);
    }

    public User create(String lastName, String firstName, String email, String password, String phoneNumber,
                       String numSiret,String companyName, String roadName, String postalCode, City city, Role role) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            String query = "INSERT INTO " + tableName + " (last_name, first_name, email, password, phone_number," +
                    "is_verified, num_siret, company_name, road_name, postal_code, id_city, id_role) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, phoneNumber);
            ps.setBoolean(6, false);
            ps.setString(7, numSiret);
            ps.setString(8, companyName);
            ps.setString(9, roadName);
            ps.setString(10, postalCode);
            ps.setLong(11, city.getId());
            ps.setLong(12, role.getId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                user = new User(rs.getLong(1), lastName, firstName, email, password, phoneNumber,
                        false, numSiret,companyName, roadName, postalCode, city, role);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }

        return user;
    }

    @Override
    public User findById(Long id) throws SQLException {
        User currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = this.connection.prepareStatement(
                    "SELECT * FROM user WHERE id= ? " +
                    "INNER JOIN city ON city.id = user.id_city " +
                    "INNER JOIN country ON country.id = city.id_country " +
                    "INNER JOIN role ON role.id = user.id_role");
            ps.setLong(1, id);

            rs = ps.executeQuery();
            if (rs.next()){
                currentPojo = ResultSetConverter.getModelFromResult(tableName, rs);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return currentPojo;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement(
                    "SELECT * FROM user " +
                    "INNER JOIN city ON city.id = user.id_city " +
                    "INNER JOIN country ON country.id = city.id_country " +
                    "INNER JOIN role ON role.id = user.id_role");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((User) ResultSetConverter.getModelFromResult(tableName, rs));
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return list;
    }


}
