package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CNITS_NET_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.list ul li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div#post1").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String time= RegexUtil.filterDate(doc.select("div.show_l_date").text());
        if(time!=null){
            news.setDatetime(time);
        }
        return news;
    }
}
