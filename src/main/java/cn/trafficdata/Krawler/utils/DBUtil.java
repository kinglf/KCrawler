package cn.trafficdata.Krawler.utils;

import edu.uci.ics.crawler4j.crawler.Page;
import redis.clients.jedis.Jedis;

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
    public static void savePage(Page page_serializable) throws Exception{
        Jedis redis=getRedis();
        String url=page_serializable.getWebURL().getURL();
        redis.set(MD5Util.MD5(url).getBytes(), SerializeUtil.serialize(page_serializable));//将page转为字节数组流时会报空指针异常

    }

}
