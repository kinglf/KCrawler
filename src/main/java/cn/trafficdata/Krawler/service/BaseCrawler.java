package cn.trafficdata.Krawler.service;

import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.utils.DBUtil;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Kinglf on 2016/8/12.
 */
public class BaseCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    private static Logger logger = LoggerFactory.getLogger(BaseCrawler.class);

    public BaseCrawler() {
        super();
    }

    @Override
    public void init(int id, CrawlController crawlController) {
        super.init(id, crawlController);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBeforeExit() {
        super.onBeforeExit();
    }


    @Override
    public Object getMyLocalData() {
        return super.getMyLocalData();
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href=url.getURL().toLowerCase();
        Boolean b=!FILTERS.matcher(href).matches();
        return false;
    }


    @Override
    public void visit(Page page) {
        try{
//            getMyController().getCrawlersLocalData().add(page);
//            DBUtil.savePage(page);
        }catch (Exception e){
            logger.error("数据库连接/存储错误,{}",e);
        }
        int detph=page.getWebURL().getDepth();
        String url=null;
        try{
            url = URLDecoder.decode(page.getWebURL().getURL(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.debug("URL解码(UTF-8)失败-{}",page.getWebURL().getURL());
        }
        String className="cn.trafficdata.Krawler.service."+CrawlerConstants.SITE_MAP.get(page.getWebURL().getDomain());
        ProcessDao processer=null;
        try {
            processer= (ProcessDao) Class.forName(className).newInstance();
            switch (detph){
                case -1:
                    System.out.println("进入-1");
                    break;
                case 0://列表页
                    List<WebURL> toSchedule = processer.pageListHandler(page);
                    putWebURLs(page,toSchedule);//深度在接口/抽象的内容中获取   可以任意指定
                    break;
                case 1://详情页

                    boolean valid=processer.processDoc(page);
                    if(!valid){
                        logger.error("网页解析出错-{}",page.getWebURL().getURL());
                    }
                    break;
                default:
                    logger.debug("detph异常-{}",detph);
                    break;
            }
        } catch (ClassNotFoundException e) {
            logger.error("没有找到对应的类Class-{}",className);
            processer=new SuperCrawler();
            switch (detph){
                case -1:
                    System.out.println("进入-1");
                    break;
                case 0://列表页
                    List<WebURL> toSchedule = processer.pageListHandler(page);
                    putWebURLs(page,toSchedule);//深度在接口/抽象的内容中获取   可以任意指定
                    break;
                case 1://详情页
                    boolean valid=processer.processDoc(page);
                    if(!valid){
                        logger.error("网页解析出错-{}",page.getWebURL().getURL());
                    }
                    break;
                default:
                    logger.debug("detph异常-{}",detph);
                    break;
            }
        } catch (InstantiationException e) {
            logger.error("{}",e);
        } catch (IllegalAccessException e) {
            logger.error("{}",e);
        }

    }

    @Override
    public Thread getThread() {
        return super.getThread();
    }

    @Override
    public void setThread(Thread myThread) {
        super.setThread(myThread);
    }

    @Override
    public boolean isNotWaitingForNewURLs() {
        return super.isNotWaitingForNewURLs();
    }

    public static Document getDoc(String url, boolean ignoreContentType, Integer timeout) {
        Document doc = null;
        try {
            if (timeout == null) {
                timeout = Integer.valueOf(timeout);
            }
            doc = Jsoup.connect(url).timeout(timeout.intValue()).ignoreHttpErrors(true).followRedirects(true).userAgent(CrawlerConstants.getUseragnet()).ignoreContentType(ignoreContentType).get();
        } catch (IOException e) {
            logger.error("JSOUP请求失败!url-{},错误{}", url, e);
        }
        return doc;
    }

    public Document getDoc(String url) {
        return getDoc(url, false, null);
    }

    public Document getDoc(String url, boolean ignoreContentType) {
        return getDoc(url, ignoreContentType, null);
    }

    public void putWebURL(Page page, String url, List<WebURL> toSchedule, int depth, int priority) {
        WebURL webURL = new WebURL();
        webURL.setURL(url);
        webURL.setParentDocid(page.getWebURL().getDocid());
        webURL.setParentUrl(page.getWebURL().getURL());
        if (priority != -1) {
            webURL.setPriority((byte) priority);
        }
        webURL.setDocid(-1);
        if (depth == -1) {
            webURL.setDepth((short) (page.getWebURL().getDepth() + 1));
        } else {
            webURL.setDepth((short) depth);
        }
        int maxCrawlDepth = getMyController().getConfig().getMaxDepthOfCrawling();
        if ((maxCrawlDepth == -1) || page.getWebURL().getDepth() < maxCrawlDepth) {
            if (shouldVisit(page, webURL)) {
                if (getMyController().getRobotstxtServer().allows(webURL)) {
                    webURL.setDocid(getMyController().getDocIdServer().getNewDocID(webURL.getURL()));
                    if (toSchedule == null) {
                        getMyController().getFrontier().schedule(webURL);
                    } else {
                        toSchedule.add(webURL);
                    }
                } else {
                    logger.debug("目标站的robot.txt不允许访问此链接{}", webURL.getURL());
                }
            } else {
                logger.debug("设置的ShouldVisit不允许访问此链接-{}", webURL.getURL());
            }
        }
    }

    public void putWebURL(Page page, String url, int depth) {
        putWebURL(page, url, null, depth, -1);
    }

    public void putWebURL(Page page, String url, List<WebURL> toSchedule, int depth) {
        putWebURL(page, url, toSchedule, depth, -1);
    }
    public void putWebURLs(Page page,List<WebURL> urlList){
        List<WebURL> toSchedule =new ArrayList<WebURL>();

        for (WebURL wurl:urlList){
            WebURL webURL=new WebURL();
            webURL.setURL(wurl.getURL());
            webURL.setParentDocid(page.getWebURL().getDocid());
            webURL.setParentUrl(page.getWebURL().getURL());
            webURL.setDocid(-1);
            int depth=wurl.getDepth();
            if(depth==-1){
                webURL.setDepth((short) (page.getWebURL().getDepth()+1));
            }else{
                webURL.setDepth((short) depth);
            }
            int maxCrawlDepth=getMyController().getConfig().getMaxDepthOfCrawling();
            if((maxCrawlDepth==-1)||(page.getWebURL().getDepth()<maxCrawlDepth)) {
                    if (getMyController().getRobotstxtServer().allows(webURL)) {
                        webURL.setDocid(getMyController().getDocIdServer().getNewDocID(webURL.getURL()));
                        toSchedule.add(webURL);
                    } else {
                        logger.debug("目标站的robot.txt不允许访问此链接{}", webURL.getURL());
                    }

            }
        }

        getMyController().getFrontier().scheduleAll(toSchedule);
    }
}
