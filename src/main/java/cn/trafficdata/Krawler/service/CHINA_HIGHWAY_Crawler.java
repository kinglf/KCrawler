package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINA_HIGHWAY_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=news-list] div[class=left]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=newscontent] table td").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("class=newscontent div[class=remarks]").text(),news,"\\|","/","来源","作者");
    }
}
