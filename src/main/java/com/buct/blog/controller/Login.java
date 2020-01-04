package com.buct.blog.controller;

import com.buct.blog.domain.User;
import com.buct.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 高谦
 * 用于登录
 */
@Controller
public class Login {
    @Autowired
    UserService userService;
    /**
     * 用于返回登录页面
     * @param request 判断是否已经登录，已经登录的话直接跳转到管理界面即可
     * @return 返回登录界面模板引擎
     */
    @GetMapping("/manage/login")
    public String login(HttpServletRequest request, Map<String,Object> map){
        HttpSession session=request.getSession();
        // 如果已经登录了，跳转到管理界面
        if(session.getAttribute("user")!=null){
            return "redict:/manage";
        }
        User user=userService.getDefaultUser();
        map.put("user",user);
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
        User defaultUser=userService.getDefaultUser();
        map.put("user",defaultUser);

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
}
