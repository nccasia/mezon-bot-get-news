package com.mezon.bot.controller;

import com.mezon.bot.entity.News;
import com.mezon.bot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;


    @GetMapping("/search")
    public Page<News> getNews(
            @RequestParam(name = "newsPageName") String newsPageName,
            @RequestParam(name = "category") String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return newsService.getNewsByPageNameAndCategory(newsPageName, category, page, size);
    }
}
