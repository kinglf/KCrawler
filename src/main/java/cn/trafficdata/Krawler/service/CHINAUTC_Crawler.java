package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINAUTC_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class^=content1] div[class^=con_left2] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=con_l2b3_p]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourceEl=doc.select("div[class^=con_l2b3_xx]").first();
        news=formatSource(sourceEl.html(),news,"\n","时间","来源","作者");
        return news;
    }
}
