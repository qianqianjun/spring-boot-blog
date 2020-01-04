package com.buct.blog.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 高谦
 * 文件上传的相关配置选项
 */
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfiguration {
    private String uploadDir;

    public String getUploadDir(){
        return uploadDir;
    }

    public void setUploadDir(String uploadDir){
        this.uploadDir=uploadDir;
    }
}
