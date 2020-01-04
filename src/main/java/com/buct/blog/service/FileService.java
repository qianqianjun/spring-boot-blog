package com.buct.blog.service;

import com.buct.blog.configuration.FileConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  高谦
 * 上传文件的service
 */
@Service
public class FileService{
    private final Path fileStorageLocation;
    @Autowired
    public FileService(FileConfiguration conf){
        this.fileStorageLocation= Paths.get(conf.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 存储文件到系统
     * @param file 文件对象
     * @return 返回文件的名字
     * @throws Exception 不能存储文件的时候报错
     */
    public String storeFile(MultipartFile file) throws Exception{
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String oriname=file.getOriginalFilename();
        String[] parts=oriname.split("\\.");
        String fileName= StringUtils.cleanPath(df.format(new Date()))+"."+parts[parts.length-1];
        Path targetLocation=this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


    /**
     * 加载文件
     * @param fileName 文件名称
     * @return 返回resource
     * @throws Exception 文件不存在的时候异常
     */
    public Resource loadFileAsResource(String fileName) throws Exception{
        Path filepath=this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource=new UrlResource(filepath.toUri());
        if(resource.exists()) {
            return resource;
        }else{
            throw new Exception("file not found"+fileName);
        }
    }
}
