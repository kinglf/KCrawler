import cn.trafficdata.Krawler.utils.DBUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class test {
    public static void main(String[] args) {
        DBUtil.getRedis().set("hehe","askdjsakdj");
        List<String> asdkj=DBUtil.getRedis().lrange("list",0,-1);
        for(String key:asdkj){
            System.out.println(key);
        }
    }


}
