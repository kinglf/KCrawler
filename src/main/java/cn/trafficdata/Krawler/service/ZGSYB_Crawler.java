package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class ZGSYB_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[class=list] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=article] div[class=content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourEl=doc.select("div[class=article] div[class=meta]").first();
        news.setDatetime(sourEl.select("span[id=pubtime_baidu]").text());
        news.setSource(sourEl.select("span[id=source_baidu]").text().replace("来源：",""));
        news.setAuthor(sourEl.select("span[id=author_baidu]").text().replace("作者：",""));
        return news;
    }
}
