package com.buct.blog.domain;

import lombok.Data;

/**
 * @author  高谦
 * 上传文件的 domain
 */
@Data
public class UploadFile {
    private String fileDownloadUrl;
    private String fileType;
    private long size;
    public UploadFile(String fileDownloadUrl,String fileType,long size){
        this.size=size;
        this.fileDownloadUrl=fileDownloadUrl;
        this.fileType=fileType;
    }
}
