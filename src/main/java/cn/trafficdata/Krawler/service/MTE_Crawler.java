package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class MTE_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        //POST方式提交
        String baseUrl="http://www.mte.net.cn/RemenDetails.aspx?NewsId=pageID";
        Elements linkEls=new Elements();
        for(int i=398;i<=7510;i++){
            Element element=new Element(Tag.valueOf("li"),"");
            element.html("<a href=\""+baseUrl.replace("pageID",i+"")+"\">");
            linkEls.add(element);
        }
        return linkEls;
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        return doc.select("div.zhhzfont2").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String author=doc.select("span#ctl00_ContentPlaceHolder1_lblAuthor").first().text();
        String time=doc.select("span#ctl00_ContentPlaceHolder1_lblTime").first().text();
        String source=doc.select("span#ctl00_ContentPlaceHolder1_lblUnit").first().text();
        if(author!=null){
            news.setAuthor(author);
        }
        if(time!=null){
            news.setDatetime(time);
        }
        if(source!=null){
            news.setSource(source);
        }
        return news;
    }
}
