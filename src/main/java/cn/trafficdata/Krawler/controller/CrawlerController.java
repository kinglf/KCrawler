package cn.trafficdata.Krawler.controller;


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
    }





}
