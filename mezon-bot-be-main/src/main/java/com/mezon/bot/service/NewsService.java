package com.mezon.bot.service;

import com.mezon.bot.entity.News;
import com.mezon.bot.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    public Page<News> getNewsByPageNameAndCategory(String newsPageName, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishDate"));
        return newsRepository.findByNewsPageNameAndCategory(newsPageName, category, pageable);
    }
}
