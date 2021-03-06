package com.buct.blog.domain;

import lombok.Data;

/**
 * @author  高谦
 * 博客主人信息表格
 */
@Data
public class User {
    private String account;
    private String password;

    private String img;

    private String email;
    private String phone;
    private String company;
    private String position;

    private String csdn;
    private String github;
    private String usualmail;

    private Integer resume;
}
