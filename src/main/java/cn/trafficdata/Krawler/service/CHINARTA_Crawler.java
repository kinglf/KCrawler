package cn.trafficdata.Krawler.service;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.DocumentUtils;
import cn.trafficdata.Krawler.utils.RegexUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/17.
 * 修改
 */
public class CHINARTA_Crawler extends SuperCrawler {

    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=newsChannelContent] div[class=newsInfo]");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return null;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=newsDetailsContent]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String sourceStr=doc.select("div[class=newsTime]").first().text();
        if(sourceStr!=null){
            String[] arr=sourceStr.split(" ");
            if(arr.length<2){
                return news;
            }
            String source=arr[0].replace("来源","").replace("：","");
            String author=arr[1];
            String time= RegexUtil.filterDate(sourceStr);
            news.setSource(source);
            news.setAuthor(author);
            news.setDatetime(time);
        }
        return news;
    }
}
