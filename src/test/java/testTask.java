/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.its114.com/html/news/guojiqianyan/$.html";
        for(int i=2;i<=11;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
