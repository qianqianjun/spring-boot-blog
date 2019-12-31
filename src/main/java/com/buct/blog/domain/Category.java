package com.buct.blog.domain;

import lombok.Data;


/**
 * @author 高谦
 * 分类专栏结构表格
 */

@Data
public class Category {
    private Integer id;
    private String name;
    private String description;
    private String imgurl;
    private String publishdate;

    public String getImgurl() {
        return imgurl;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getPublishdate() {
        return publishdate;
    }
}
