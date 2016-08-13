import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.model.News;
import cn.trafficdata.Krawler.utils.DBUtil;
import cn.trafficdata.Krawler.utils.SerializeUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class test {
    public static void main(String[] args) {
//        Set s=DBUtil.getRedis().keys("*");
//        Iterator it=s.iterator();
//        while (it.hasNext()){
//            String value= (String) it.next();
//            byte[] key=DBUtil.getRedis().get(value.getBytes());
//            Object obj=  SerializeUtil.unserialize(key);
//            if(obj instanceof Page){
//                Page page= (Page) obj;
//                System.out.println(page.getWebURL().getURL());
//
//            }
//        }

        List<byte[]> lrange = DBUtil.getRedis().lrange(CrawlerConstants.RESULT_TABLE_NAME.getBytes(), 0, -1);
        for(byte[] bytes:lrange){
            Object unserialize = SerializeUtil.unserialize(bytes);
            if(unserialize instanceof News){
                News news= (News) unserialize;
                System.out.println(news.getTitle()+"---"+news.getContent());
            }
        }

    }



}
