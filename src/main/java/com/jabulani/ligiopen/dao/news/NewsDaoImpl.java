package com.jabulani.ligiopen.dao.news;

import com.jabulani.ligiopen.model.news.News;
import com.jabulani.ligiopen.model.news.NewsItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao{
    private final EntityManager entityManager;
    @Autowired
    public NewsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public News createNews(News news) {
        entityManager.persist(news);
        return news;
    }

    @Override
    public News updateNews(News news) {
        entityManager.merge(news);
        return news;
    }

    @Override
    public String deleteNews(Integer newsId) {
        return null;
    }

    @Override
    public News getNewsById(Integer newsId) {
        TypedQuery<News> query = entityManager.createQuery("from News where id = :newsId", News.class);
        query.setParameter("newsId", newsId);
        return query.getSingleResult();
    }

    @Override
    public List<News> getAllNews() {
        TypedQuery<News> query = entityManager.createQuery("from News", News.class);
        return query.getResultList();
    }

    @Override
    public NewsItem createNewsItem(NewsItem newsItem) {
        entityManager.persist(newsItem);
        return newsItem;
    }

    @Override
    public NewsItem updateNewsItem(NewsItem newsItem) {
        entityManager.merge(newsItem);
        return newsItem;
    }

    @Override
    public String deleteNewsItem(Integer newsItemId) {
        return null;
    }

    @Override
    public NewsItem getNewsItemById(Integer newsItemId) {
        TypedQuery<NewsItem> query = entityManager.createQuery("from NewsItem where id = :newItemId", NewsItem.class);
        query.setParameter("newItemId", newsItemId);
        return query.getSingleResult();
    }

    @Override
    public List<NewsItem> getAllNewsItems() {
        TypedQuery<NewsItem> query = entityManager.createQuery("from NewsItem ", NewsItem.class);
        return query.getResultList();
    }
}
