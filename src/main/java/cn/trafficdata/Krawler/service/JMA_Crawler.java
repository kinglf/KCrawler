package cn.trafficdata.Krawler.service;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.utils.DocumentUtils;
import cn.trafficdata.Krawler.utils.RegexUtil;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/16.
 */
public class JMA_Crawler extends DocumentUtils implements ProcessDao {
     public List<WebURL> pageListHandler(Page page) {
        List<WebURL> webURLs=new ArrayList<WebURL>();
        //http://a.jiemian.com/index.php?m=lists&a=ajaxlist&cid=30&callback=jQuery110204512597946450114_1471343786945&page=2&_=1471343786946
        if(page.getParseData() instanceof HtmlParseData){
            HtmlParseData htmlParseData= (HtmlParseData) page.getParseData();
            String html=htmlParseData.getHtml();
            html=html.substring(html.indexOf("(")+1,html.length()-1);
            JSONObject jsonObject = JSONObject.fromObject(html);
            Document doc= Jsoup.parse(jsonObject.getString("rst"));
            Elements linkEls=doc.select("h3 a");
            for(Element linkEl:linkEls){
                if(linkEl.hasAttr("title")){
                    String url=linkEl.attr("href");
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
            }
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
        /**
         * 自定义区域
         */
        String author=null;
        String time=null;
        String source=null;
        try{
            author=doc.select("div[class=article-info] p[class=info-s] span[class=author] a").first().text();
            time=doc.select("div[class=article-info] p[class=info-s] span[class=date]").first().text();
            source= RegexUtil.filterStr(title,"【(.*?)】",true);
            title=title.replaceAll("【.*?】","");
        }catch (NullPointerException ue){
        }
        try{
            String thumbImg=doc.select("div[class=article-img] img").first().attr("abs:src");
            String imgName=getImageName(thumbImg);
            downImageByJsoup(thumbImg,imgName);
            news.setThumbnail(imgName);
        } catch (IOException e) {
        }catch (NullPointerException ue){
        }
        news.setTitle(title);
        news.setAuthor(author);
        news.setDatetime(time);
        news.setSource(source);


        //自定义结束
        if(news.getDatetime()==null){
            news.setDatetime(pluginNews.getTime());
        }
        Element imgElement=doc.select("div[class=article-content]").first();
        Element tempInfo=imgElement.select("div[class=article-source]").first();
        if(tempInfo!=null){
            imgElement.select("div[class=article-source]").first().html("");
        }
        imgElement=processImage(imgElement);
        String content=formatContent(imgElement.html());
        news.setContent(content);
        news.setUrl(page.getWebURL().getURL());
        saveResult(news);
        logger.info("数据获取成功,{}",news.toString());
        return true;
    }
}
