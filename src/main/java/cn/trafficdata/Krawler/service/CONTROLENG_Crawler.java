package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CONTROLENG_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.R_lnews h1");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div.Adetail_content").first();
        Elements dels=docEl.select("div");
        for(Element del:dels){
            del.remove();
        }
        Elements dels2=docEl.select("a");
        for(Element del:dels2){
            del.remove();
        }
        Elements dels3=docEl.select("b");
        for(Element del:dels3){
            del.remove();
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("h3 p").text(),news," ","-","来源","作者");
    }
}
