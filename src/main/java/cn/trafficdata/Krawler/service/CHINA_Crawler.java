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
 */
public class CHINA_Crawler extends DocumentUtils implements ProcessDao {
    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        Document doc=page2Doc(page);
        doc.setBaseUri(page.getWebURL().getURL());
        try{
            Elements linkEls=doc.select("div[class=news] div[id=newsdata] div[class=conR] h2");
            for(Element linkEl:linkEls){
                String url=linkEl.select("a").attr("abs:href");
                logger.info("发现新链接:{}",url);
                try {
                    WebURL webURL = new WebURL();
                    webURL.setURL(url);
                    webURL.setDepth((short) 1);
                    webURLs.add(webURL);
                }catch (Exception e){
                    logger.error("error[{}]",url);
                }
            }
        }catch (Exception e){
            logger.error("列表页解析失败,{},{}",page.getWebURL().getURL(),e);
            e.printStackTrace();
        }
        return webURLs;
    }

    public boolean processDoc(Page page) {
        Document doc=page2Doc(page);
        News pluginNews=null;
        try {
            pluginNews= ContentExtractor.getNewsByDoc(doc);
        } catch (Exception e) {
            logger.error("ContentExtractor插件调用错误,页面url-{},错误-{}",page.getWebURL().getURL(),e);
            return false;
        }
        LocalNews news=new LocalNews();
        String title=pluginNews.getTitle();
        news.setTitle(title);
        news=processSource(doc,news);
        if(news.getDatetime()==null){
            news.setDatetime(pluginNews.getTime());
        }
        Element imgElement=pluginNews.getContentElement();
        imgElement=processImage(imgElement);
        String content=formatContent(imgElement.html());
        news.setContent(content);
        news.setUrl(page.getWebURL().getURL());
        saveResult(news);
        logger.info("数据获取成功,{}",news.toString());
        return true;
    }
    private static LocalNews processSource(Document doc,LocalNews news){
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
