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
    private String thumbnail;//缩略图
    private String content;//新闻内容
    private String author;//作者
    private String datetime;//时间
    private String source;//来源
    private String jianjie;//简介
    private String key;//关键词以,号分割

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
