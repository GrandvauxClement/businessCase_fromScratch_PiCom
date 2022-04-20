package com.picom.dao;

import com.picom.db.DBConnect;
import com.picom.models.Ad;
import com.picom.models.Area;
import com.picom.models.TimeInterval;
import com.picom.models.User;
import com.picom.models.db.TableName;
import com.picom.utils.DateManagement;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class AdDAO extends AbstractGenericDAO<Ad>{

    public AdDAO() {
        super(TableName.AD);
    }

    public Ad createAd(String image, String text, Date startDate, int numDaysOfDiffusion, Long userId,
                       List<Area> areaList, List<TimeInterval> timeIntervalList) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Ad ad = null;

        try {
            String query = "INSERT INTO " + tableName + " (image, text, created_at, start_date, num_days_of_diffusion," +
                    "id_user) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            Date createdAt = new Date();
            ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, image);
            ps.setString(2, text);
            ps.setDate(3, DateManagement.convertUtilDateToSQLDate(createdAt));
            ps.setDate(4, DateManagement.convertUtilDateToSQLDate(startDate));
            ps.setInt(5, numDaysOfDiffusion);
            ps.setLong(6, userId);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            System.out.println(ps.getWarnings());

            if (rs.next()) {
                // Add Interval Time relation with ad
                StringBuilder queryForTimeInterval = new StringBuilder("INSERT INTO ad_time_interval (id_ad, id_time_interval) VALUES ");
                Long idAdCreate = rs.getLong(1);
                int counter = 0;
                for (TimeInterval time: timeIntervalList){
                   queryForTimeInterval.append("(").append(idAdCreate).append(", ").append(time.getId()).append(")");
                   counter ++;
                   if (counter < timeIntervalList.size()){
                       queryForTimeInterval.append(",");
                   }
                }

                queryForTimeInterval.append(";");

                PreparedStatement psInterval = this.connection.prepareStatement(String.valueOf(queryForTimeInterval), Statement.RETURN_GENERATED_KEYS);
                psInterval.executeUpdate();
                ResultSet rsInterval = psInterval.getGeneratedKeys();

               /* int counter = 0;
                while (rsInterval.next()){
                    timeIntervalList.get(counter).setId(rs.getLong("1"));
                    counter ++;
                }*/

                // Add Area relation with ad

                StringBuilder queryForArea = new StringBuilder("INSERT INTO ad_area (id_ad, id_area) VALUES ");
                counter = 0;
                for (Area area: areaList){
                    queryForArea.append("(").append(idAdCreate).append(", ").append(area.getId()).append(")");
                    counter ++;
                    if (counter < areaList.size()){
                        queryForArea.append(",");
                    }
                }

                queryForArea.append(";");

                PreparedStatement psArea = this.connection.prepareStatement(String.valueOf(queryForArea), Statement.RETURN_GENERATED_KEYS);
                psArea.executeUpdate();
                ResultSet rsArea = psArea.getGeneratedKeys();

               /*  counter = 0;
                while (rsArea.next()){
                    timeIntervalList.get(counter).setId(rs.getLong("1"));
                    counter ++;
                }*/
                UserDAO userDAO = new UserDAO();
                User user = userDAO.findById(userId);
                ad = new Ad(idAdCreate, image, text, createdAt, startDate, numDaysOfDiffusion, user, timeIntervalList, areaList);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }

        return ad;
    }
}
