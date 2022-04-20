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
                       List<Area> areaList) throws SQLException {

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

            if (rs.next()) {
                Long idAdCreate = rs.getLong(1);
                // Add Area relation with ad

                for (Area area: areaList){

                    // Add area to ad that we just created
                    String queryForArea = "INSERT INTO ad_area (id_ad, id_area) VALUES (?,?)";
                    PreparedStatement psArea = this.connection.prepareStatement(queryForArea, Statement.RETURN_GENERATED_KEYS);

                    psArea.setLong(1,idAdCreate);
                    psArea.setLong(2, area.getId());

                    psArea.executeUpdate();
                    ResultSet rsArea = psArea.getGeneratedKeys();
                    if (rsArea.next()){
                        // Then we area add to ad, we add all time interval selected for this area
                        Long idAdAreaCreate = rsArea.getLong(1);
                        for (TimeInterval timeInterval : area.getTimeIntervalList()){

                            String queryForTimeInterval = "INSERT INTO ad_time_interval (id_ad_area, id_time_interval) VALUES (?,?)";
                            PreparedStatement psTimeInterval = this.connection.prepareStatement(queryForTimeInterval, Statement.RETURN_GENERATED_KEYS);

                            psTimeInterval.setLong(1,idAdAreaCreate);
                            psTimeInterval.setLong(2, timeInterval.getId());
                            psTimeInterval.executeUpdate();
                        }
                    }
                }

                UserDAO userDAO = new UserDAO();
                User user = userDAO.findById(userId);
                ad = new Ad(idAdCreate, image, text, createdAt, startDate, numDaysOfDiffusion, user, areaList);
            }
        } finally {
            DBConnect.closeAll(ps, rs);
        }

        return ad;
    }
}
