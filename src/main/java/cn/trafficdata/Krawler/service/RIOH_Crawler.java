package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class RIOH_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul[class=list_con] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element elDoc=doc.select("div[class=content_contain]").first();
        Elements delEls=elDoc.select("div[align=right]");
        for(Element delEl:delEls){
            if(delEl.html().contains("返回")||delEl.html().contains("字体")){
                delEl.remove();
            }
        }
        return elDoc;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        news.setSource("交通运输部公路科学研究院");
        news.setDatetime(RegexUtil.filterDate(doc.select("div[class=content_userinfo]").text()));
        return news;
    }
}
