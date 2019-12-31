package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/***
 * @author  高谦
 * 文章相关的前端接口。
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 根据文章的id 获取文章的所有信息。
     * @param aid 文章的 id
     * @return 文章的所有信息。
     */
    @GetMapping("/articles/detail")
    public String articles(@RequestParam("aid") Integer aid){
        Article article=articleService.getArticleById(aid);
        System.out.println(article.getContent());
        return "article";
    }
}
