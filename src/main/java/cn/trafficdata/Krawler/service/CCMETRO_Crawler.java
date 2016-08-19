package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CCMETRO_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("table[id=dlResult]>tbody>tr>td");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("td[id=content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return news;
    }
}
