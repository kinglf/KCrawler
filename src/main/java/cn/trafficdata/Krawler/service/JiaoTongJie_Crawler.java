package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.utils.DocumentUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.List;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class JiaoTongJie_Crawler extends DocumentUtils implements ProcessDao {

    public List<WebURL> pageListHandler(Page page) {
        return null;
    }

    public boolean processDoc(Page page) {
        return false;
    }
}
