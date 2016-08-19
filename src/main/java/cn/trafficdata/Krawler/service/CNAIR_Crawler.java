package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CNAIR_Crawler extends  SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("span.mainNewsTitle");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.NewsContent").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String author=doc.select("div.artInfo div#author").text();
        String source=doc.select("div.artInfo span#MediaName").text();
        String time= RegexUtil.filterDate(doc.select("div.artInfo").text());
        if(author!=null){
            news.setAuthor(author);
        }
        if(source!=null){
            news.setSource(source);
        }
        if(time!=null){
            news.setDatetime(time);
        }
        return news;
    }
}
