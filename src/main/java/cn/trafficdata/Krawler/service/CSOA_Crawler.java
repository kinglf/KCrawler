package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CSOA_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[id=news_list] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=detail-content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("中国船东网");
        news.setDatetime(RegexUtil.filterDate(doc.select("div[class=writerinfo]").text()));
        return news;
    }
}
