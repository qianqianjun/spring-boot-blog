package com.buct.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class BackManageController {
    @GetMapping("/manage")
    public String index(){
        return "backmanage/index";
    }

    @GetMapping("/manage/login")
    public String login(){
        return "backmanage/login";
    }
}
