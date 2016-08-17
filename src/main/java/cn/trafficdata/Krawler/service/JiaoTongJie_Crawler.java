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
public class JiaoTongJie_Crawler extends SuperCrawler {


    protected Elements getLinkElements(Document doc) {
        return doc.select("div[class=list_ys] li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return null;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String sourceAtime=doc.select("div[class=wb_xwly").first().text();
        news=formatSource(sourceAtime,news);
        return news;
    }
}
