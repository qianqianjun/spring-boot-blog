package com.buct.blog.domain;

import lombok.Data;
import java.util.Date;

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
    private Date publishDate; // 发布日期
    private Integer visitorNum; // 访问人数
    private Integer outstanding; // 是否在首页轮播显示。
    private String imgurl; // 文章图片地址。
    private Integer status; // 当前文章的状态 1： 发布 0 编辑中
    public Article(){

    }
}
