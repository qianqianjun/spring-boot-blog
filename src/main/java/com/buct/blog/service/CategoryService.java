package com.buct.blog.service;

import com.buct.blog.dao.ArticleDao;
import com.buct.blog.dao.CategoryDao;
import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ArticleDao articleDao;
    //获取前k个栏目
    public List<Category> getCategoriesLimits(int k){return categoryDao.getCategoriesLimits(k);}
    //获取某一栏目所有已发布文章
    public List<Article> getArticlesByCategory(int type){return categoryDao.getArticlesByCategory(type);}
    //获取所有专栏
    public List<Category> getAllCategories(){return  categoryDao.getAllCategories();}
    //删除专栏
    public void deleteCategory(Integer id){
        ArrayList<Article> arrayList = (ArrayList<Article>)  articleDao.getArticlesByType(id);
        for(int i=0;i<arrayList.size();i++){
            articleDao.setArticleType(arrayList.get(i).getId(),null);
            articleDao.setArticleStatus(arrayList.get(i).getId(),0);
        }
        categoryDao.deleteCategory(id);
    }
    //添加专栏
    public void addCategory(String name,String description,String imgurl){
        categoryDao.addCategory(name,description,imgurl);
    }
    //修改专栏
    public void changeCategory(Integer id,String name,String description,String imgurl){
        categoryDao.setCategoryDescription(id,description);
        categoryDao.setCategoryName(id,name);
        categoryDao.setCategoryImgurl(id,imgurl);
    }
}
