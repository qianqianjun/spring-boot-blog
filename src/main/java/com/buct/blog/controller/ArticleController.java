package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

/***
 * @author  高谦
 * 文章相关的前端接口。
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    /**
     * 根据文章的id 获取文章的所有信息。
     * @param aid 文章的 id
     * @return 文章的所有信息。
     */
    @GetMapping("/articles/detail")
    public String articles(@RequestParam("aid") Integer aid,Map<String,Object> map){
        Article article=articleService.getArticleById(aid);
        map.put("article",article);
        ArrayList<Category> categories= (ArrayList<Category>)categoryService.getCategoriesLimits(8);
        map.put("categories",categories);
        return "article";
    }

    @GetMapping("/articles/list")
    public String articlesByCategory(@RequestParam("type") Integer type,Map<String,Object> map){
        ArrayList<Article> articlesByCategory=(ArrayList<Article>)
                categoryService.getArticlesByCategory(type);
        map.put("acticleList",articlesByCategory);

        return "recommend";
    }
}
