package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/17.
 */
public class testCrawler extends SuperCrawler {

    protected Elements getLinkElements(Document doc) {
        return null;
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return null;
    }

    protected Element getContentElement(Document doc) {
        return null;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return null;
    }

}
