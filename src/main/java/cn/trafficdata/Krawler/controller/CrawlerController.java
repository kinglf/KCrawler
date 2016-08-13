package cn.trafficdata.Krawler.controller;


import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.service.BaseCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by Kinglf on 2016/8/12.
 */
public class CrawlerController {
    private static Logger logger= LoggerFactory.getLogger(CrawlerController.class);

    public static void main(String[] args) {
        /*   未考虑验证码和屏蔽方案
        * 1.加载任务
        * 2.初始化crawler4j
        * 3.将所有根Link放到crawler4j中
        * 4.执行超级类,根据domain通过反射来获得解析方法
        *   depth://0,根,列表页
        *          //1,详情页
        *
        *
        *
        *      redis数据存储
        *
        *
        *
        *
        *
        *
        *
        * */
        logger.info("test");
        System.out.println("asdasd");
//        controller.start(M18Crawler.class, numberOfCrawlers);



        CrawlController controller=getDefaultCrawlController();
        loadTasks(controller);
        controller.start(BaseCrawler.class,1);

    }
    /*
    * 加载任务
    * */
    private static void loadTasks(CrawlController controller){
        controller.addSeed("http://www.moc.gov.cn/jiaotongyaowen/",-1);
    }

    /*
    * 得到crawlController初始化
    *
    * */
    public static CrawlController getDefaultCrawlController(){
        String crawlStorageFolder=CrawlerConstants.STORAGE_FOLDER;
        int maxDepthOfCrawling=2;
        int maxOutgoingLinksToFollow=0;

        CrawlConfig config=new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setUserAgentString(CrawlerConstants.USERAGNET);
        config.setMaxOutgoingLinksToFollow(maxOutgoingLinksToFollow);
        config.setFollowRedirects(true);
        config.setPolitenessDelay(1000);

        PageFetcher pageFetcher=new PageFetcher(config);
        RobotstxtConfig robotstxtConfig=new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer=new RobotstxtServer(robotstxtConfig,pageFetcher);

        CrawlController controller=null;
        try{
            controller = new CrawlController(config,pageFetcher,robotstxtServer);
        } catch (Exception e) {
            logger.error("初始化爬虫失败{}",e);
        }
        return controller;
    }




}
