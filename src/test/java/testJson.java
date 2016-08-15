import cn.trafficdata.Krawler.model.News;
import cn.trafficdata.Krawler.utils.RegexUtil;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class testJson {
    public static void main(String[] args) {
        String html="<h4 style=\"text-align: center;margin: 6px auto;color:#666;font-size:13px\">来源：中国交通新闻网&nbsp;&nbsp;&nbsp;&nbsp;2016-08-09</h4>";
        Document doc= Jsoup.parse(html);
        String h4 = doc.select("h4").text();
        RegexUtil.filterDate(h4);

    }
}
