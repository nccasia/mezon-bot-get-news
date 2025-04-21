package com.mezon.bot.controller;

import com.mezon.bot.entity.PageUrl;
import com.mezon.bot.service.PageUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/page-url")
@RequiredArgsConstructor
public class PageUrlController {

    private final PageUrlService pageUrlService;

    @GetMapping
    public List<String> getPageUrlsByNewsPageName(@RequestParam String newsPageName) {
        return pageUrlService.getPageUrlsByNewsPageName(newsPageName);
    }
}
