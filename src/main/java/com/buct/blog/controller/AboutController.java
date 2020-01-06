package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.User;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 高谦
 */
@Controller
public class AboutController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/about")
    public String about(Map<String,Object> map){
        User user=userService.getDefaultUser();
        if(user.getResume()==null){
            Article blank=new Article();
            blank.setContent("当前没有介绍文章页面或者页面被删除，请进入后台维护简历页面！");
            map.put("article",blank);
            return "about";
        }
        Article article=articleService.getArticleById(user.getResume());
        if(article==null){
            Article blank=new Article();
            blank.setContent("当前没有介绍文章页面或者页面被删除，请进入后台维护简历页面！");
            map.put("article",blank);
        }else{
            map.put("article",article);
        }
        return "about";
    }
}
