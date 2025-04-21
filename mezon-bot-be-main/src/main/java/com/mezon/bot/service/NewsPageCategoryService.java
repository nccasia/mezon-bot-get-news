package com.mezon.bot.service;

import com.mezon.bot.repository.NewsPageCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsPageCategoryService {

    private final NewsPageCategoryRepository newsPageCategoryRepository;

    public List<String> getCategoryNamesByNewsPageName(String newsPageName) {
        return newsPageCategoryRepository.findCategoryNamesByNewsPageName(newsPageName);
    }
}
