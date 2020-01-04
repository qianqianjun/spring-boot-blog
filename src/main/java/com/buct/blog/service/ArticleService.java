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

    /**查找图片轮播文章。
     * @return
     */
    public List<Article> getAllCarouselArticles(){
        ArrayList<Article> articles=(ArrayList<Article>) carouselDao.getAllCarousel();
        return articles;
    }
    public List<Article> getArticlesByDate(int k){return articleDao.getArticlesByDate(k);}
    public List<Article> getArticleByVisitor(int k){return articleDao.getArticlesByVisitor(k);}

    /**
     * 根据文章的id 返回对应文章对象
     * @param aid 文章的id
     * @return 返回文章
     */
    public Article getArticleById(Integer aid){
        return articleDao.getArticleById(aid);
    }

    //添加文章
    public void addArticle(String title,String content,Integer type,
                           Integer status,String imgurl,String abstruct){
        articleDao.addArticle(title,content,type,status,imgurl,abstruct);
    }
    //修改文章标题
    public void setArticleTitle(Integer id,String title){
        articleDao.setArticleTitle(id,title);
    }
    //修改文章内容
    public void setArticleContent(Integer id,String content){
        articleDao.setArticleContent(id,content);
    }
    //修改文章摘要
    public void setArticleAbstruct(Integer id,String abstruct){
        articleDao.setArticleAbstruct(id,abstruct);
    }
    //修改文章图片
    public void setArticleImgurl(Integer id,String imgurl){
        articleDao.setArticleImgurl(id,imgurl);
    }
    //修改文章轮播
    public void setArticleOutstanding(Integer id,Integer outstanding){
        articleDao.setArticleOutstanding(id,outstanding);
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
    public List<ArticleAndCategory> getAllPublishArticles(){return articleDao.getAllPublishArticles();}
    //获取所有未发布文章
    public List<ArticleAndCategory> getAllUnpublishArticles(){return articleDao.getAllUnpublishArticles();}
    //获取所有已删除文章
    public List<ArticleAndCategory> getAllDeleteArticles(){return articleDao.getAllDeleteArticles();}
}