import cn.trafficdata.Krawler.service.BaseCrawler;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Kinglf on 2016/8/16.
 */
public class testDoc {
    public static void main(String[] args) {
        Document doc= BaseCrawler.getDoc("http://a.jiemian.com/index.php?m=lists&a=ajaxlist&cid=30&page=1&_=1471335720017",true,5000);
//        String html=doc.
//        System.out.println(html);
//        String jsonStr=html.substring(html.indexOf("(")+1,html-)
    }
}
