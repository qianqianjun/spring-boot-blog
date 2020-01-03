package com.buct.blog.controller;
import com.buct.blog.domain.Category;
import com.buct.blog.domain.User;
import com.buct.blog.service.CategoryService;
import com.buct.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author 高谦
 * 实现后台管理系统的相关功能
 */
@Controller
public class BackManageController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    /**
     * 后台管理页面数据准备接口
     * @param request 用于获取Session来判断是否登录
     * @return 返回模板页面
     */
    @GetMapping("/manage")
    public String manage(HttpServletRequest request,Map<String,Object> map){
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:/manage/login";
        }
        User user=(User) session.getAttribute("user");
        map.put("user",user);
        return "backmanage/index";
    }

    /**
     * 用于返回登录页面
     * @param request 判断是否已经登录，已经登录的话直接跳转到管理界面即可
     * @return 返回登录界面模板引擎
     */
    @GetMapping("/manage/login")
    public String login(HttpServletRequest request,Map<String,Object> map){
        HttpSession session=request.getSession();
        // 如果已经登录了，跳转到管理界面
        if(session.getAttribute("user")!=null){
            return "redict:/manage";
        }
        // 如果没有登录信息，则直接返回登录页面
        return "backmanage/login";
    }

    /**
     * 用于验证账号和密码是否正确
     * @param account 账号信息
     * @param password 密码信息
     * @param map 前台数据接受器
     * @param request 用于获取Session
     * @return 登录成功跳转到管理界面，失败的话返回错误信息到登录界面
     */
    @PostMapping("/manage/verification")
    public String verification(@RequestParam("account") String account,
                               @RequestParam("password") String password,
                               Map<String,Object> map,
                               HttpServletRequest request){
        if(account.equals("") || password.equals("")){
            map.put("error","请您完整输入账号和密码");
            return "backmanage/login";
        }else{
            User user=userService.login(account,password);
            if(user==null){
                map.put("error","账号或者密码错误！");
                return "backmanage/login";
            }
            // 创建session 跳转到后台管理界面
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
        }
        return "redirect:/manage";
    }

    /**
     * 用于注销登录
     * @param request 用于获取 session
     * @return 返回登录页面
     */
    @GetMapping("/manage/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("user",null);
        return "redirect:/manage/login";
    }

    /**
     * 后台文章管理界面
     * @param request 获取用户的信息
     * @param map 前台数据传递
     * @return 返回文章管理页面
     */
    @GetMapping("/manage/article")
    public String manageArticle(HttpServletRequest request,Map<String,Object> map){
        User user=(User) request.getSession().getAttribute("user");
        map.put("user",user);
        return "backmanage/articleManage";
    }

    /**
     * 写文章索引页面
     * @param request 获取session
     * @param map 用于前台参数传递
     * @return 返回写文章的页面
     */
    @GetMapping("/manage/write")
    public String write(HttpServletRequest request,Map<String,Object>map){
        User user=(User) request.getSession().getAttribute("user");
        map.put("user",user);
        ArrayList<Category> categories=(ArrayList<Category>) categoryService.getCategoriesLimits(1000);
        map.put("categories",categories);
        return "backmanage/write";
    }
}
