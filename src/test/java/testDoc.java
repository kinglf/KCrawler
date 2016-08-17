import cn.trafficdata.Krawler.constants.CrawlerConstants;
import cn.trafficdata.Krawler.service.BaseCrawler;
import cn.trafficdata.Krawler.utils.FileUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kinglf on 2016/8/16.
 */
public class testDoc {
    public static void main(String[] args) {
//        Document doc= BaseCrawler.getDoc("http://www.bbaqw.com/ajax/getpage.htm?xnow=2&mid=27&flag=2",true,5000);

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
