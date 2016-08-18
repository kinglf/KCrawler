package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINAPORTS_ORG_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=List_class] ul li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=info_content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setDatetime(RegexUtil.filterDate(doc.select("div[class=info_TitleUrl]").text()));
        news.setSource("中国港口协会");
        return news;
    }
}
