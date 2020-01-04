package com.buct.blog.dao;

import com.buct.blog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  高谦
 * 用户信息相关的功能接口
 */
@Repository
public interface UserDao {
    //登录验证 write by 刘权达
    User login(String username, String password);

    /**
     * 修改用户的头像地址
     * @param url 头像新的图片地址
     */
    void changePic(String url);

    /**
     * 用户获取默认的用户
     * @return 返回数据库中的用户，其实就只有一个
     */
    User getDefaultUser();

    /**
     * 用于修改用户的基本信息
     * @param user 参数
     */
    void updateBasic(User user);

    /**
     * 修改用户的其它信息
     * @param user 参数
     */
    void updateOther(User user);
}
