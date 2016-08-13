package cn.trafficdata.Krawler.model;

import java.io.Serializable;

/**
 * Created by Kinglf on 2016/8/13.
 */
public class News implements Serializable{
    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private static final long serialVersionUID = -2296876911607498211L;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}
