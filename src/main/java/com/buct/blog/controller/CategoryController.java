package com.buct.blog.controller;

import com.buct.blog.domain.Category;
import com.buct.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    //删除专栏
    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("id") Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/manage/category";
    }
    //添加专栏
    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("imgurl") String imgurl){
        categoryService.addCategory(name,description,imgurl);
        return "redirect:/manage/category";
    }
    //修改专栏
    @PostMapping("/changeCategory")
    public String changeCategory(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("imgurl") String imgurl){
        System.out.println(name);
        categoryService.changeCategory(id,name,description,imgurl);
        return "redirect:/manage/category";
    }
}
