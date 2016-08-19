package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class WOKEJI_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.box2title>a");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.TRS_Editor").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String source=doc.select("div.timefun span#media_name").text();
        String author=doc.select("div.timefun span#art_source").text();
        String date=doc.select("div.timefun span#pub_date").text();
        if(source!=null){
            news.setSource(source);
        }else{
            news.setSource("中国科技网");
        }
        if(author!=null){
            news.setAuthor(author);
        }
        if(date!=null){
            news.setDatetime(date);
        }
        return news;
    }
}
