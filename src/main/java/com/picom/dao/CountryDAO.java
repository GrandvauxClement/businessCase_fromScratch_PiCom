package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.City;
import com.picom.models.Country;
import com.picom.models.db.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountryDAO extends AbstractGenericDAO<Country>{

    public CountryDAO() {
        super(TableName.COUNTRY);
    }

    public Country create(String name, String indicativePhone) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Country country = null;

        try {
            String query = "INSERT INTO " + tableName + " (name, phone_indicative) VALUES(?, ?)";

            ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, indicativePhone);


            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                country = new Country(rs.getLong(1), name,indicativePhone);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }

        return country;
    }


}
