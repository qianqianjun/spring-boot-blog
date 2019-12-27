package com.buct.blog.service;

import com.buct.blog.dao.CarouselDao;
import com.buct.blog.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高谦
 * 文章相关service
 */
@Service
public class ArticleService {
    @Autowired
    CarouselDao carouselDao;

    @Autowired
    ArticleDao articleDao;

    /**查找图片轮播文章。
     * @return
     */
    public List<Article> getAllCarouselArticles(){
        ArrayList<Article> articles=(ArrayList<Article>) carouselDao.getAllCarousel();
        for(int i=0;i<articles.size();i++){
            Article article=articles.get(i);
            articles.get(i).setAbstruct(article.getContent().substring(200));
        }
        return articles;
    }

    /**
     * 根据文章的id 返回对应文章对象
     * @param aid 文章的id
     * @return 返回文章
     */
    public Article getArticleById(Integer aid){
        return null;
    }
}

