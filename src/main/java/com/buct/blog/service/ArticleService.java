package com.buct.blog.service;

import com.buct.blog.dao.CarouselDao;
import com.buct.blog.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高谦
 * 文章相关service
 */
@Service
public class ArticleService {
    @Autowired
    CarouselDao carouselDao;
    public List<Article> getAllCarouselArticles(){
        return carouselDao.getAllCarousel();
    }
}
