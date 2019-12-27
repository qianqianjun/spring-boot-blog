package com.buct.blog.dao;

import com.buct.blog.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
    //获取前k个栏目
    public List<Category> getCategoriesLimits(int k);
}
