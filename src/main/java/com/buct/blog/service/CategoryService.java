package com.buct.blog.service;

import com.buct.blog.dao.CategoryDao;
import com.buct.blog.domain.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;
    public List<category> getCategoriesLimits(int k){return categoryDao.getCategoriesLimits(k);}
}
