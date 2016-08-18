package cn.trafficdata.Krawler.service;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.DocumentUtils;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/18.
 */
public class _21ITS_Crawler extends DocumentUtils implements ProcessDao {
    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs = new ArrayList<WebURL>();
        Document doc = page2Doc(page);
        try {
            //抽象方法获得链接
            Elements linkEls=doc.select("div[class^=new-content-left] ul[class=news-list] li");
            for (Element linkEl : linkEls) {
                String url = linkEl.select("a").attr("abs:href");
                logger.info("发现新链接:{}", url);
                try {
                    WebURL webURL = new WebURL();
                    webURL.setURL(url);
                    webURL.setDepth((short) 1);
                    webURLs.add(webURL);
                } catch (Exception e) {
                    logger.error("error[{}]", url);
                }
            }
        } catch (Exception e) {
            logger.error("列表页解析失败,{},{}", page.getWebURL().getURL(), e);
            e.printStackTrace();
        }
        return webURLs;
    }


    public boolean processDoc(Page page) {
        Document doc = page2Doc(page);
        News pluginNews = null;
        try {
            pluginNews = ContentExtractor.getNewsByDoc(doc);
        } catch (Exception e) {
            logger.error("ContentExtractor插件调用错误,页面url-{},错误-{}", page.getWebURL().getURL(), e);
            return false;
        }
        LocalNews news = new LocalNews();
        //抽象方法填充资源,包括时间,作者,引用或转自
        Element sourceEl=doc.select("p[class=left]").first();
        news=formatSource(sourceEl.html(),news,"\n","-","来源","作者");
        if(news.getTitle()==null){
            news.setTitle(pluginNews.getTitle());
        }
        if (news.getDatetime() == null) {
            news.setDatetime(pluginNews.getTime());
        }
        //抽象方法填充内容区域,并去除无用的标签
        Element docEl=doc.select("div[class=new-detailed-cont]").first();
        Elements delEls=docEl.select("p");
        for(Element delEl:delEls){
            if (delEl.html().contains("更多内容")||delEl.attr("class")=="new-align-right"){
                delEl.remove();
            }
        }
        Element imgElement = docEl;
        if(imgElement==null){
            imgElement = pluginNews.getContentElement();
        }
        imgElement = processImage(imgElement);
        String content=null;
        if(imgElement.html().contains("<table>")){
            content=processTableContent(imgElement);
        }else{
            content = formatContent(imgElement.html());
        }
        news.setContent(content);
        news.setUrl(page.getWebURL().getURL());
        saveResult(news);
        logger.info("数据获取成功,{}", news.toString());
        return true;
    }

    private static String processTableContent(Element docEl){
        List<Element> tableList=new ArrayList<Element>();
        List<String> tableConList=new ArrayList<String>();
        Elements tableEls=docEl.select("table");
        for(int i=0;i<tableEls.size();i++){
            Element tableEl=tableEls.get(i);
            tableList.add(tableEl);
            tableEl.html("["+i+"]");
            tableConList.add(tableEl.html().replaceAll("<(.*?) (.*?)>","<$1>"));
        }
        String content = formatContent(docEl.html());
        for(int i=0;i<tableEls.size();i++){
            content=content.replace("["+i+"]",tableConList.get(i));
        }
        return content.replace("\n","").replace(" ","");

    }

}
