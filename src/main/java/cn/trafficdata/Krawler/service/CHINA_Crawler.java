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
public class CHINA_Crawler extends SuperCrawler {

    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=news] div[id=newsdata] div[class=conR] h2");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return null;
    }

    protected Element getContentElement(Document doc) {
        return null;//未进行指定时,就使用WebCollector中的解析模块
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Element el=doc.select("div[class=chan_newsInfo]").first();
        if(el!=null){

            Element delEl=el.select("div[class=chan_newsInfo_lin]").first();
            if (delEl!=null){
                delEl.remove();
            }
            Element delEl2=el.select("div[class=chan_newsInfo_comment]").first();
            if (delEl2!=null){
                delEl2.remove();
            }
            String sourceStr=el.text();
            String time= RegexUtil.filterDate(sourceStr);
            String source=sourceStr.replace(time,"").trim().replace("&nbsp;","");
            news.setDatetime(time);
            news.setSource(source);

        }

        return news;
    }
}
