package cn.trafficdata.Krawler.service;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.List;

/**
 * Created by Kinglf on 2016/8/12.
 */
public interface ProcessDao {
    List<WebURL> pageListHandler(Page page);
    boolean processDoc(Page page);

}
