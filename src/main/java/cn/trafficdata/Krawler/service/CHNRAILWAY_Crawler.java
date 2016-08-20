package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kinglf on 2016/8/19.
 */
public class CHNRAILWAY_Crawler extends SuperCrawler {
    protected Elements getLinkElements(Document doc) {
        if(doc.baseUri().contains("txy.chnrailway.com")){
            Elements links=doc.select("section.news-item h3 a");
            return links;
        }
        return doc.select("div.newslist div.tit");
    }

    protected LocalNews processVideo(LocalNews news, Document doc) {
        return news;
    }

    protected Element getContentElement(Document doc) {
        Element docEL=doc.select("article.page-content").first();
        if(docEL!=null){
            Elements delEls=docEL.select("h1");
            for(Element delEl:delEls){
                delEl.remove();
            }
            Elements delEls2=docEL.select("ul.clearfix");
            for(Element delEl:delEls2){
                delEl.remove();
            }
            return docEL;
        }
        return doc.select("div.content").first();
    }

    protected LocalNews processSources(LocalNews news, Document doc) {
        Elements sourceEl=doc.select("article.page-content ul.clearfix li");
        if(sourceEl.size()==4){
            String author=sourceEl.get(0).text();
            String source=sourceEl.get(1).text();
            String time=sourceEl.get(2).text();
            if (author != null) {
                news.setAuthor(author);
            }
            if (source != null) {
                news.setSource(source);
            }
            if (time != null) {
                news.setDatetime(time);
            }
            return news;
        }
        return formatSource(doc.select("div.info").html(),news,"\n","-","来源","作者");
    }

    public static void main(String[] args) {
        //抽取通讯员单独的页面布局
        String baseUrl="http://txy.chnrailway.com/index.aspx?page=$";
        for(int i=1;i<=4610;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
