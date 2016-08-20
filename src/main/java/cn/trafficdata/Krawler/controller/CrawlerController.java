package cn.trafficdata.Krawler.controller;


import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.service.BaseCrawler;
import cn.trafficdata.Krawler.service.TaskServices;
import cn.trafficdata.Krawler.utils.MD5Util;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kinglf on 2016/8/12.
 */
public class CrawlerController {
    private static Logger logger= LoggerFactory.getLogger(CrawlerController.class);
    public static CrawlController controller;
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




        controller=getDefaultCrawlController();
        loadTasks(controller);
        controller.start(BaseCrawler.class,20);


        //保存
//        List<Object> crawlersLocalData = controller.getCrawlersLocalData();
//        for(Object page:crawlersLocalData){
//            if(page instanceof Page){
//                Page rpage= (Page) page;
//
//                try {
//                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/crawl/xuliehua/"+ MD5Util.MD5(((Page) page).getWebURL().getURL())));
//                    oos.writeObject(page);
//                    oos.flush();
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
    /*
    * 加载任务
    * */
    private static void loadTasks(CrawlController controller){
//        controller.addSeed("http://www.moc.gov.cn/jiaotongyaowen/",-1);
//        controller.addSeed("http://www.zgjtb.com/node_142.htm",-1);
//        controller.addSeed("http://www.jiaotongjie.com/hotnews/index_12.html",-1);
//        controller.addSeed("http://money.163.com/special/002526O5/transport.html",-1);
//        controller.addSeed("http://www.jiemian.com/lists/30.html",-1);//此链接不准,用下方json格式链接,总计为89页
//        controller.addSeed("http://a.jiemian.com/index.php?m=lists&a=ajaxlist&cid=30&callback=jQuery110204512597946450114_1471343786945&page=2&_=1471343786946",-1);
//        controller.addSeed("http://auto.qq.com/l/service/trafficnews/list.htm",-1);
//        controller.addSeed("http://auto.sohu.com/qichejiaotong/index.shtml",-1);
//        controller.addSeed("http://news.c-cc.cn/new_news.asp?classid=0&Page=1",-1);
//        controller.addSeed("http://www.bbaqw.com/wzs/jtaq/jtrd.htm");
//        controller.addSeed("http://www.chinarta.com/News/kTraffic/index.asp?page=1",-1);
//        controller.addSeed("http://news.china.com/baike_5Lqk6YCa.html",-1);
//        controller.addSeed("http://www.cccnews.cn/zjxw/index.htm",-1);
//        String[] links=new String[]{"http://www.bbaqw.com/wzs/jtaq/jtrd.htm","http://www.chinarta.com/News/kTraffic/","http://news.china.com/baike_5Lqk6YCa.html","http://www.cccnews.cn/zjxw/","http://www.chinautc.com/templates/H_news/group.aspx?nodeid=3","http://www.jt12345.com/portal.php?mod=list&catid=29","http://www.jiaotongjie.com/csjt/","http://www.21its.com/News/NewsList.aspx?typeid=1","http://www.cn-its.com.cn/news/","http://www.its114.com/html/news/","http://www.chinahighway.com/xw/xw_index.php","http://www.china-highway.com/Home/News/index/fid/80.html","http://www.rioh.cn/Stencil/002/dtxw.asp?xcd=2","http://www.chinagonglu.com/A/?L-1010803053.Html","http://www.zgjtb.com/gonglu/node_210.htm","http://www.zgsyb.com/html/news/node_1580.htm","http://www.wti.ac.cn/listb.aspx?page=1&menuid=1332","http://epaper.zgsyb.com/html/2016-05/09/node_2.htm","http://www.cwtca.org.cn/newsAction.do?action=news&isType=2","http://www.chinaports.com/portlspnews/1-20-hot-null/query","http://www.chinaports.org/list/1003_1023_0.htm","http://www.chinaports.org/list/1003_1026_0.htm","http://www.chineseport.cn/list.php?fid=47","http://info.shippingchina.com/bluenews/index/blist/type/%E4%BB%8A%E6%97%A5%E8%81%9A%E7%84%A6.html","http://info.shippingchina.com/bluenews/index/list/type/%E6%B5%B7%E8%BF%90%E6%96%B0%E9%97%BB.html","http://info.shippingchina.com/bluenews/index/list/type/%E6%B8%AF%E5%8F%A3%E6%96%B0%E9%97%BB.html","http://www.csoa.cn/redianjj/","http://www.cj-pilot.com.cn:9001/news-list.php?pagenumber=218-3","http://www.jiaotongjie.com/sy/hot/","http://www.56beijing.org/news/shuiyun/","http://www.chinawuliu.com.cn/zixun/class_6.shtml","http://www.rail-transit.com/List_News2.aspx?CateID=1","http://www.rail-transit.com/List_News2.aspx?CateID=2","http://www.rail-transit.com/List_News2.aspx?CateID=3","http://www.ccmetro.com/search/searchnews.aspx","http://www.bjsubway.com/news/qyxw/yyzd/","http://rail.ally.net.cn/html/hyzix/ganxiantielu/","http://rail.ally.net.cn/html/xinjialanmu/","http://www.chnrailway.com/news/gn/","http://www.rails.com.cn/news.php?classid=11","http://www.rail-info.com/info/tlzx.jsp","http://txy.chnrailway.com/","http://www.caac.gov.cn/XWZX/MHYW/","http://www.zgjtb.com/minhang/node_105.htm","http://www.jiaotongjie.com/mh/xwjd/","http://www.jiaotongjie.com/mh/jc/","http://www.caacnews.com.cn/n/n11.aspx?pageid=1","http://www.caacnews.com.cn/n/n14.aspx?pageid=1","http://www.caacnews.com.cn/n/n15.aspx?pageid=1","http://news.carnoc.com/hotnews.html","http://news.cnair.com/minhang/","http://www.chinaports.org/list/1003_1025_0.htm","http://www.chineseport.cn/list.php?fid=78","http://info.shippingchina.com/bluenews/index/list/type/%E8%B4%B8%E6%98%93%E6%96%B0%E9%97%BB.html","http://www.yunquna.com/news/newslist-2-15-1.html","http://www.yunquna.com/news/newslist-4-15-1.html","http://www.yunquna.com/news/newslist-3-15-1.html","http://www.yunquna.com/news/newslist-1-15-1.html","http://www.chinaports.org/list/1003_1028_0.htm","http://www.china-its.org/cnt_list_2_1.html","http://www.66its.com/news/","http://www.itsc.cn/article_cat.php?id=6009","http://www.its114.com/html/news/chengshizhinengjiaotong/","http://zizhan.mot.gov.cn/zfxxgk/index.html","http://zizhan.mot.gov.cn/zfxxgk/index.html?gk=252/259/262/list_4557.htm","http://www.mot.gov.cn/zhengcejiedu/","http://zizhan.mot.gov.cn/zfxxgk/index.html?gk=252/265/list_4557.htm","http://auto.163.com/special/tech/","http://www.chnrailway.com/tdkj/kjdt/index.shtml","http://www.mte.net.cn/remenAllList.aspx","http://www.afzhan.com/channel/t2364/tech.html","http://www.cnits.net.cn/html/news/","http://www.cnits.net.cn/html/news/jishu/","http://www.ccmetro.com/technic/","http://www.cannews.com.cn/xwzx/kjqy/","http://rail.ally.net.cn/html/art/","http://www.chinametro.net/index.php?m=news&id=441","http://www.zgcsgd.com/list.php?fid=63","http://www.firetc.com/news/list.php?catid=836","http://www.firetc.com/news/list.php?catid=837","http://www.firetc.com/news/list.php?catid=834","http://www.wokeji.com/qyts/1_qykj/index_1.shtml","http://www.wokeji.com/explore/qykj/","http://www.toutiao.com/m5510943119/","http://www.its114.com/html/news/guojiqianyan/","http://www.cjtkj.com/research/","http://auto.163.com/special/tech/","http://metro.controleng.cn/IndustryNews.aspx","http://www.cannews.com.cn/xwzx/perspective/","http://www.chnrailway.com/comments/Commentary/","http://info.shippingchina.com/bluenews/index/blist/type/%E6%B7%B1%E5%BA%A6%E5%88%86%E6%9E%90.html","http://www.chnrailway.com/tdkj/kjzt/index.shtml","http://www.chnrailway.com/comments/Commentary/","http://www.cannews.com.cn/xwzx/perspective/"};
////
//        for(String link:links){
//            controller.addSeed(link,-1);
//        }

        try {
            List<String> taskList = TaskServices.loadTasks();
            for(String task:taskList){
                controller.addSeed(task,-1);
                logger.info("系统得到任务并装载{}",task);
                //暂未对任务进行标记
            }
        } catch (SQLException e) {
            logger.error("数据库连接错误{}",e);
        }

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
        config.setPolitenessDelay(500);
        config.setConnectionTimeout(20000);
        config.setIncludeBinaryContentInCrawling(true);//图片下载
        config.setMaxDownloadSize(1048576*5); //最大范围5M
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
class TaskThread implements Runnable{
    private static Logger logger=LoggerFactory.getLogger(TaskThread.class);

    private static CrawlController controller;
    public TaskThread(CrawlController controller){
        this.controller=controller;
        new Thread(this).start();
    }
    public void run() {
        while(true){
            try {
                List<String> taskList = TaskServices.loadTasks();
                for(String task:taskList){
                    controller.addSeed(task,-1);
                    logger.info("系统得到任务并装载{}",task);
                    //暂未对任务进行标记
                }
            } catch (SQLException e) {
                logger.error("数据库连接错误{}",e);
            }


            try {
                Thread.sleep(10000);
                logger.info("没有找到任务,间隔10秒钟后重试");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}