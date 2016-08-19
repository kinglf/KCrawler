package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class MOT_GOV_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("td[align=left]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div#cont_detail").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("交通运输部");
        return formatSource(doc.select("table#cont_info").text(),news," ","发文日期","发布机构","发布机构");
    }
}
