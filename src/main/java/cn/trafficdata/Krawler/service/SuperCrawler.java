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
 * Created by Kinglf on 2016/8/17.
 */
public abstract class SuperCrawler extends DocumentUtils implements ProcessDao{
          public List<WebURL> pageListHandler(Page page) {
            List<WebURL> webURLs = new ArrayList<WebURL>();
            Document doc = page2Doc(page);
            try {
                //抽象方法获得链接
                Elements linkEls=getLinkElements(doc);
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
            news=processSources(news,doc);
            if(news.getTitle()==null){
                news.setTitle(pluginNews.getTitle());
            }
            if (news.getDatetime() == null) {
                news.setDatetime(pluginNews.getTime());
            }
            //抽象方法填充内容区域,并去除无用的标签
            Element imgElement = getContentElement(doc);
            if(imgElement==null){
                imgElement = pluginNews.getContentElement();
            }
            news=processVideo(news,doc);
            imgElement = processImage(imgElement);
            String content = formatContent(imgElement.html());
            news.setContent(content);
            news.setUrl(page.getWebURL().getURL());
            saveResult(news);
            logger.info("数据获取成功,{}", news.toString());
            return true;
        }
    /**
     * 列表页处理链接用
     * @return
     */
    protected abstract Elements getLinkElements(Document doc);

    /**
     *  获得视频标签
     * @param news
     * @param doc
     * @return news
     */
    protected abstract LocalNews processVideo(LocalNews news, Document doc);

    /**
     * 得到有用的文字,去除无用的文字标签
     * @param doc
     * @return
     */
    protected abstract Element getContentElement(Document doc);

    /**
     * 处理资源,得到包括时间,作者,来源等
     * @param news
     * @param doc
     * @return
     */
    protected abstract LocalNews processSources(LocalNews news, Document doc);


}

