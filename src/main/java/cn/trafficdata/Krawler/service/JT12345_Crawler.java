package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class JT12345_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=bm2] h2");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("td[id=article_content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourceEl=doc.select("p[class=xg1]").first();
        news=formatSource(sourceEl.text(),news,"\\|","-","来自","作者");
        return news;
    }
}
