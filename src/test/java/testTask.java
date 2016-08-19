/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.its114.com/html/news/chengshizhinengjiaotong/jiaotongyoudao/$.html";
        for(int i=2;i<=10;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
