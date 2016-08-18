package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class ITS114_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[class=list-wrap] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[id=Article] div[class=content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div[id=Article] h1 span").html(),news,"&nbsp","-","来源","作者");
    }
}
