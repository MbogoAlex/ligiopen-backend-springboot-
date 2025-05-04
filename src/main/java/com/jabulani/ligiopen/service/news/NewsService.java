package com.jabulani.ligiopen.service.news;

import com.jabulani.ligiopen.model.news.dto.NewsCreationRequestDto;
import com.jabulani.ligiopen.model.news.dto.NewsDto;
import com.jabulani.ligiopen.model.news.dto.NewsItemCreationDto;
import com.jabulani.ligiopen.model.news.dto.NewsItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsDto createNews(NewsCreationRequestDto newsCreationRequestDto, MultipartFile coverPhoto) throws IOException;
    NewsDto updateNews(NewsCreationRequestDto newsCreationRequestDto);
    NewsDto updateNewsCoverPhoto(Integer newsId, MultipartFile file) throws IOException;

    NewsDto getNews(Integer newsId);
    List<NewsDto> getAllNews(Integer clubId);
    String deleteNews(Integer newsId);
    String deleteNewsCoverPhoto(Integer fileId);

    NewsItemDto createNewsItem(NewsItemCreationDto newsItemCreationDto, MultipartFile file) throws IOException;
    NewsItemDto updateNewsItem(NewsItemCreationDto newsItemCreationDto);

    NewsItemDto getNewsItem(Integer newsItemId);

}
