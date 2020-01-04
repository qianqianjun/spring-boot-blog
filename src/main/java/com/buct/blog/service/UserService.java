package com.buct.blog.service;

import com.buct.blog.dao.UserDao;
import com.buct.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User login(String username, String password){
        return userDao.login(username,password);
    }

    /**
     * @author 高谦
     * 用于更改用户头像
     * @param url 用户新头像地址
     */
    public void changePic(String url) {
        userDao.changePic(url);
    }

    /**
     * 登录界面显示用户的头像，需要查找数据库来获取图像信息。
     * @return 返回博主信息
     */
    public User getDefaultUser() {
        return userDao.getDefaultUser();
    }

    /**
     * 更新用户的基本信息
     * @param user 参数
     */
    public void updateBasic(User user) {
        userDao.updateBasic(user);
    }

    /**
     * 修改用户的其它信息
     * @param user 参数
     */
    public void updateOther(User user) {
        userDao.updateOther(user);
    }
}
