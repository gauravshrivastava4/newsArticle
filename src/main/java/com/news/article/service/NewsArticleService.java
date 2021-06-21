package com.news.article.service;

import com.news.article.exception.NewsArticleNotFoundException;
import com.news.article.model.NewsArticle;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface NewsArticleService {

    public void createNewsArticle(NewsArticle newsArticle);

    public void updateNewsArticle(@RequestBody NewsArticle newsArticle) throws NewsArticleNotFoundException;

    public NewsArticle getNewsArticle(Integer id) throws NewsArticleNotFoundException;

    public Collection<NewsArticle> getNewsArticles();
}
