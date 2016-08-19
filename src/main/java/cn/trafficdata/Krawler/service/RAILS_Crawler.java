package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class RAILS_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.ns_ps>table[align=center]>tbody>tr a");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div[class^=content_style_newsshow]").first();
        Element delEL=docEl.select("p.news_title01").first();
        if(delEL!=null){
            delEL.remove();
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div.channal_style p").first().text(),news," ","-","新闻来源","作者");
    }
}
