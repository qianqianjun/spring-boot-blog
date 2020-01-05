package com.buct.blog.dao;

import com.buct.blog.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    //按时间倒序获取前k个已发布文章
    public List<Article> getArticlesByDate(int k);
    //按访问量获取前k个文章
    public List<Article> getArticlesByVisitor(int k);

    /**
     * write by 高谦
     * 按照文章id 获取文章
     * @param aid 文章 id
     * @return 返回查找的文章
     */
    Article getArticleById(Integer aid);

    /**
     * 添加文章
     * write by 刘权达
     * fix by 高谦 增加 status 字段
     * @param title
     * @param blob  这里原来是string 类型的content ，为了防止乱码，改为字节数组
     * @param type
     * @param status
     * @param abstruct
     */
    public void addArticle(String title,byte[] blob,Integer type,Integer status,String abstruct);

    /**
     * write by 高谦
     * 删除掉了之前 单个修改文章某一字段的功能，一并修改
     * @param article 文章信息
     */
    public int fixArticle(Article article);



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

    /**
     * write by 高谦
     * @param type 文章的type
     * @return 文章列表
     */
    List<Article> getArticlesByType(Integer type);
}
