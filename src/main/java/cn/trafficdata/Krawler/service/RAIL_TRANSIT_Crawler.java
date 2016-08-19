package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class RAIL_TRANSIT_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        //POST方式提交
        String baseUrl="http://www.rail-transit.com/Detail_News.aspx?id=pageID";
        Elements linkEls=new Elements();
        for(int i=20;i<=37859;i++){
            Element element=new Element(Tag.valueOf("li"),"");
            element.html("<a href=\""+baseUrl.replace("pageID",i+"")+"\">");
            linkEls.add(element);
        }
        return linkEls;
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=pl_n_zi]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("p[class=riq]").first().html(),news,"&nbsp","-","来源","作者");
    }
}
