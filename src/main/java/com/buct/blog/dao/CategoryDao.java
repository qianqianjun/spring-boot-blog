package com.buct.blog.dao;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
    //获取前k个栏目
    public List<Category> getCategoriesLimits(int k);
    //获取某一栏目所有已发布文章
    public List<Article> getArticlesByCategory(int type);
}
