package com.buct.blog.dao;

import com.buct.blog.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    //按时间倒序获取前k个文章
    public List<Article> getArticlesByDate(int k);
    //按访问量获取前k个文章
    public List<Article> getArticlesByVisitor(int k);
}
