package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class YUNQUNA_Crawler extends  SuperCrawler  {
    protected Elements getLinkElements(Document doc) {
        return doc.select("dd.title h2");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div#divNewsContent").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("运去哪");
        return news;
    }
}
