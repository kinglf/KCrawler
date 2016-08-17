package cn.trafficdata.Krawler.model;

import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Kinglf on 2016/8/13.
 */
public class LocalNews implements Serializable{
    private static final long serialVersionUID = 8291745614934779794L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(JSONObject.fromObject(this));
    }

    private int id;
    private String url;//原始链接

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String title;//标题

    public String getFenlei() {
        return fenlei;
    }

    public void setFenlei(String fenlei) {
        this.fenlei = fenlei;
    }

    private String fenlei;//分类
    private String thumbnail;//缩略图
    private String content;//新闻内容
    private String author;//作者
    private String datetime;//时间
    private String source;//来源
    private String jianjie;//简介
    private String key;//关键词以,号分割

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getVideo_vid() {
        return video_vid;
    }

    public void setVideo_vid(String video_vid) {
        this.video_vid = video_vid;
    }

    public String getVideo_cid() {
        return video_cid;
    }

    public void setVideo_cid(String video_cid) {
        this.video_cid = video_cid;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_pic() {
        return video_pic;
    }

    public void setVideo_pic(String video_pic) {
        this.video_pic = video_pic;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    private String video_type;//用来判断哪的视频
    private String video_vid;//vid
    private String video_cid;
    private String video_url;
    private String video_pic;
    private String video_title;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
