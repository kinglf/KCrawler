package cn.trafficdata.Krawler.utils;

import cn.trafficdata.Krawler.SerializableModel.Page_Serializable;
import redis.clients.jedis.Jedis;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class DBUtil {

    private static String tableNameForPage="pageList";
    private static String ip="127.0.0.1";
    private static int port=6699;
    private static Jedis jedis=null;
    public static Jedis getRedis(){
        if(jedis==null||!jedis.isConnected()){
            jedis=new Jedis(ip,port);
        }
        return jedis;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        DBUtil.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        DBUtil.port = port;
    }
    public static void savePage(Page_Serializable page_serializable) throws Exception{
        Jedis jedis=getRedis();
        String url=page_serializable.getWebURL().getURL();
        jedis.set(MD5Util.MD5(url).getBytes(), SerializeUtil.serialize(page_serializable));
    }
}
