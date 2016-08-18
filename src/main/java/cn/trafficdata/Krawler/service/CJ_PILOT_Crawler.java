package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CJ_PILOT_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("table[class=new-list-box] tr td a");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("td[class=n_zw]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news=formatSource(doc.select("div[class=n_tips]").html(),news,"&nbsp;","-","来源","发布单位");
        news.setSource("长江引航中心");
        return news;
    }
}
