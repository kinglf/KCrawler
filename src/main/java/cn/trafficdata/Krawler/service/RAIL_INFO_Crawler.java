package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class RAIL_INFO_Crawler extends  SuperCrawler{
    protected Elements getLinkElements(Document doc) {
        return doc.select("table.lstdash>tbody>tr>td>a");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("table.h14l28").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        return formatSource(doc.select("td[align=left]").text(),news," ",".","信息来源","作者");
    }
}
