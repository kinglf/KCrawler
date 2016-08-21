package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/21.
 */
public class _163_Crawler extends SuperCrawler{
    protected Elements getLinkElements(Document doc) {
        if(doc.baseUri().contains("auto.163.com")){
            return doc.select("div.item-cont h3");
        }
        return doc.select("div[class^=area_list] div[class=col_l] h2");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=post_text]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div[class=post_text]").first().text(),news," ","时间","本文来源","作者");
    }
}
