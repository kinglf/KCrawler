package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class TaskServices {
    public static List<String> loadTasks() throws SQLException {
        List<String> tasks=new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException e) {
            return null;
        }
        PreparedStatement ps = connection.prepareStatement("SELECT url FROM tasktable WHERE flag=3");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String url = resultSet.getString(1);
            tasks.add(url);
        }
    return tasks;
    }
}