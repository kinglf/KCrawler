package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CHINA_ITS_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("table.tab_list>tbody>tr>td[align=left]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div.sub_right_area div.txt").first();
        Element delEl=docEl.select("h1").first();
        if(delEl!=null){
            delEl.remove();
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("china-its.org");
        return news;
    }
}
