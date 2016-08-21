package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINESEPORT_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("span[style=float:left;]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("table[class=content]").first();

        Elements delELs=docEl.select("div[class=xtkchineseport.cnpfl]");
        for(Element delEl:delELs){
            if(delEl.html().contains("浏览器版本过低")){
                delEl.remove();
            }
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Elements sourEl=doc.select("div[class=top_about] a");
        news.setSource(sourEl.get(1).text());
        news.setDatetime(RegexUtil.filterDate(sourEl.text()));
        return news;
    }
}
