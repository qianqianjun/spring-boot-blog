package com.buct.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 高谦
 */
@Controller
public class CategoryController {
    @GetMapping("/category")
    public String category(){
        return "services";
    }
}
