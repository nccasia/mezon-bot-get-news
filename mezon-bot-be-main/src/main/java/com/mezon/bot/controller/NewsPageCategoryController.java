package com.mezon.bot.controller;

import com.mezon.bot.service.NewsPageCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-page-category")
@RequiredArgsConstructor
public class NewsPageCategoryController {

    private final NewsPageCategoryService newsPageCategoryService;

    @GetMapping("/by-newspage-name")
    public ResponseEntity<List<String>> getCategoryNamesByNewsPageName(@RequestParam String newsPageName) {
        List<String> categoryNames = newsPageCategoryService.getCategoryNamesByNewsPageName(newsPageName);
        return ResponseEntity.ok(categoryNames);
    }
}
