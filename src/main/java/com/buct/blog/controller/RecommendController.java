package com.buct.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendController {
    @GetMapping("/recommend")
    public String recommend(){
        return "recommend";
    }
}
