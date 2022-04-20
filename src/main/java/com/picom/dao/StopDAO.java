package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.Area;
import com.picom.models.Stop;
import com.picom.models.db.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StopDAO extends AbstractGenericDAO<Stop>{

    public StopDAO() {
        super(TableName.STOP);
    }

    public List<Stop> findStopIdArea(Long idArea) throws SQLException {
        List<Stop> list = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement(
                    "SELECT * FROM stop " +
                        "WHERE stop.id_area = ?");
            ps.setLong(1, idArea);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((Stop) ResultSetConverter.getModelFromResult(tableName, rs));
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return list;
    }
}
