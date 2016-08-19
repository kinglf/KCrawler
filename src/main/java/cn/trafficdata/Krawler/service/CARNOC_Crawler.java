package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CARNOC_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        return doc.select("div.newslitable ul li");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEl=doc.select("div#newstext").first();
        Element delEl=docEl.select("div.imginnews").first();
        if(delEl!=null){
            delEl.remove();
        }
        Element delEl2=docEl.select("div#newspub").first();
        if(delEl2!=null){
            delEl2.remove();
        }
        Elements delEls=doc.select("p");
        for(Element del:delEls){
            if (del.html().contains("text/javascript")||del.html().contains("好文，推荐给大家")){
                del.remove();
            }
        }
        return docEl;
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        String author=doc.select("span#author_baidu").first().text().replace("作者：","");
        String source=doc.select("span#source_baidu").first().text().replace("来源：","");
        if(author!=null){
            news.setAuthor(author);
        }
        if(source!=null){
            news.setSource(source);
        }
        return news;
    }
}
