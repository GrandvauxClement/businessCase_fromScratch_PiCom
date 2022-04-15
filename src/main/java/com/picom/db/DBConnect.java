package com.picom.db;

import com.picom.configuration.AppProperties;
import com.picom.exceptions.CantOpenConnectionException;

import java.sql.*;

public class DBConnect {

    public static Connection getConnection() {
        try {
            System.out.println(AppProperties.getInstance().getDbUrl());
            return DriverManager.getConnection(
                    AppProperties.getInstance().getDbUrl(),
                    AppProperties.getInstance().getDbUser(),
                    AppProperties.getInstance().getDbPassword()
            );
        } catch (SQLException e){
            e.printStackTrace();
            throw new CantOpenConnectionException();
        }
    }

    public static void closeAll(PreparedStatement ps, ResultSet rs){
        try {
            rs.close();
        } catch (Exception e){

        }

        try {
            ps.close();
        } catch (Exception e){

        }
    }

}
