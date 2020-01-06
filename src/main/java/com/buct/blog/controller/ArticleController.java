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
     * @param imgurl
     * @param abstruct
     * @return
     */
    @PostMapping("/addArticle")
    @ResponseBody
    public Article addArticle(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("type") Integer type,
                             @RequestParam("status") Integer status,
                             @RequestParam("imgurl") String imgurl,
                             @RequestParam("abstruct") String abstruct){
        articleService.addArticle(title,content,type,status,imgurl,abstruct);
        Article article=new Article();
        article.setAbstruct(abstruct);
        article.setContent(content);
        article.setImgurl(imgurl);
        article.setType(type);
        article.setTitle(title);
        article.setStatus(status);
        return article;
    }
    //修改文章标题
    @GetMapping("/setArticleTitle")
    public String setArticleTitle(@RequestParam("id") Integer id,
                                  @RequestParam("title") String title){
        articleService.setArticleTitle(id,title);
        return null;
    }
    //修改文章内容
    @GetMapping("/setArticleContent")
    public String setArticleContent(@RequestParam("id") Integer id,
                                    @RequestParam("content") String content){
        articleService.setArticleContent(id,content);
        return  null;
    }
    //修改文章摘要
    @GetMapping("/setArticleAbstruct")
    public String setArticleAbstruct(@RequestParam("id") Integer id,
                                     @RequestParam("abstruct") String abstruct){
        articleService.setArticleAbstruct(id,abstruct);
        return null;
    }
    //修改文章图片
    @GetMapping("/setArticleImgurl")
    public String setArticleImgurl(@RequestParam("id") Integer id,
                                     @RequestParam("imgurl") String imgurl){
        articleService.setArticleImgurl(id,imgurl);
        return null;
    }
    //修改文章轮播
    @GetMapping("/setArticleOutstanding")
    public String setArticleOutstanding(@RequestParam("id") Integer id,
                                   @RequestParam("outstanding") Integer outstanding){
        articleService.setArticleOutstanding(id,outstanding);
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
    public String deleteArticle(@RequestParam("id") Integer id){
        articleService.setArticleStatus(id,2);
        return "redirect:/manage/article";
    }
}
