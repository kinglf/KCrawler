package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINAHIGHWAY_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=homehotext] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=thetext]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String sourEl=doc.select("div[class=thedate]").first().text();
         if(sourEl!=null){
            String[] arr=sourEl.split("　　");
            String date= RegexUtil.filterDate(sourEl);
            news.setDatetime(date);
                if(arr.length>2){
                    String source=arr[1];
                    String author=arr[2];
                    news.setSource(source);
                    news.setAuthor(author);
                }

            }
        return news;
    }
}
