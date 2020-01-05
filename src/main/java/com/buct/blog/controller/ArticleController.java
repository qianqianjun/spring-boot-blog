package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import com.buct.blog.domain.Comment;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.CategoryService;
import com.buct.blog.service.CommentService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    CommentService commentService;

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

    /**
     * 根据专栏标号返回专栏内的所有文章
     * @param type 专栏的标号，文章的type
     * @param map 前台数据传递
     * @return 返回专栏文章页面
     */
    @GetMapping("/articles/list")
    public String getArticlesByType(@RequestParam("type") Integer type,Map<String,Object> map){
        Category category=categoryService.getCategoryById(type);
        map.put("category",category);
        ArrayList<Article> articles=(ArrayList<Article> ) articleService.getArticlesByType(type);
        map.put("articles",articles);
        ArrayList<Category> categories=(ArrayList<Category>) categoryService.getCategoriesLimits(100);
        map.put("categories",categories);
        return "categoryArticles";
    }





    //给文章添加一条新评论,返回评论的id
    @GetMapping("/addComment")
    public String addComment(@RequestParam("aid") Integer aid,
                            @RequestParam("content") String content,
                             @RequestParam("email") String email,
                             @RequestParam("nickName") String nickName){
        Integer id = commentService.addComment(aid,content,email,nickName);
        System.out.println(id);
        return null;
    }
    //回复某条评论
    @GetMapping("/replyComment")
    public String replyComment(@RequestParam("id") Integer id,
                               @RequestParam("reply") String reply){
        commentService.replyComment(id,reply);

        return null;
    }
    //查看某篇文章的所有评论
    @GetMapping("/commentsByArticle")
    public String commentsByArticle(@RequestParam("aid") Integer aid){
        ArrayList<Comment> list = (ArrayList<Comment>)commentService.commentsByArticle(aid);
        System.out.println(list);
        return null;
    }

    /***
     * 添加文章接口
     * write by bingyu
     * fix by qianqianjun
     * @param title
     * @param content
     * @param type
     * @param status
     * @param abstruct
     * @return
     */
    @PostMapping("/addArticle")
    @ResponseBody
    public Article addArticle(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("type") Integer type,
                             @RequestParam("status") Integer status,
                             @RequestParam("abstruct") String abstruct){
        articleService.addArticle(title,content,type,status,abstruct);
        Article article=new Article();
        article.setAbstruct(abstruct);
        article.setContent(content);
        article.setType(type);
        article.setTitle(title);
        article.setStatus(status);
        return article;
    }



    //删除文章
    @GetMapping("/deleteArticle")
    public String deleteArticle(@RequestParam("id") Integer id){
        articleService.deleteArticle(id);
        return null;
    }
    //按文章名模糊搜索文章
    @GetMapping("/getArticleByTitle")
    public String getArticleByTitle(@RequestParam("title") String title){
        articleService.getArticleByTitle(title);
        return null;
    }
    //获取所有文章，包括未发布
    @GetMapping("/getAllArticles")
    public String getAllArticles(){
        articleService.getAllArticles();
        return null;
    }
    //获取某一文章访问量
    @GetMapping("/getVisitorNum")
    public String getVisitorNum(@RequestParam("id") Integer id){
        articleService.getVisitorNum(id);
        return null;
    }
    //修改某一文章访问量
    @GetMapping("/setVisitorNum")
    public String setVisitorNum(@RequestParam("id") Integer id,
                                @RequestParam("visitorNum") Integer visitorNum){
        articleService.setVisitorNum(id,visitorNum);
        return null;
    }
    //修改文章状态
    @GetMapping("/setArticleStatus")
    public String setArticleStatus(@RequestParam("id") Integer id,
                                @RequestParam("status") Integer status){
        articleService.setArticleStatus(id,status);
        return null;
    }
}
