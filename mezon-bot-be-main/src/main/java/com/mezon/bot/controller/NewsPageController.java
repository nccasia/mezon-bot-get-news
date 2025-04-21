package com.mezon.bot.controller;

import com.mezon.bot.dto.NewsPageDTO;
import com.mezon.bot.service.NewsPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-page")
@RequiredArgsConstructor
public class NewsPageController {

    private final NewsPageService newsPageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody NewsPageDTO newsPageDTO) {
        newsPageService.addNewsPage(newsPageDTO);
    }
}
