package cn.trafficdata.Crawler.TaskShell.util;

/**
 * Created by Kinglf on 2016/8/15.
 */
public class Task {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int id;
    private String url;
    private int flag;//0未执行,1正在执行
}