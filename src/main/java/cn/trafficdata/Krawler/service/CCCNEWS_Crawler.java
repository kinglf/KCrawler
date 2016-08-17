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
public class CCCNEWS_Crawler extends DocumentUtils implements ProcessDao {
    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        Document doc=page2Doc(page);
        doc.setBaseUri(page.getWebURL().getURL());
        try{
            Elements linkEls=doc.select("div[class=listright_cont] li");
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
        news=processSource(doc,news);
        String sourceAtime=doc.select("div[class=subtitle]").first().text();
        news=formatSource(sourceAtime,news);
        if(news.getDatetime()==null){
            news.setDatetime(pluginNews.getTime());
        }
        Element imgElement=doc.select("div[class=cas_content]").first();
        imgElement=processImage(imgElement);
        String content=formatContent(imgElement.html());
        news.setContent(content);
        news.setUrl(page.getWebURL().getURL());
        saveResult(news);
        logger.info("数据获取成功,{}",news.toString());
        return true;
    }
    private static LocalNews processSource(Document doc,LocalNews news){
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
