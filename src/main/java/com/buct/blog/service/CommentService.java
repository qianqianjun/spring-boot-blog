package com.buct.blog.service;

import com.buct.blog.dao.CommentDao;
import com.buct.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    //插入一条新评论，返回该记录的id
    public Integer addComment(Integer aid,String content,String email,String nickName){
        return commentDao.addComment(aid,content,email,nickName);
    }
    //回复一条评论
    public void replyComment(Integer id,String reply){
        commentDao.replyComment(id,reply);
    }
    //查看某一文章所有的评论
    public List<Comment> commentsByArticle(Integer aid){
        return commentDao.commentsByArticle(aid);
    }
}
