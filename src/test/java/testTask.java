/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.chinaports.org/list/1003_1028_$.htm";
        for(int i=1;i<=94;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
