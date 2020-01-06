package com.buct.blog.dao;

import com.buct.blog.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    //添加评论,返回评论id

    /**
     * write by 刘权达
     * fix by 高谦
     * 加你妹的nickname 啊，有毛用？ 我删了。
     * @param aid 对应文章的id
     * @param content 留言内容
     * @param email 留言人的 邮箱
     */
    void addComment(Integer aid,String content,String email);
    //回复评论
    void replyComment(Integer id,String reply);
    //查看某一文章所有评论
    List<Comment> commentsByArticle(Integer aid);

    /**
     * write by 高谦
     * 获取所有评论信息，包括文章信息才可以。
     * @return 返回 评论和文章的view
     */
    List<Comment> getAllComments();
}
