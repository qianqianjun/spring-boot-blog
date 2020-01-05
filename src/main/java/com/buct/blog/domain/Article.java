package com.buct.blog.domain;

import lombok.Data;

import java.io.UnsupportedEncodingException;

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

    private String abstruct;

    private String categoryName; // view 属性，数据库中没有这个字段

    /**
     * 用于读取数据库中二进制文件。
     */
    private byte[] blob;

    public void setBlob(byte[] blob){
        this.blob=blob;
    }

    /**
     * 用于设置数据库二进制数据存储文件
     * @param content 要转换为二进制数据的 string
     */
    public void setBlob(String content){
        try {
            this.blob=content.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于获取 blob 二进制文件
     * @return 返回blob 二进制数据
     */
    public byte[] getBlob(){
        return this.blob;
    }

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

    /***
     * 将二进制数据转换为 string
     * @param blob 从数据库中读取的二进制文件
     */

    public void setContent(byte[] blob){
        try {
            this.content = new String(blob, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
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
