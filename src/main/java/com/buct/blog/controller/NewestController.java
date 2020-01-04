package com.buct.blog.controller;

import com.alibaba.druid.sql.ast.SQLPartitionValue;
import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author  高谦
 * 最新文章列表页面
 */
@Controller
public class NewestController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/newest")
    public String newest(Map<String,Object> map){
        ArrayList<Category> categories=(ArrayList<Category>)categoryService.getCategoriesLimits(8);
        map.put("categories",categories);
        ArrayList<Article> newestArticles=(ArrayList<Article>) articleService.getArticlesByDate(1000);
        map.put("newestArticles",newestArticles);
        return "newest";
    }
}
