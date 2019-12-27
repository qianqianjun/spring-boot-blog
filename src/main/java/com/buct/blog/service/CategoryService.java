package com.buct.blog.service;

import com.buct.blog.dao.CategoryDao;
import com.buct.blog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    public List<Category> getCategoriesLimits(int k){return categoryDao.getCategoriesLimits(k);}
}
