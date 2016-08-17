package cn.trafficdata.Krawler.constants;

import cn.trafficdata.Krawler.utils.FileUtils;

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
    public static final String STORAGE_FOLDER="data/crawl/root";
    public static final String IMAGE_FOLDER="data/crawl/image";
    public static final String RESULT_TABLE_NAME="newsResult";
    public static final String TASK_TABLE_NAME="tasktable";

    static{
        SITE_MAP.put("zgjtb.com","ZGJTB_Crawler");
        SITE_MAP.put("jiaotongjie.com","JiaoTongJie_Crawler");
        SITE_MAP.put("moc.gov.cn","MOC_Crawler");
        SITE_MAP.put("163.com","WANGYI163_Crawler");
        SITE_MAP.put("jiemian.com","JMA_Crawler");
        SITE_MAP.put("qq.com","QQ_Crawler");
        SITE_MAP.put("sohu.com","SOHU_Crawler");
        SITE_MAP.put("c-cc.cn","C_CC_Crawler");
        SITE_MAP.put("bbaqw.com","BBAQW_Crawler");
        SITE_MAP.put("chinarta.com","CHINARTA_Crawler");
        SITE_MAP.put("china.com","CHINA_Crawler");
        SITE_MAP.put("cccnews.cn","CCCNEWS_Crawler");
        SITE_MAP.put("chinautc.com","CHINAUTC_Crawler");
        SITE_MAP.put("jt12345.com","JT12345_Crawler");
        SITE_MAP.put("21its.com","21ITS_Crawler");
        SITE_MAP.put("cn-its.com.cn","CN-ITS_Crawler");
        SITE_MAP.put("its114.com","ITS114_Crawler");
        SITE_MAP.put("chinahighway.com","CHINAHIGHWAY_Crawler");
        SITE_MAP.put("china-highway.com","CHINA-HIGHWAY_Crawler");

        FileUtils.createDirectory(IMAGE_FOLDER);
    }
    public static String getUseragnet(){
        return USERAGNET;
    }
}
