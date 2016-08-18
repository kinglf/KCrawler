package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class _56BEIJING_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=wzlb_con_left_table] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=wznr_con_nr]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource(doc.select("span[id=lblSouce]").text());
        news.setDatetime(doc.select("span[id=lblPublicTime]").text());
        return news;
    }
}
