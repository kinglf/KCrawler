/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.cccnews.cn/zjxw/index_rpage.htm";
        for(int i=1;i<=99;i++){
            System.out.println(baseUrl.replace("rpage",i+""));
        }
    }
}
