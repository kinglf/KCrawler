import cn.trafficdata.Krawler.utils.RegexUtil;

/**
 * Created by Kinglf on 2016/8/16.
 */
public class testRegex {

    //测试目的,正则表达式过滤[p]标签中多余的或空置的[p]标签

    public static void main(String[] args) {
        String regex="【.*?】";
        String str="【中国新闻周刊】高铁再提速有多少“纠结”";
        System.out.println(RegexUtil.filterStr(str,regex));

    }
}
