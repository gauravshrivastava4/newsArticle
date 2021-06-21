package com.news.article.service;

import com.news.article.exception.NewsArticleNotFoundException;
import com.news.article.model.NewsArticle;
import com.news.article.repository.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class NewsArticleServiceImpl implements NewsArticleService{

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    /**
     * This method will take newsarticle object from request and save it into the hashmap
     * using the key as id
     * @param newsArticle
     */
    public void createNewsArticle(NewsArticle newsArticle){
        newsArticleRepository.save(newsArticle);
    }

    /**
     *  This method will update the news article on the basis of the request we receive.
     * @param newsArticle
     */
    public void updateNewsArticle(NewsArticle newsArticle) throws NewsArticleNotFoundException {
        if(!newsArticleRepository.existsById(newsArticle.getId()))
            throw new NewsArticleNotFoundException("Article not present for id "+newsArticle.getId());
        newsArticleRepository.save(newsArticle);
    }

    /**
     * This method will fetch the data from database on the basis of article id
     * @param id
     * @return NewsArticle Object retrieved from the database
     */
    public NewsArticle getNewsArticle(Integer id) throws NewsArticleNotFoundException {
        return newsArticleRepository.findById(id).get();
    }

    /**
     * This method will fetch and return all news articles available in the database
     * @return Collection of NewsArticle
     */
    public Collection<NewsArticle> getNewsArticles()
    {
        return (Collection<NewsArticle>) newsArticleRepository.findAll();
    }
}
