package com.buct.blog.service;

import com.buct.blog.dao.ArticleDao;
import com.buct.blog.dao.CarouselDao;
import com.buct.blog.domain.Article;
import com.buct.blog.domain.ArticleAndCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高谦
 * 文章相关service
 */
@Service
public class ArticleService {
    @Autowired
    CarouselDao carouselDao;

    @Autowired
    ArticleDao articleDao;

    /**
     * 查找图片轮播文章。
     * @return 返回文章数组
     */
    public List<Article> getAllCarouselArticles(){
        ArrayList<Article> articles=(ArrayList<Article>) carouselDao.getAllCarousel();
        return articles;
    }

    /**
     * write by 刘权达
     * 根据发布日期显示最新的 k 个文章
     * @param k 文章条数限制
     * @return 文章数组
     */
    public List<Article> getArticlesByDate(int k){
        return articleDao.getArticlesByDate(k);
    }

    /**
     * write by 刘权达
     * 显示浏览量最大的 前 k 个文章
     * @param k 文章条数限制
     * @return 文章数组
     */
    public List<Article> getArticleByVisitor(int k){
        return articleDao.getArticlesByVisitor(k);
    }

    /**
     * 根据文章的id 返回对应文章对象
     * @param aid 文章的id
     * @return 返回文章
     */
    public Article getArticleById(Integer aid){
        Article article= articleDao.getArticleById(aid);
        if(article==null){
            return null;
        }
        article.setContent(article.getBlob());
        return article;
    }

    /**
     * 添加文章页面
     * write by 刘权达
     * fix by 高谦
     * 为了防止数据库乱码，将content 换为 blob
     * @param title 文章标题
     * @param content 文章内容
     * @param type 文章专栏标号
     * @param status 文章当前状态
     * @param abstruct 文章摘要
     */
    public void addArticle(String title,String content,Integer type,
                           Integer status,String abstruct){
        try{
            byte[] blob=content.getBytes("utf-8");
            articleDao.addArticle(title,blob,type,status,abstruct);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改文章
     * @param title 文章标题
     * @param content 文章内容
     * @param type 文章专栏标号
     * @param status 文章当前状态
     * @param abstruct 文章摘要
     */
    public void fixArticle(Integer id,String title,String content,Integer type,
                           Integer status,String abstruct){
        Article article=new Article();
        article.setId(id);
        article.setBlob(content);
        article.setTitle(title);
        article.setStatus(status);
        article.setAbstruct(abstruct);
        article.setType(type);
        articleDao.fixArticle(article);
    }


    //删除文章
    public void deleteArticle(Integer id){
        articleDao.deleteArticle(id);
    }
    //按文章名模糊搜索文章
    public List<Article> getArticleByTitle(String title){
        return articleDao.getArticleByTitle(title);
    }
    //获取所有文章，包括未发布
    public List<Article> getAllArticles(){
        return articleDao.getAllArticles();
    }
    //获取某一文章访问量
    public Integer getVisitorNum(Integer id){
        return articleDao.getVisitorNum(id);
    }
    //修改某一文章访问量
    public void setVisitorNum(Integer id,Integer visitorNum){
        articleDao.setVisitorNum(id,visitorNum);
    }
    //修改文章状态
    public void setArticleStatus(Integer id,Integer status){
        articleDao.setArticleStatus(id,status);
    }
    //获取所有已发布文章
    public List<ArticleAndCategory> getAllPublishArticlesAndCategory(){
        return articleDao.getAllPublishArticlesAndCategory();}
    //获取所有未发布文章
    public List<ArticleAndCategory> getAllUnpublishArticles(){return articleDao.getAllUnpublishArticles();}
    //获取所有已删除文章
    public List<ArticleAndCategory> getAllDeleteArticles(){return articleDao.getAllDeleteArticles();}

    /**
     * write by 高谦
     * @param type 根据专栏的id （文章的type 来获取一系列文章）
     * @return 返回一系列文章
     */
    public List<Article> getArticlesByType(Integer type) {
        return articleDao.getArticlesByType(type);
    }

    /**
     * write by 高谦
     * 获取所有已经发布的文章
     * @return 已经发布的文章列表
     */
    public List<Article> getAllPublishArticles(){
        return articleDao.getAllPublishArticles();
    }

    /**
     * write by 高谦
     * 获取有 category name 的article view
     * @return article view list
     */
    public List<Article> getArticlesWithCategory(){
        return articleDao.getArticleAndCategory();
    }

    /**
     * write by 高谦
     * @param article 参数
     */
    public void setBanner(Article article) {
        articleDao.setBanner(article);
    }

    /**
     * write by 高谦
     * 取消文章的轮播
     * @param id 文章id
     */
    public void cancleBanner(Integer id) {
        articleDao.cancleBanner(id);
    }

}