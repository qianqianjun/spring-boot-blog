package com.buct.blog.service;

import com.buct.blog.dao.CommentDao;
import com.buct.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    //插入一条新评论，返回该记录的id

    /**
     * write by 刘权达
     * fix by 高谦
     * 删除了没有的 nickname
     * @param aid 文章 id
     * @param content 留言内容
     * @param email 留言人的email
     */
    public void addComment(Integer aid,String content,String email){
        commentDao.addComment(aid,content,email);
    }
    //回复一条评论
    public void replyComment(Integer id,String reply){
        commentDao.replyComment(id,reply);
    }
    //查看某一文章所有的评论
    public List<Comment> commentsByArticle(Integer aid){
        return commentDao.commentsByArticle(aid);
    }

    /**
     * 获取所有的评论信息
     * @return 返回评论信息列表
     */
    public List<Comment> getAllComment() {
        return commentDao.getAllComments();
    }

    /**
     *write by 高谦
     * @param id
     * @param reply
     */
    public void fixComment(Integer id, String reply) {
        commentDao.fixComment(id,reply);
    }

    /**
     *write by 高谦
     * @param id
     */
    public void delComment(Integer id) {
        commentDao.delComment(id);
    }
}
