package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.TimeInterval;
import com.picom.models.User;
import com.picom.models.db.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TimeIntervalDAO extends AbstractGenericDAO<TimeInterval>{

    public TimeIntervalDAO() {
        super(TableName.TIME_INTERVAL);
    }

    public List<TimeInterval> findTimeIntervalByIdAd(Long idAd) throws SQLException {
        List<TimeInterval> list = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement(
                    "SELECT time_interval.* FROM time_interval " +
                        "INNER JOIN ad_time_interval ON ad_time_interval.id_time_interval = time_interval.id " +
                        "WHERE ad_time_interval.id_ad = ?");
            ps.setLong(1, idAd);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((TimeInterval) ResultSetConverter.getModelFromResult(tableName, rs));
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return list;
    }
}
