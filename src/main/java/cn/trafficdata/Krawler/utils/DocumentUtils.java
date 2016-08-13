package cn.trafficdata.Krawler.utils;

import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.model.News;
import cn.trafficdata.Krawler.service.BaseCrawler;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Kinglf on 2016/8/13.
 */
public class DocumentUtils {
    public static Logger logger = LoggerFactory.getLogger(DocumentUtils.class);
    public static Document page2Doc(Page page){
        Document doc=null;
        if(page.getParseData() instanceof HtmlParseData){
            HtmlParseData htmlParseData= (HtmlParseData) page.getParseData();
            String html=htmlParseData.getHtml();
            doc = Jsoup.parse(html);
            doc.setBaseUri(page.getWebURL().getURL());
        }
        return doc;
    }



    public static void main(String[] args) {
       Document doc= BaseCrawler.getDoc("http://www.moc.gov.cn/jiaotongyaowen/201608/t20160811_2075161.html",true,5000);
        processImage(doc);

    }
    public static Element processImage(Element docEl){
        Elements els=docEl.select("img");
        for(Element el:els){
            if(el.hasAttr("src")){
                String imgUrl=el.attr("abs:src");
                String imgName=getImageName(imgUrl);
                el.html("[trafficdataImg src=\""+imgName+"\"]trafficdataImg");
                try{
                    downImageByJsoup(imgUrl,imgName);
                }catch (IOException e){
                    logger.error("图片下载失败,请手动下载,文件名-{},连接-{}",imgName,imgUrl);
                    e.printStackTrace();
                }
            }
        }
        return docEl;
    }

    public static void downImageByJsoup(String imgUrl,String fileName) throws IOException {
        fileName= CrawlerConstants.IMAGE_FOLDER+"/"+fileName;
        Connection.Response resultImageResponse = Jsoup.connect(imgUrl).ignoreContentType(true).execute();
        FileOutputStream out = (new FileOutputStream(new java.io.File(fileName)));
        out.write(resultImageResponse.bodyAsBytes());
    }

    public static String getImageName(String url){
        String extName=FileUtils.getFileExtName(url);
        return MD5Util.MD5(url+System.currentTimeMillis()+ Math.random())+"."+extName;
    }
    public static void saveResult(String title,String content){
        News news=new News(title,content);
        DBUtil.getRedis().lpush(CrawlerConstants.RESULT_TABLE_NAME.getBytes(),SerializeUtil.serialize(news));
    }

}
