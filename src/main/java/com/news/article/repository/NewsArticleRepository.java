package com.news.article.repository;

import com.news.article.model.NewsArticle;
import org.springframework.data.repository.CrudRepository;

public interface NewsArticleRepository extends CrudRepository<NewsArticle,Integer> {

}
