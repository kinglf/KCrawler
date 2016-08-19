package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class FIRECT_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("li.catlist_li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.content").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String date= RegexUtil.filterDate(doc.select("div.info").text());
        if(date!=null){
            news.setDatetime(date);
        }
        news.setSource("防火资源网");
        return news;
    }
}
