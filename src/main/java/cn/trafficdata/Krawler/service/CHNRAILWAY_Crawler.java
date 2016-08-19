package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CHNRAILWAY_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.newslist div.tit");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.content").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div.info").html(),news,"\n","-","来源","作者");
    }
}
