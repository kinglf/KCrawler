package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class EPAGER_ZGSYB_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[class=main-ed-articlenav-list] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("table[cellpadding=0] td").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("中国水运报");
        news.setDatetime(RegexUtil.filterStr(doc.baseUri(),"http://epaper.zgsyb.com/html/(.*?)/c.*?",true).replace("/","-"));
        return news;
    }
}
