package cn.trafficdata.Krawler.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class CrawlerConstants {

    public static final Map<String,String> SITE_MAP=new HashMap<String, String>();
    public static final String ENCODING = "UTF-8";
    public static final String USERAGNET = "Mozilla/5.0 (X11; Linux x86_64; rv:31.0) Gecko/20100101 Firefox/31.0";
    public static final String USERAGNET2 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.65 Safari/537.36";

    static{
        SITE_MAP.put("zgjtb.com","ZGJTB_Crawler");
        SITE_MAP.put("jiaotongjie.com","JiaoTongJie_Crawler");
        SITE_MAP.put("moc.gov.cn","MOC_Crawler");
    }
}
