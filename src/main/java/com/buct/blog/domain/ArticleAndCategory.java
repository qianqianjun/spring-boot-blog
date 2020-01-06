package com.buct.blog.domain;

import lombok.Data;

@Data
public class ArticleAndCategory {
    private Integer articleId; // 文章标号（主键）
    private String title; // 标题
    private String content; // 内容
    private Object blob;
    private Integer type; // 专栏标号
    private String articlePublishDate; // 发布日期
    private Integer visitorNum; // 访问人数
    private Integer outstanding; // 是否在首页轮播显示。
    private String articleImgurl; // 文章图片地址。
    private Integer status; // 当前文章的状态 1： 发布 0 编辑中
    private String articleAbstruct; // 当前文章的摘要，数据库没有这一项。

    private String name;
    private String description;
    private String categoryImgurl;
    private String categoryPublishDate;

    public void setContent(byte[] bytes){
        try{
            this.content=new String(bytes,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
