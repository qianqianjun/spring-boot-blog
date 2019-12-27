package com.buct.blog.dao;

import com.buct.blog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    //登录验证
    List<User> login(String username, String password);
}
