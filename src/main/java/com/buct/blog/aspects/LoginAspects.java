package com.buct.blog.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author  高谦
 * 权限拦截器，防止用户不登录进行后台操作
 */
@Aspect
@Component
public class LoginAspects {
    @Pointcut("execution(public * com.buct.blog.controller.BackManageController.*(..))")
    public void login(){

    }

    @Around("login()")
    public Object isLogin(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes=(ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:/manage/login";
        }
        return joinPoint.proceed();
    }
}
