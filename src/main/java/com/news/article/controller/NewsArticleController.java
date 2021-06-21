package com.news.article.controller;

import com.news.article.model.NewsArticle;
import com.news.article.service.NewsArticleService;
import com.news.article.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class NewsArticleController {

    @Autowired
    private NewsArticleService newsArticleService;

    /**
     * This method will take NewsArticle Request body and will create new article
     * @param newsArticle
     * @return Success in case article is created successfully.
     *         Exception message in case of failure.
     */
    @PostMapping("/createArticle")
    public ResponseEntity createNewsArticle(@Valid @RequestBody NewsArticle newsArticle)
    {
        ResponseEntity responseEntity = null;
        try {
            newsArticleService.createNewsArticle(newsArticle);
            responseEntity = new ResponseEntity<>(Constants.ARTICLE_CREATED_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * This method will take NewsArticle object and update the news article
     * @param newsArticle
     * @return Success in case article is updated successfully.
     *         Exception message in case of failure.
     */
    @PutMapping("/updateArticle")
    public ResponseEntity updateNewsArticle(@Valid @RequestBody NewsArticle newsArticle)
    {
        ResponseEntity responseEntity = null;
        try {
            newsArticleService.updateNewsArticle(newsArticle);
            responseEntity = new ResponseEntity<>(Constants.ARTICLE_UPDATED_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * This method will fetch the news article on the basis of article id
     * @param id
     * @return NewsArticle will be returned on the basis of id.
     */
    @GetMapping("/getNewsArticle/{id}")
    public ResponseEntity getNewsArticle(@PathVariable Integer id)
    {
        ResponseEntity responseEntity = null;
        try {
            NewsArticle newsArticle = newsArticleService.getNewsArticle(id);
            responseEntity = new ResponseEntity<>(newsArticle, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

    /**
     * This method will return list of all news articles
     * @return List of news article
     */
    @GetMapping("/getAllNewsArticles")
    public ResponseEntity getAllNewsArticles()
    {
        ResponseEntity responseEntity = null;
        try {
            Collection<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
            responseEntity = new ResponseEntity<>(newsArticles, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
