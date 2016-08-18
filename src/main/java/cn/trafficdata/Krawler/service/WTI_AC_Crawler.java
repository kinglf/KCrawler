package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class WTI_AC_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[class=list-unstyled] li[class^=news-li]");//临时用,源地址无效,自己随便找的
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {

        return doc.select("article-content").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourceEl=doc.select("span[class=article-source]").first();
        news.setDatetime(RegexUtil.filterDate(sourceEl.text()));
        news.setSource("交通运输部水运科学研究院");
        return news;
    }
}
