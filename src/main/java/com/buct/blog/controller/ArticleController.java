package com.buct.blog.controller;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import com.buct.blog.domain.Comment;
import com.buct.blog.service.ArticleService;
import com.buct.blog.service.CategoryService;
import com.buct.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * write by 高谦
     * 根据文章的id 获取文章的所有信息。
     * 注意，每一次获取文章都要使得浏览量加一
     * 获取文章的时候需要同时获取评论信息。
     * @param aid 文章的 id
     * @return 文章的所有信息。
     */
    @GetMapping("/articles/detail")
    public String articles(@RequestParam("aid") Integer aid,Map<String,Object> map){
        // 获取文章
        Article article=articleService.getArticleById(aid);
        // 文章浏览量加一
        articleService.setVisitorNum(article.getId(),article.getVisitorNum()+1);
        article.setVisitorNum(article.getVisitorNum()+1);
        map.put("article",article);
        // 获取评论
        ArrayList<Comment> comments=(ArrayList<Comment>)
                commentService.commentsByArticle(article.getId());
        map.put("comments",comments);
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



    //发布文章，status=1
    @GetMapping("/publishArticle")
    public String publishArticle(@RequestParam("id") Integer id){
        articleService.setArticleStatus(id,1);
        return "redirect:/manage/article";
    }
    //撤销文章
    @GetMapping("/cancelArticle")
    public String cancelArticle(@RequestParam("id") Integer id){
        System.out.println(id);
        articleService.setArticleStatus(id,0);
        return "redirect:/manage/article";
    }
    //删除文章
    @GetMapping("/deleteArticle")
    public String deleteArticle(@RequestParam("id") Integer id) {
        articleService.setArticleStatus(id, 2);
        return "redirect:/manage/article";
    }

    /**
     * 添加评论接口
     * @param aid 评论对应的文章的 id
     * @param content 评论的内容
     * @param email 评论人的 email 地址
     * @return 返回添加的评论内容
     */
    @PostMapping("/addComment")
    @ResponseBody
    public Comment addComment(@RequestParam("aid") Integer aid,
                              @RequestParam("content") String content,
                              @RequestParam("email") String email){
        commentService.addComment(aid,content,email);
        Comment comment=new Comment();
        comment.setAid(aid);
        comment.setContent(content);
        comment.setEmail(email);
        return comment;

    }

    //修改文章
    @PostMapping("/fixArticle")
    @ResponseBody
    public Article fixArticle(@RequestParam("id") Integer id,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam("type") Integer type,
                                 @RequestParam("status") Integer status,
                                 @RequestParam("abstruct") String abstruct){
        articleService.fixArticle(id,title,content,type,status,abstruct);
        Article article=new Article();
        article.setId(id);
        article.setAbstruct(abstruct);
        article.setContent(content);
        article.setType(type);
        article.setTitle(title);
        article.setStatus(status);
        return article;
    }
}
