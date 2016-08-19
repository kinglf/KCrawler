package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class AFZHAN_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.news-left-list");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div#newsContent").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String source=doc.select("a.companyName").text();
        if(source!=null){
            news.setSource(source);
        }else{
            news.setSource("中国安防展览网");
        }
        return news;
    }
}
