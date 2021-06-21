package com.news.article.service;

import com.news.article.exception.NewsArticleNotFoundException;
import com.news.article.model.NewsArticle;
import com.news.article.repository.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        newsArticle = this.CalculateRelevance(newsArticle);
        newsArticleRepository.save(newsArticle);
    }

    /**
     *  This method will update the news article on the basis of the request we receive.
     * @param newsArticle
     */
    public void updateNewsArticle(NewsArticle newsArticle) throws NewsArticleNotFoundException {
        if(!newsArticleRepository.existsById(newsArticle.getId()))
            throw new NewsArticleNotFoundException("Article not present for id "+newsArticle.getId());
        newsArticle = this.CalculateRelevance(newsArticle);
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

    /**
     * This method will calculate article relevance
     * if there are more ! than . set relevance to HOT
     * if there are more , than . set relevance to BORING
     * Else set relevance to STANDARD
     * @param newsArticle
     * @return newsArticle updated news article object with relevance values
     */
    private NewsArticle CalculateRelevance(NewsArticle newsArticle)
    {
        String relevance = "STANDARD";
        String text = newsArticle.getText();
        Integer exclaimCount = StringUtils.countOccurrencesOf(text, "!");
        Integer fullStopsCount = StringUtils.countOccurrencesOf(text, ".");
        Integer commaCount = StringUtils.countOccurrencesOf(text, ",");
        if(exclaimCount > fullStopsCount)
            relevance = "HOT";
        else if(commaCount > fullStopsCount)
            relevance = "BORING";

        newsArticle.setArticleRelevance(relevance);
        return newsArticle;
    }
}
