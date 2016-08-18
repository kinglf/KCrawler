package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class CHINAWULIU_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=yfTabContent]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[id=mainContent]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element sourceEl=doc.select("div[class=main1_L6] dd").first();
        String[] arr=sourceEl.text().replace("字号：T|T","").split("     ");
        if(arr.length==2){
            news.setDatetime(arr[0]);
            news.setSource(arr[1]);
        }else{
            news.setSource("中国物流与采购网");
        }
        return news;
    }
}
