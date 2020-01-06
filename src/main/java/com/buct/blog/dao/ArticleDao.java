package com.buct.blog.dao;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.ArticleAndCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    //按时间倒序获取前k个已发布文章
    public List<Article> getArticlesByDate(int k);
    //按访问量获取前k个文章
    public List<Article> getArticlesByVisitor(int k);

    /**
     * 按照文章id 获取文章
     * @param aid
     * @return
     */
    Article getArticleById(Integer aid);

    //添加文章 fix by qianqianjun 增加 status 字段
    public void addArticle(String title,String content,Integer type,Integer status,
                           String imgurl,String abstruct);
    //修改文章标题
    public void setArticleTitle(Integer id,String title);
    //修改文章内容
    public void setArticleContent(Integer id,String content);
    //修改文章摘要
    public void setArticleAbstruct(Integer id,String abstruct);
    //修改文章图片
    public void setArticleImgurl(Integer id,String imgurl);
    //修改文章轮播
    public void setArticleOutstanding(Integer id,Integer outstanding);
    //删除文章
    public void deleteArticle(Integer id);
    //按文章名模糊搜索文章
    public List<Article> getArticleByTitle(String title);
    //获取所有文章，包括未发布
    public List<Article> getAllArticles();
    //获取某一文章访问量
    public Integer getVisitorNum(Integer id);
    //修改某一文章访问量
    public void setVisitorNum(Integer id,Integer visitorNum);
    //修改文章状态
    public void setArticleStatus(Integer id,Integer status);
    //修改文章专栏
    public void setArticleType(Integer id,Integer type);
    //获取所有已发布文章
    public List<ArticleAndCategory> getAllPublishArticles();
    //获取所有未发布文章
    public List<ArticleAndCategory> getAllUnpublishArticles();
    //获取所有已删除文章
    public List<ArticleAndCategory> getAllDeleteArticles();
    //软删除文章
    public void softDeleteArticle(Integer id);
    //修改文章发布日期
    public void setArticlePublishDate(Integer id);
    //获取某一专栏所有文章
    public List<Article> getArticlesByType(Integer type);
}
