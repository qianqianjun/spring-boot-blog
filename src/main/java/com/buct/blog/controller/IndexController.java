package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import com.buct.blog.domain.User;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.CategoryService;
import com.buct.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 高谦
 * 首页文章显示 controller
 */
@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String index(Map<String,Object> map){
        // 查找图片轮播文章
        ArrayList<Article> carouses=(ArrayList<Article>) articleService.getAllCarouselArticles();
        map.put("carouses",carouses);

        int k=2;
        ArrayList<Article> articlesByDate=(ArrayList<Article>) articleService.getArticlesByDate(k);
        map.put("articlesByDate",articlesByDate);
        k=2;
        ArrayList<Article> articlesByVisitor=(ArrayList<Article>) articleService.getArticleByVisitor(k);
        map.put("articleByVisitor",articlesByVisitor);

        ArrayList<Category> categoriesLimits=(ArrayList<Category>) categoryService.getCategoriesLimits(k);
        map.put("categoriesLimits",categoriesLimits);
        // 全部专栏
        // 查找最新文章
        // 查找访问量最大的文章

        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Map<String,Object> map){
        ArrayList<User> user = (ArrayList<User>) userService.login(username,password);
        map.put("user",user);
        return "index";
    }

    @GetMapping("/articles/detail")
    public String articles(@RequestParam("aid") Integer aid){
        Article article=articleService.getArticleById(aid);
        System.out.println(article.getContent());
        return article.getContent();
    }

}
