package cn.trafficdata.Krawler.SerializableModel;

import edu.uci.ics.crawler4j.url.WebURL;

import java.io.Serializable;

/**
 * Created by Kinglf on 2016/8/13.
 */
public class Page_Serializable extends edu.uci.ics.crawler4j.crawler.Page implements Serializable {
    private static final long serialVersionUID = 1L;
    public Page_Serializable(WebURL url) {
        super(url);
    }
}
