/**
 * Created by Kinglf on 2016/8/17.
 */
public class testTask {
    public static void main(String[] args) {
        String baseUrl="http://www.zgjtb.com/gonglu/node_210_$.htm";
        for(int i=2;i<=8;i++){
            System.out.println(baseUrl.replace("$",i+""));
        }
    }
}
