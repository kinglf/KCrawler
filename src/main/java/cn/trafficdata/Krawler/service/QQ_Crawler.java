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
 * 图片类新闻未处理
 * 未修改-原因同上
 */
public class QQ_Crawler extends DocumentUtils implements ProcessDao{
    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        Document doc=page2Doc(page);
        doc.setBaseUri(page.getWebURL().getURL());
        try{
            Elements linkEls=doc.select("div[class=leftList] ul li");
            for(Element linkEl:linkEls){
                String url=linkEl.select("a").attr("abs:href");
                logger.info("发现新链接:{}",url);
                try {
                    WebURL webURL = new WebURL();
                    webURL.setURL(url);
                    webURL.setDepth((short) 1);
                    webURLs.add(webURL);
                    webURL.setTag("综合新闻");
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
        //过滤[/p][p]之间的非法内容
        Document doc=page2Doc(page);
        News pluginNews=null;
        try {
            pluginNews= ContentExtractor.getNewsByDoc(doc);
        } catch (Exception e) {
            logger.error("ContentExtractor插件调用错误,页面url-{},错误-{}",page.getWebURL().getURL(),e);
            return false;
        }
        LocalNews news=new LocalNews();
        //处理视频
        news=processVideo(news,doc);
        news=processSource(news,doc);
        String title=pluginNews.getTitle();
        news.setTitle(title);
        try {
            String sourceAtime=doc.select("div[class=info]").first().text();
            news=formatSource(sourceAtime,news);
        }catch (Exception e){
        }
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

    private static LocalNews processVideo(LocalNews news,Document doc){
        Element vEle=doc.select("div[class^=rv-root-v2]").first();
        if(vEle!=null){
            String url=doc.select("h2[class=rv-title] a").attr("href");
            String title=doc.select("h2[class=rv-title] a").text();
            news.setVideo_url(url);
            news.setVideo_title(title);
            news.setVideo_type("QQ_VIDEO");
            vEle.remove();
        }

        return news;
    }
    private static LocalNews processSource(LocalNews news,Document doc){
        String time=RegexUtil.filterStr(doc.html(),"pubtime:'(.*?)'",true);
        news.setDatetime(time);
        return news;
    }
}
