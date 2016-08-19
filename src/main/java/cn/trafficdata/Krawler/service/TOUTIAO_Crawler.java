package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class TOUTIAO_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("li.article-item div.info-inner");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        Element videoEl=doc.select("div.tt-video-box").first();
        if(videoEl!=null){
            String videoID=videoEl.attr("tt-videoid");
            String videoimg=videoEl.attr("tt-poster");
            if(videoID!=null){
                news.setVideo_vid(videoID);
                news.setVideo_pic(videoimg);
                news.setVideo_type("TOUTIAO");
            }
        }
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.article-content").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String source=doc.select("div[class^=minibar] span.src").text();
        String date=doc.select("div[class^=minibar] span.time").text();
        if(source!=null){
            news.setSource(source);
        }else{
            news.setSource("今日头条");
        }
        if(date!=null){
            news.setDatetime(date);
        }
        return news;
    }
}
