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
 * Created by Kinglf on 2016/8/12.
 * 修改
 */
public class MOC_Crawler extends SuperCrawler{


    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=dfxw_main_bottom] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return null;
    }

    protected Element getContentElement(Document doc) {
        return null;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String sourceAtime=doc.select("h4").first().text();
        news=formatSource(sourceAtime,news);
        return news;
    }
}
