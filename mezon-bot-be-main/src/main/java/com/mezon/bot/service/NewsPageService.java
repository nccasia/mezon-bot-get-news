package com.mezon.bot.service;

import com.mezon.bot.dto.NewsPageDTO;
import com.mezon.bot.entity.NewsPage;
import com.mezon.bot.repository.NewsPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsPageService {

    private final NewsPageRepository newsPageRepository;

    public void addNewsPage(NewsPageDTO newsPageDTO) {
        NewsPage newsPage = NewsPage.builder()
                .url(newsPageDTO.getUrl())
                .build();
        newsPageRepository.save(newsPage);
    }
}
