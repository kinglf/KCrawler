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
public class CCCNEWS_Crawler extends SuperCrawler {


    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=listright_cont] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return null;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div[class=cas_content]").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element titleEl=doc.select("h2").first();
        if(titleEl!=null){
            Element delEl=titleEl.select("h5").first();
            if(delEl!=null){
                delEl.remove();
            }
            news.setTitle(titleEl.text());
        }
        String sourceStr=doc.select("div[class=subtitle]").html();
        if(sourceStr!=null){
            String author= RegexUtil.filterStr(sourceStr,"sp='(.*?)'",true);
            news.setAuthor(author);
            news.setSource("交通建设新闻网");
        }
        return news;
    }

}
