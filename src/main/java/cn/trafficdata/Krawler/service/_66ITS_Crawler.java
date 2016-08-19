package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class _66ITS_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul.list4 li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div.cBox").first();
        Elements els=docEl.select("span");
        for(Element delEL:els){
            if(delEL.html().contains("66its.com")){
                delEL.remove();
            }
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div.cInfo").text(),news," ","-","来源","编辑");
    }
}
