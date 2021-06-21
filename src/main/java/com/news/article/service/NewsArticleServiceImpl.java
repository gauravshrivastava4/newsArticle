package com.news.article.service;

import com.news.article.exception.NewsArticleNotFoundException;
import com.news.article.model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class NewsArticleServiceImpl implements NewsArticleService{

    private static Map<Integer, NewsArticle> articleMap = new HashMap<>();

    /**
     * This method will take newsarticle object from request and save it into the hashmap
     * using the key as id
     * @param newsArticle
     */
    public void createNewsArticle(NewsArticle newsArticle){
        articleMap.put(newsArticle.getId(),newsArticle);
    }

    /**
     *
     * @param newsArticle
     */
    public void updateNewsArticle(NewsArticle newsArticle) throws NewsArticleNotFoundException {
        NewsArticle newsArticle1 = articleMap.get(newsArticle.getId());

        if(newsArticle1 == null)
            throw new NewsArticleNotFoundException("Article not present for id "+newsArticle.getId());

        newsArticle1.setText(newsArticle.getText());
        newsArticle1.setTitle(newsArticle1.getTitle());
        articleMap.put(newsArticle1.getId(),newsArticle1);
    }

    /**
     *
     * @param id
     * @return
     */
    public NewsArticle getNewsArticle(Integer id) throws NewsArticleNotFoundException {
        if(articleMap.get(id) == null)
            throw new NewsArticleNotFoundException("Article Not Found");
        return articleMap.get(id);
    }

    /**
     *
     * @return
     */
    public Collection<NewsArticle> getNewsArticles()
    {
        return articleMap.values();
    }
}
