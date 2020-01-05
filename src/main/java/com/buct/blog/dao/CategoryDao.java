package com.buct.blog.dao;

import com.buct.blog.domain.Article;
import com.buct.blog.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
    //获取前k个栏目
    public List<Category> getCategoriesLimits(Integer k);
    //获取某一栏目所有已发布文章
    public List<Article> getArticlesByCategory(Integer type);
    //添加专栏
    public void addCategory(String name,String description,String imgurl);
    //修改专栏名称
    public void setCategoryName(Integer id,String name);
    //删除专栏
    public void deleteCategory(Integer id);
    //获取所有专栏
    public List<Category> getAllCategories();
    //修改专栏描述
    public void setCategoryDescription(Integer id,String description);
    //修改专栏图片
    public void setCategoryImgurl(Integer id,String imgurl);

    /**
     * write by 高谦
     * 根据专栏标号获取专栏
     * @param id 专栏id
     * @return 专栏对象
     */
    Category getCategoryById(Integer id);
}
