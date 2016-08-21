/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.chnrailway.com/comments/Commentary/index_$.shtml";
        for(int i=2;i<=100;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
