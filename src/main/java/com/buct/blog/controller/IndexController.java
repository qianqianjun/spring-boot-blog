package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 高谦
 * 首页登controller
 */
@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String index(Map<String,Object> map){
        // 查找图片轮播文章
        ArrayList<Article> carouses=(ArrayList<Article>) articleService.getAllCarouselArticles();
        map.put("carouses",carouses);
        for(int i=0;i<carouses.size();i++){
            System.out.println(carouses.get(i).getContent());
        }
        // 全部专栏
        // 查找最新文章
        // 查找访问量最大的文章

        return "index";
    }
}
