package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CN_ITS_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=list] li h3");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=news-t4]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news=formatSource(doc.select("div[class=news-t3]").html(),news,"\n","-","来源","责任编辑");
        return news;
    }
}
