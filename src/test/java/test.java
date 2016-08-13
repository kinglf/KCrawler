import cn.trafficdata.Krawler.utils.DBUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class test {
    public static void main(String[] args) {
//        DBUtil.getRedis().set("hehe","askdjsakdj");
//        List<String> asdkj=DBUtil.getRedis().lrange("list",0,-1);
//        for(String key:asdkj){
//            System.out.println(key);
//        }

        String html="<p align=\"center\"><img alt=\"\" src=\"./W020160811745020245712.jpg\" oldsrc=\"W020160811745020245712.jpg\">[trafficdataImg src=\"FE77264B7D3E98743F4ECDDB5516F2E2.jpg\"]trafficdataImg</p>    <p align=\"justify\">　　8月9日，中国民用航空局局长冯正霖在京会见了美国国家航空航天局局长查尔斯·博尔登一行。双方就中美民航关系发展、探讨双方在航空领域合作的可能性等内容交换了意见。中国航空研究院院长张新国会谈时在座。</p>";
        String regex="";
        System.out.println(html.replaceAll("<p.*?>","<p>"));
    }



}
