package com.news.article.controller;

import com.news.article.model.NewsArticle;
import com.news.article.service.NewsArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NewsArticleControllerTest {

    @InjectMocks
    private NewsArticleController newsArticleController;

    @Mock
    private NewsArticleService newsArticleService;

    @Test
    public void testCreateNewsArticleSuccess() {
        assertThat(newsArticleController.createNewsArticle(new NewsArticle())).isNotNull();
    }

    @Test
    public void testUpdateNewsArticleSuccess() {
        assertThat(newsArticleController.updateNewsArticle(new NewsArticle())).isNotNull();
    }

    @Test
    public void testGetNewsArticleSuccess() {
        assertThat(newsArticleController.getNewsArticle(1)).isNotNull();
    }

    @Test
    public void testGetNewsArticlesSuccess() {
        assertThat(newsArticleController.getAllNewsArticles()).isNotNull();
    }

}
