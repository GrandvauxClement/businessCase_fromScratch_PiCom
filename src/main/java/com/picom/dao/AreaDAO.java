package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.Area;
import com.picom.models.TimeInterval;
import com.picom.models.db.TableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AreaDAO extends AbstractGenericDAO<Area>{

    public AreaDAO() {
        super(TableName.AREA);
    }

    public List<Area> findAreaByIdAd(Long idAd) throws SQLException {
        List<Area> list = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement(
                    "SELECT area.* FROM area " +
                            "INNER JOIN ad_area ON ad_area.id_area = area.id " +
                            "WHERE ad_area.id_ad = ?");
            ps.setLong(1, idAd);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((Area) ResultSetConverter.getModelFromResult(tableName, rs));
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }
        return list;
    }
}
