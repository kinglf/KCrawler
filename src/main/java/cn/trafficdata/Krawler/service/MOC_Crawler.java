package cn.trafficdata.Krawler.service;

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
public class MOC_Crawler extends DocumentUtils implements ProcessDao {

    public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        Document doc=page2Doc(page);
        doc.setBaseUri(page.getWebURL().getURL());
        try{
        Elements linkEls=doc.select("div[class=dfxw_main_bottom] li");
            System.out.println("发现连接"+linkEls.size());
        for(Element linkEl:linkEls){
            String url=linkEl.select("a").attr("abs:href");
            System.out.println("发现连接"+url);
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
        Element titleEl=doc.select("h3").first();
        Element docEl=doc.select("div[class^=zcjdxl_main_top]").first();
        //先处理图片,然后过滤标签
        docEl=processImage(docEl);
        String content=RegexUtil.filterScript(docEl.html());
        content= RegexUtil.filterHtml(content).replace("\r","").replace("\n","");
        content=content.replaceAll("<p.*?>","<p>");
        content=RegexUtil.fiterHtmlTag(content,"img");
        String title=titleEl.text();
//        System.out.println(title+"-------"+content);
        saveResult(title,content);


        return true;
    }
}
