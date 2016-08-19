package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CANNEWS_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("ul.ul_4 li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class^=article-content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String date=doc.select("div.article-infos span.date").text();
        String source = doc.select("div.article-infos span.source").text();
        String author = doc.select("div.article-infos span.editors").text();

        if(date!=null){
            news.setDatetime(date);
        }
        if(source!=null){
            news.setSource(source);
        }
        if(author!=null){
            news.setAuthor(author);
        }
        return news;
    }
}
