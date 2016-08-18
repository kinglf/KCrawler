/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.chinawuliu.com.cn/class-1-6($).shtml";
        for(int i=1;i<=267;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
