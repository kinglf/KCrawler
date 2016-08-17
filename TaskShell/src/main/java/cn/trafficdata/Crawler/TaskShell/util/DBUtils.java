package cn.trafficdata.Crawler.TaskShell.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class DBUtils {
    public static String url="jdbc:mysql://127.0.0.1:3306/crawler_zk?characterEncoding=UTF-8";
    public static String username="root";
    public static String password="1234";
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection(String rurl,String rusername,String rpassword)
            throws SQLException
    {
        return DriverManager.getConnection(rurl, rusername, rpassword);
    }
    public static Connection getConnection()
            throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }
    public static boolean validDB(String url,String username,String password){
        try {
            Connection connection=DriverManager.getConnection(url,username,password);
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
