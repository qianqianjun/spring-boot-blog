package com.buct.blog.dao;

import com.buct.blog.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    //添加评论,返回评论id
    Integer addComment(Integer aid,String content,String email,String nickName);
    //回复评论
    void replyComment(Integer id,String reply);
    //查看某一文章所有评论
    List<Comment> commentsByArticle(Integer aid);
}
