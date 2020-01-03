package com.buct.blog.controller;

import com.buct.blog.domain.UploadFile;
import com.buct.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author 高谦
 * 文件操作相关 接口
 */
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    /**
     * 上传文件返回文件的地址
     * @param file 前台传过来的文件
     * @return 返回文件名称
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        try{
            return fileService.storeFile(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
