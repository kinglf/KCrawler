package cn.trafficdata.Krawler.utils;

import edu.uci.ics.crawler4j.crawler.Page;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class DBUtil {

    private static String ip="127.0.0.1";
    private static int port=6379;
    private static Jedis jedis=null;
    public static Jedis getRedis(){
        if(jedis==null||!jedis.isConnected()){
            jedis=new Jedis(ip,port);
        }
        return jedis;
    }

    public static void savePage(Page page_serializable) throws Exception{
        Jedis redis=getRedis();
        String url=page_serializable.getWebURL().getURL();
        redis.set(MD5Util.MD5(url).getBytes(), SerializeUtil.serialize(page_serializable));//将page转为字节数组流时会报空指针异常

    }

    /**
     * mysql链接方式,主要用作task加载用
     */
    private static String sqlUrl="jdbc:mysql://127.0.0.1:3306/crawler_zk?characterEncoding=UTF-8";
    private static String username="root";
    private static String password="1234";
    static {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Connection getConnection()
            throws SQLException
    {
        return DriverManager.getConnection(sqlUrl, username, password);
    }


}
