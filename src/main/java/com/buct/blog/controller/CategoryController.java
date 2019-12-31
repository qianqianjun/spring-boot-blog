package com.buct.blog.controller;

import com.buct.blog.domain.Category;
import com.buct.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * @author 高谦
 */
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String category(Map<String, Object> map){
        ArrayList<Category> categoriesLimits=(ArrayList<Category>)
                categoryService.getCategoriesLimits(65535);
        map.put("categories",categoriesLimits);
        return "category";
    }
}
