package cn.trafficdata.Krawler.utils;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.controller.CrawlerController;
import cn.trafficdata.Krawler.model.LocalNews;
import cn.trafficdata.Krawler.service.BaseCrawler;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import net.sf.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

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






    public static Element processImage(Element docEl){
        Elements els=docEl.select("img");
        for(Element el:els){
            if(el.hasAttr("src")){
                String imgUrl=el.attr("abs:src");
                if(!imgUrl.equals("")){
                    String imgName=getImageName(imgUrl);
                    el.html("[img src=\""+imgName+"\"]");
                    try{
                        downImageByJsoup(imgUrl,imgName);
                    }catch (IOException e){
                        logger.error("图片下载失败,请手动下载,文件名-{},连接-{}",imgName,imgUrl);
                        e.printStackTrace();
                    }
                }

            }
        }
        return docEl;
    }

    public static void downImageByJsoup(String imgUrl,String fileName) throws IOException {
        imgUrl=imgUrl.replace("/../","/");
        fileName= CrawlerConstants.IMAGE_FOLDER+"/"+fileName;
        Connection.Response resultImageResponse = Jsoup.connect(imgUrl).ignoreContentType(true).timeout(10000).execute();
        FileOutputStream out = (new FileOutputStream(new java.io.File(fileName)));
        out.write(resultImageResponse.bodyAsBytes());
    }

    public static String getImageName(String url){
        String extName=FileUtils.getFileExtName(url);
        Pattern imgPatterns = Pattern.compile(".*((bmp|gif|jpe?g|png|tiff?))$");
        if (!imgPatterns.matcher(extName).matches()) {
            extName="jpeg";
        }
        String imgName=MD5Util.MD5(url+System.currentTimeMillis()+ Math.random())+"."+extName;
        return imgName;
    }
    public static void saveResult(LocalNews news){
        Session session=null;
        try{
            session=HibernateUtil.getSession();
            session.beginTransaction();
            news.setFenlei(CrawlerController.isDoingName);
            session.save(news);
            session.getTransaction().commit();
        }catch (HibernateException e){
            logger.error("[{}]没有被存储,错误内容为[{}]",news.toString(),e);
            if(session!=null){
                session.getTransaction().rollback();
            }
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    public static String formatContent(String content,String split){
        // 此内容已将img标签更换为[img src=""]
        //过滤script标签
        content= RegexUtil.filterScript(content);
        //过滤注释
        content=content.replaceAll("<!-.*?->","");
        //过滤加粗标签
        content=content.replaceAll("<strong.*?>","[strong]");
        content=content.replace("</strong>","[/strong]");
        //去除文章里的所有html(不包括P)标签
        content=content.replaceAll("<p.*?>","[p]");
        content=content.replace("</p>","[/p]");
        content= RegexUtil.filterHtml(content).replace("\r","").replace("\n","");
        //再次过滤img标签
        content=RegexUtil.fiterHtmlTag(content,"img").replace(" ","");
        //过滤没用的标签
        String regex="\\[p\\](&nbsp;)*\\[/p\\]";
        content=content.replaceAll(regex,"").replace("&nbsp;","");
        if(!content.contains("[p]")){
            String[] arrs = content.split(split);
            StringBuilder stringBuilder=new StringBuilder();
            for(String arr:arrs){
                if(!arr.contains(split)&&arr.length()>0){
                    stringBuilder.append("[p]"+arr+"[/p]");
                }
            }
            content= stringBuilder.toString();
        }
        return content.replace(split,"");
        //得到完成的分段落的带strong、img的正文
    }
    public static String formatContent(String content){
        return formatContent(content,"　");
    }

    /**
     *
     * @param sourceAtime
     * @param news
     * @param split
     * @return
     */
    public static LocalNews formatSource(String sourceAtime,LocalNews news,String split){
        String[] arr=sourceAtime.split(split);
        String source=null;
        String time=null;
        String author=null;
        for(String str:arr){
            if(str.contains("时间")){
                time= RegexUtil.filterDate(str);
            }else if(str.contains("来源")){
                source=str.replace("来源","").replace("：","");
            }else if(str.contains("作者")){
                author=str.replace("作者","").replace("：","");
            }
        }
        news.setDatetime(time);
        news.setSource(source);
        news.setAuthor(author);
        return news;
    }

    public static LocalNews formatSource(String sourceAtime,LocalNews news,String split,String shijian,String laiyuan,String zuozhe){
        String[] arr=sourceAtime.split(split);
        String source=null;
        String time=null;
        String author=null;
        for(String str:arr){
            if(str.contains(shijian)){
                time= RegexUtil.filterDate(str);
            }else if(str.contains(laiyuan)){
                source=str.replace(laiyuan,"").replace("：","");
            }else if(str.contains(zuozhe)){
                author=str.replace(zuozhe,"").replace("：","");
            }
        }
        news.setDatetime(time);
        news.setSource(source);
        news.setAuthor(author);
        return news;
    }

    /**
     *
     * @param sourceAtime
     * @param news
     * @return
     */
    public static LocalNews formatSource(String sourceAtime,LocalNews news){
        return formatSource(sourceAtime,news," ");
    }
    public static void main(String[] args) {
//        Document doc= BaseCrawler.getDoc("http://www.moc.gov.cn/jiaotongyaowen/201608/t20160811_2075161.html",true,5000);
//        System.out.println(processImage(doc.select("div[class^=zcjdxl_main_top]").first()).html());
        try {
            String url="http://www.bbaqw.com/wz/34040.htm";
            News newsByUrl = ContentExtractor.getNewsByUrl(url);

            String html=newsByUrl.getContentElement().html();
            html=formatContent(html);
            System.out.println("AfterContent:"+html);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
