package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINAPORTS_COM_Crawler extends  SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=news-more-liebiao] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div[class=news-wzh-text-nav]").first();
        Elements delEls=docEl.select("div");
        for(Element delEl:delEls){
            if(delEl.html().contains("分享")||delEl.html().contains("BAIDU")){
                delEl.remove();
            }
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("div[class=news-wenzhang-laiyuan]").first().text(),news," ","-","来源","作者");
    }
}
