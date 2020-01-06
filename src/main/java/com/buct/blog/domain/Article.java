package com.buct.blog.domain;

import lombok.Data;

/**
 * @author 高谦
 * 数据库文章表结构
 */
@Data
public class Article {
    private Integer id; // 文章标号（主键）
    private String title; // 标题
    private String content; // 内容
    private Integer type; // 专栏标号
    private String publishDate; // 发布日期
    private Integer visitorNum; // 访问人数
    private Integer outstanding; // 是否在首页轮播显示。
    private String imgurl; // 文章图片地址。
    private Integer status; // 当前文章的状态 1： 发布 0 编辑中

    private String abstruct; // 当前文章的摘要，数据库没有这一项。
    public Article(){

    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Integer getOutstanding() {
        return outstanding;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getType() {
        return type;
    }

    public Integer getVisitorNum() {
        return visitorNum;
    }

    public String getAbstruct() {
        return abstruct;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbstruct(String abstruct) {
        this.abstruct = abstruct;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOutstanding(Integer outstanding) {
        this.outstanding = outstanding;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setVisitorNum(Integer visitorNum) {
        this.visitorNum = visitorNum;
    }
}
