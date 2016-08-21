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
    public static final String IMAGE_LOG_FILE="data/crawl/image/log.txt";
    public static final String RESULT_TABLE_NAME="newsResult";
    public static final String TASK_TABLE_NAME="tasktable";
    //diy
    public static final int FENLEI=10;
    public static final String FENLEISTR="管窥";

    static{
        SITE_MAP.put("zgjtb.com","ZGJTB_Crawler");
        SITE_MAP.put("jiaotongjie.com","JiaoTongJie_Crawler");
        SITE_MAP.put("moc.gov.cn","MOC_Crawler");
        SITE_MAP.put("163.com","_163_Crawler");
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
        SITE_MAP.put("21its.com","_21ITS_Crawler");
        SITE_MAP.put("cn-its.com.cn","CN_ITS_Crawler");
        SITE_MAP.put("its114.com","ITS114_Crawler");
        SITE_MAP.put("chinahighway.com","CHINAHIGHWAY_Crawler");
        SITE_MAP.put("china-highway.com","CHINA_HIGHWAY_Crawler");
        SITE_MAP.put("rioh.cn","RIOH_Crawler");
        SITE_MAP.put("zgsyb.com","ZGSYB_Crawler");
        SITE_MAP.put("wti.ac.cn","WTI_AC_Crawler");
        SITE_MAP.put("cwtca.org.cn","CWTCA_ORG_Crawler");
        SITE_MAP.put("chinaports.com","CHINAPORTS_COM_Crawler");
        SITE_MAP.put("chinaports.org","CHINAPORTS_ORG_Crawler");
        SITE_MAP.put("chineseport.cn","CHINESEPORT_Crawler");
        SITE_MAP.put("shippingchina.com","SHIPPINGCHINA_Crawler");
        SITE_MAP.put("csoa.cn","CSOA_Crawler");
        SITE_MAP.put("cj-pilot.com.cn","CJ_PILOT_Crawler");
        SITE_MAP.put("56beijing.org","_56BEIJING_Crawler");
        SITE_MAP.put("chinawuliu.com.cn","CHINAWULIU_Crawler");
        SITE_MAP.put("rail-transit.com","RAIL_TRANSIT_Crawler");
        SITE_MAP.put("ccmetro.com","CCMETRO_Crawler");
        SITE_MAP.put("bjsubway.com","BJSUBWAY_Crawler");
        SITE_MAP.put("ally.net.cn","ALLY_Crawler");
        SITE_MAP.put("chnrailway.com","CHNRAILWAY_Crawler");
        SITE_MAP.put("rails.com.cn","RAILS_Crawler");
        SITE_MAP.put("rail-info.com","RAIL_INFO_Crawler");
        SITE_MAP.put("caac.gov.cn","CAAC_Crawler");
        SITE_MAP.put("caacnews.com.cn","CAACNEWS_Crawler");
        SITE_MAP.put("carnoc.com","CARNOC_Crawler");
        SITE_MAP.put("cnair.com","CNAIR_Crawler");
        SITE_MAP.put("yunquna.com","YUNQUNA_Crawler");
        SITE_MAP.put("china-its.org","CHINA_ITS_Crawler");
        SITE_MAP.put("66its.com","_66ITS_Crawler");
        SITE_MAP.put("itsc.cn","ITSC_Crawler");
        SITE_MAP.put("mot.gov.cn","MOT_GOV_Crawler");
        SITE_MAP.put("mte.net.cn","MTE_Crawler");
        SITE_MAP.put("afzhan.com","AFZHAN_Crawler");
        SITE_MAP.put("cnits.net.cn","CNITS_NET_Crawler");
        SITE_MAP.put("cannews.com.cn","CANNEWS_Crawler");
        SITE_MAP.put("chinametro.net","CHINAMETRO_Crawler");
        SITE_MAP.put("zgcsgd.com","ZGCSGD_Crawler");
        SITE_MAP.put("firetc.com","FIRECT_Crawler");
        SITE_MAP.put("wokeji.com","WOKEJI_Crawler");
        SITE_MAP.put("toutiao.com","TOUTIAO_Crawler");
        SITE_MAP.put("cjtkj.com","CJTKJ_Crawler");
        SITE_MAP.put("controleng.cn","CONTROLENG_Crawler");

        FileUtils.createDirectory(IMAGE_FOLDER);
    }
    public static String getUseragnet(){
        return USERAGNET;
    }
}
