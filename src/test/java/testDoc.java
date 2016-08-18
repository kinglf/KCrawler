import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.service.BaseCrawler;
import cn.trafficdata.Krawler.utils.FileUtils;
import cn.trafficdata.Krawler.utils.RegexUtil;
import net.sf.json.JSONArray;
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
public class testDoc {
    public static void main(String[] args) {
        Document doc= BaseCrawler.getDoc("http://www.chinawuliu.com.cn/zixun/201608/18/314494.shtml",true,15000);
        Elements select =doc.select("div[class=wzlb_con_left_table] li");
        int i=0;
        for(Element el:select){
            System.out.println(el.select("a"));
            i++;
        }
        System.out.println(i);

        //
        System.out.println(doc.select("div[class=main1_L6] dd").first().text());

        //
//        Element docEl = doc.select("div[class=new-detailed-cont]").first();
//        Elements delEls=docEl.select("p");
//        for(Element delEl:delEls){
//            if (delEl.html().contains("更多内容")||delEl.attr("class")=="new-align-right"){
//                delEl.remove();
//            }
//        }
//        System.out.println(processTableContent(docEl));
//        System.out.println(docEl.html().replaceAll("<(.*?) (.*?)>","<$1>"));


    }
    private static String processTableContent(Element docEl){
        List<Element> tableList=new ArrayList<Element>();
        List<String> tableConList=new ArrayList<String>();
        Elements tableEls=docEl.select("table");
        for(int i=0;i<tableEls.size();i++){
            Element tableEl=tableEls.get(i);
            tableList.add(tableEl);
            tableConList.add(tableEl.html().replaceAll("<(.*?) (.*?)>","<$1>"));
            tableEl.html("["+i+"]");
        }
//        for(String con:tableConList){
//            System.out.println(con);
//        }
        String content = RegexUtil.filterHtml(docEl.html());
//        System.out.println("未替换的html");
        for(int i=0;i<tableEls.size();i++){
            content=content.replace("["+i+"]",tableConList.get(i));
        }
        return content.replace("\n","").replace(" ","");

    }
    public static void getBBAQWlinks(){
        String url="http://www.bbaqw.com/ajax/getpage.htm";
        List<Integer> ids=new ArrayList<Integer>();
        for(int i=1;i<=112;i++){
            try {
                Document doc=Jsoup.connect(url).data("xnow",i+"").data("mid","27").data("flag","2").userAgent(CrawlerConstants.getUseragnet()).timeout(20000).post();
                JSONArray jsonArray=JSONArray.fromObject(doc.text());
                for(int j=0;j<jsonArray.size();j++){
                    int id=jsonArray.getJSONObject(j).getInt("id");
                    ids.add(id);
                }
                System.out.println(doc.text());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        for(Integer id:ids){
            String link="http://www.bbaqw.com/wz/"+id+".htm";
            System.out.println(link);
            FileUtils.appendFile("d:/bbaqwUID.txt",link+"\n");
        }
    }
}
