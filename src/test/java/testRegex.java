import cn.trafficdata.Krawler.utils.RegexUtil;

/**
 * Created by Kinglf on 2016/8/16.
 */
public class testRegex {

    //测试目的,正则表达式过滤[p]标签中多余的或空置的[p]标签

    public static void main(String[] args) {
        String regex="vid: '(.*?)'";
        String str="var related_video_info = {\n" +
                "        vid: 'l0191utgy16',\n" +
                "        cid: 'rdjwrufh3fhdwmy',\n" +
                "        url: 'http://v.qq.com/page/l/1/6/l0191utgy16.html',\n" +
                "        pic: 'http://vpic.video.qq.com/64710610/l0191utgy16_ori_1.jpg',\n" +
                "        title: '《交通必看档》首期上线 权威关注安全出行'\n" +
                "    };\n";
        System.out.println(RegexUtil.filterStr(str,regex,true));

    }
}
