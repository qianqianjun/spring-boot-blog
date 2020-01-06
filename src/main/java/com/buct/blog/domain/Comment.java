package com.buct.blog.domain;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer aid;
    private String publishDate;
    private String content;
    private String reply;
    private String email;
    private Integer ignore;

    // 下面是comment 的view属性，数据库中实际没有这些字段
    // 数据库 进行连表查询的时候其它表的属性
    private String title;
}
