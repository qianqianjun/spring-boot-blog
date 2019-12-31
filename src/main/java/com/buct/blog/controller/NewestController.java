package com.buct.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author  高谦
 * 最新文章列表页面
 */
@Controller
public class NewestController {
    @GetMapping("/newest")
    public String newest(){
        return "newest";
    }
}
