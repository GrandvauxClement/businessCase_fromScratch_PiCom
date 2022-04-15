package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.Country;
import com.picom.models.db.TableName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGenericDAO<T> implements GenricDAO<T> {

    protected TableName tableName;
    protected Connection connection;

    public AbstractGenericDAO(TableName tableName){
        this.tableName = tableName;
        this.connection = DBConnect.getConnection();
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> list = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = this.connection.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((T) ResultSetConverter.getModelFromResult(tableName, rs));
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return list;
    }

    @Override
    public T findById(Long id) throws SQLException {
        T currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = this.connection.prepareStatement("SELECT * FROM " + tableName +" WHERE id= ?");
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

    public boolean deleteById(Long id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean response = false;
        try {

            PreparedStatement psTest = this.connection.prepareStatement("SELECT * FROM " + tableName +" WHERE id= ?");
            psTest.setLong(1, id);
            rs = psTest.executeQuery();
            if (rs.next()){
                String query = "DELETE FROM " + tableName + " WHERE id=?";

                ps = connection.prepareStatement(query);
                ps.setLong(1, id);
                ps.executeUpdate();
                response = true;
            }

        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return response;
    }

    public T findByName(String name) throws SQLException {
        T currentPojo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = this.connection.prepareStatement("SELECT * FROM " + tableName +" WHERE name= ?");
            ps.setString(1, name);

            rs = ps.executeQuery();
            if (rs.next()){
                currentPojo = ResultSetConverter.getModelFromResult(tableName, rs);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return currentPojo;
    }

    public boolean checkIfFieldExist(String fieldName, String fieldValue) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean response = false;
        try{
            ps = this.connection.prepareStatement("SELECT * FROM " + tableName +" WHERE " + fieldName + " = ?");
          //  ps.setString(1, fieldName);
            ps.setString(1, fieldValue);

            rs = ps.executeQuery();
            if (rs.next()){
               response = true;
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return response;
    }
}
