package com.buct.blog.service;

import com.buct.blog.dao.CategoryDao;
import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    //获取前k个栏目
    public List<Category> getCategoriesLimits(int k){return categoryDao.getCategoriesLimits(k);}
    //获取某一栏目所有已发布文章
    public List<Article> getArticlesByCategory(int type){return categoryDao.getArticlesByCategory(type);}
}
