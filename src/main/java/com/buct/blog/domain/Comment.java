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
    private String nickName;
}
