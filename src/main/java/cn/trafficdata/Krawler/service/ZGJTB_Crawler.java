package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.News;
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
 */
public class ZGJTB_Crawler extends DocumentUtils implements ProcessDao{


    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        Document doc=page2Doc(page);
        doc.setBaseUri(page.getWebURL().getURL());
        try{
            Elements linkEls=doc.select("div[class=p-list] ul[class=p-li-ul] li");
            for(Element linkEl:linkEls){
                String url=linkEl.select("a").attr("abs:href");
                logger.info("发现新链接:{}",url);
                try {
                    WebURL webURL = new WebURL();
                    webURL.setURL(url);
                    webURL.setDepth((short) 1);
                    webURLs.add(webURL);
                }catch (Exception e){
                    logger.error("url:{}error[{}]",url,e);
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
        String title=doc.select("div[class=t-title] h1").first().text();
        String sourceAtime=doc.select("div[class=t-title] p").first().text();
        String[] arr=sourceAtime.split(" ");
        String source=null;
        String time=null;
        String author=null;
        for(String str:arr){
            if(str.contains("时间")){
                time=RegexUtil.filterDate(str);
            }else if(str.contains("来源")){
                source=str.replace("来源","").replace("：","");
            }else if(str.contains("作者")){
                author=str.replace("作者","").replace("：","");
            }
        }
        Element docEl=doc.select("div[class=m]").first();
        //先处理图片,然后过滤标签
        docEl=processImage(docEl);
        String content= RegexUtil.filterScript(docEl.html());
        content=content.replaceAll("<!-.*?->","");
        content=content.replaceAll("<strong.*?>","[strong]");
        content=content.replace("</strong>","[/strong]");
        content= RegexUtil.filterHtml(content).replace("\r","").replace("\n","");
        content=content.replaceAll("<p.*?>","<p>").replace("　","");
        content=RegexUtil.fiterHtmlTag(content,"img").replace(" ","");
        News news=new News();
        news.setTitle(title);
        news.setContent(content);
        news.setSource(source);
        news.setDatetime(time);
        news.setAuthor(author);
        news.setUrl(page.getWebURL().getURL());
//        saveResult(news);
        System.out.println(news.toString());
        return true;
    }
}
