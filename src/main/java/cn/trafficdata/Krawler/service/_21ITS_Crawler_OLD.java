package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class _21ITS_Crawler_OLD extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class^=new-content-left] ul[class=news-list] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div[class=new-detailed-cont]").first();
        Elements delEls=docEl.select("p");
        for(Element delEl:delEls){
            if (delEl.html().contains("更多内容")||delEl.attr("class")=="new-align-right"){
                delEl.remove();
            }
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourceEl=doc.select("p[class=left]").first();
        news=formatSource(sourceEl.html(),news,"\n","-","来源","作者");
        return news;
    }
}
